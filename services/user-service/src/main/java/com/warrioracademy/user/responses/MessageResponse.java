package com.warrioracademy.user.responses;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    @NotNull
    private String message;
    private Object object;

    public MessageResponse(String message) {
        this.message = message;
    }
}
