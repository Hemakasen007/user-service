package com.travel.lodge.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private String status;
    private String message;
    private Object data;

    public CommonResponse(String status, String message){
        this.status = status;
        this.message = message;
    }
}
