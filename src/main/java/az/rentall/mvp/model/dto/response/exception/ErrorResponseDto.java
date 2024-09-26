package az.rentall.mvp.model.dto.response.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Value
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ErrorResponseDto {

    int errorCode;
    String message;
    String code;
    LocalDateTime localDateTime;
}
