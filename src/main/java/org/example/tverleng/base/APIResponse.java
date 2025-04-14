package org.example.tverleng.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse <T>{
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
