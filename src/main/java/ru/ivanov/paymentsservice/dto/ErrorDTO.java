package ru.ivanov.paymentsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private String status;
    private int code;
    private String[] errors;

    public ErrorDTO(HttpStatus httpStatus, String... errors) {
        setStatus(httpStatus.name());
        setCode(httpStatus.value());
        setErrors(errors);
    }
}
