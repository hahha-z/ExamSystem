package com.ljx.common;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String msg;
    private Object data;
    private Long total;

    public static Result fail(){
        return result(400,"失败",null, 0L);
    }

    public static Result success(){
        return result(200,"成功",null,0L);
    }

    public static Result success(Object data){
        return result(200,"成功",data,0L);
    }

    public static Result success(Object data,Long total){
        return result(200,"成功",data,total);
    }

    private static Result result(int code, String msg, Object data, Long total) {
        Result result = new Result();
        result.setData(data);
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        return result;
    }
}
