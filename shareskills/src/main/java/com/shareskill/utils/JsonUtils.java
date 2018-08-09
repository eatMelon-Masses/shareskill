package com.shareskill.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class JsonUtils {
    public static void ajaxJson(String jsonString,HttpServletResponse response) {
        ajax(jsonString, "application/json",response);
    }
    private static void ajax(String content, String type,HttpServletResponse response) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getJsonObject(){
        return new JSONObject();

    }

    /**
     * 添加消息到jsonArray里
     * @param jsonArray
     * @param key
     * @param value
     */
    public static void addDateToJsonArray(JSONArray jsonArray,String key,String value){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key,value);
        jsonArray.add(jsonObject);
    }
}