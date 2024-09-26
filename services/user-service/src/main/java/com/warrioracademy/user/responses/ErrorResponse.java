package com.warrioracademy.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Date date;
    private String path;
    private String message;
}
