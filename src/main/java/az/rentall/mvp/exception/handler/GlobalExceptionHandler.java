package az.rentall.mvp.exception.handler;

import az.rentall.mvp.exception.*;
import az.rentall.mvp.model.dto.response.exception.ErrorResponseDto;
import az.rentall.mvp.model.dto.response.exception.FieldErrorResponse;
import az.rentall.mvp.model.dto.response.exception.ValidationExceptionResponse;
import az.rentall.mvp.service.TelegramNotificationService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final TelegramNotificationService telegramNotificationService;
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException (NullPointerException nullPointerException) {
        log.error("Not found "+nullPointerException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(404,
                        nullPointerException.getMessage(),
                        "NOT_FOUND",
                        LocalDateTime.now()));

    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(NotFoundException exception) {
        return buildExceptionResponse(exception.getMessage(), NOT_FOUND.value(), "NOT_FOUND");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponseDto handleProductNotFoundException(ProductNotFoundException exception) {
        return buildExceptionResponse(exception.getMessage(), NOT_FOUND.value(), "NOT_FOUND");
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponseDto handleFileUploadException (FileUploadException fileUploadException) {
        log.error("Not found "+fileUploadException.getMessage());
        return buildExceptionResponse(fileUploadException.getMessage(),BAD_REQUEST.value(),"FILE_UPLOAD_ERROR");
    }

    @ExceptionHandler({BadCredentialsException.class,
            NotVerifiedException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponseDto handleBadCredentialsException (BadCredentialsException badCredentialsException) {
        log.error("Not found "+badCredentialsException.getMessage());
        return buildExceptionResponse(badCredentialsException.getMessage(),BAD_REQUEST.value(),"WRONG_CREDENTIALS_ERROR");
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponseDto handleAlreadyExistsException(AlreadyExistsException exception) {
        log.error("Already exists for argument: "+exception.getMessage());
        return buildExceptionResponse(exception.getMessage(), CONFLICT.value(), "ALREADY_EXISTS");
    }

    @ExceptionHandler(DeletionException.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponseDto handleDeletionException(DeletionException exception) {
        log.error("Delete exception for: "+exception.getMessage());
        return buildExceptionResponse(exception.getMessage(), CONFLICT.value(), "DELETION_ERROR");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponseDto handleMethodValidationException(HandlerMethodValidationException exception) {
        log.error("Validation failed for argument: "+exception.getMessage());
        return buildExceptionResponse(exception.getMessage(), BAD_REQUEST.value(), "HANDLER_METHOD_VALIDATION_ERROR");
    }

    @ExceptionHandler({UnauthorizedAccessException.class,
            InvalidTokenException.class,
            TokenExpiredException.class})
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponseDto handleUnauthorizedAccessException(UnauthorizedAccessException exception) {
        log.error("Unauthorized access for argument: "+exception.getMessage());
        return buildExceptionResponse(exception.getMessage(), UNAUTHORIZED.value(), "UNAUTHORIZED");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception exception) {
        log.error("Internal server error for argument: "+exception.getMessage());
        return buildExceptionResponse(exception.getMessage(), INTERNAL_SERVER_ERROR.value(), "UNEXPECTED_EXCEPTION");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ValidationExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.error("Method not valid for argument: "+exception.getMessage());
        List<FieldErrorResponse> fieldErrors =new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            FieldErrorResponse error = FieldErrorResponse.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            fieldErrors.add(error);
        });
        return buildValidationExceptionResponse(BAD_REQUEST.value(), fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    private ValidationExceptionResponse handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Constraint violation exception for argument: "+exception.getMessage());
        List<FieldErrorResponse> fieldErrors = exception.getConstraintViolations()
                .stream().map(violation ->
                        FieldErrorResponse.builder()
                                .field(violation.getPropertyPath().toString())
                                .message(violation.getMessage())
                                .build())
                .collect(Collectors.toList());
        return buildValidationExceptionResponse(BAD_REQUEST.value(), fieldErrors);
    }


    private ValidationExceptionResponse buildValidationExceptionResponse(Integer status, List<FieldErrorResponse> filedErrors){
        return ValidationExceptionResponse.builder()
                .timestamp(LocalDateTime.now().format(DATE_FORMATTER))
                .status(status)
                .code("VALIDATION_FAILED")
                .fieldErrors(filedErrors)
                .build();
    }

    private ErrorResponseDto buildExceptionResponse(String message, int status, String code) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(message)
                .errorCode(status)
                .code(code)
                .localDateTime(LocalDateTime.now())
                .build();
       telegramNotificationService.sendNotification(errorResponseDto.toString());
        return errorResponseDto;
    }
}
