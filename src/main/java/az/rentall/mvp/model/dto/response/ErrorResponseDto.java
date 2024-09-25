package az.rentall.mvp.model.dto.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorResponseDto {
    int errorCode;
    String message;
    LocalDateTime localDateTime;
}
