package com.example.springsecuritydemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyResponse<T> {

    private int code;

    private T data;

    private String message;

    public static<T> MyResponse success(T data) {
        MyResponse myResponse = new MyResponse();
        myResponse.setCode(200);
        myResponse.setData(data);
        myResponse.setMessage("成功");
        return myResponse;
    }


    public static MyResponse fail(String message) {
        MyResponse myResponse = new MyResponse();
        myResponse.setCode(500);
        myResponse.setMessage(message);
        return myResponse;
    }
}
