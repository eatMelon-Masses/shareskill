package com.shareskill.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonUtilsTest {

    @Test
    public void ajaxJson() {
    }

    @Test
    public void ajax() {
    }

    @Test
    public void getJsonObject() {
    }

    @Test
    public void addDateToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        //JsonUtils.addDateToJsonArray(jsonArray,"key","values");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","values");
        jsonArray.add(jsonObject);
        System.out.print(jsonArray.toString());

    }
}