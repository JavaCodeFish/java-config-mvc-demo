package com.yuan.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yuan.demo.model.dto.ResponseMsg;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static <T> String toJsonString(T object){
        return JSON.toJSONString(object);
    }

    public static <T> String toJsonString(List<T> list){
        return JSONArray.toJSONString(list);
    }

    public static void main(String[] args) {
        ResponseMsg msg = new ResponseMsg();
        msg.setCode(100);
        msg.setMsg("mmmmm");
        List<ResponseMsg> list = new ArrayList<>();
        list.add(msg);
        msg.setCode(101);
        list.add(msg);
        msg.setCode(102);
        list.add(msg);
        msg.setCode(103);
        list.add(msg);
        System.out.println(JsonUtil.toJsonString(list));
    }
}
