package com.taotao.config;

import lombok.Data;

/**
 * @author taotao11
 * @version 1.0
 */

@Data
public class Result {

    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";

    //状态码
    private String code;
    //后台报错信息
    private String msg;
    //返回的数据
    private Object data;


    //成功的方法
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }

}
