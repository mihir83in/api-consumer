package com.zp.apiconsumer.commons.model.api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private int code;
    private String message;
}
