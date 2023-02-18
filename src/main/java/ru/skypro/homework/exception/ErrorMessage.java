package ru.skypro.homework.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// Имхо лучше вообще не писать комменты, чем писать такие.
// По названию и так понятно, что это "Сообщение, содержащее информацию об ошибке"
/**
 * Сообщение, содержащее информацию об ошибке
 */
@Getter
public class ErrorMessage {

    private final int statusCode;

    private final LocalDateTime timestamp;

    private final String message;

    private final String description;

    private ErrorMessage(HttpStatus httpStatus, String message, String description) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = httpStatus.value();
        this.message = message;
        this.description = description;
    }

    public static ErrorMessage of(HttpStatus httpStatus, String message, String description) {
        return new ErrorMessage(httpStatus, message, description);
    }
}
