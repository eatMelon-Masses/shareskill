package com.shareskill.controller;

import com.shareskill.model.TDataresource;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface DataController_json {
    public List<TDataresource> browseData();
    public List<Object> delDataRes(TDataresource data, BindingResult bindingResult);
}
