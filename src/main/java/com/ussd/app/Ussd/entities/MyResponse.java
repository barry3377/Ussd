package com.ussd.app.Ussd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class MyResponse<T> {
        private String status;
        private T object;

}
