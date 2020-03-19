package com.elvis.springcloud.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    public CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult<T> succ() {
        return new CommonResult();
    }
}
