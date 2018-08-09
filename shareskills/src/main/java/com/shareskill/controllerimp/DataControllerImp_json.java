package com.shareskill.controllerimp;

import com.shareskill.controller.DataController_json;
import com.shareskill.model.TDataresource;
import com.shareskill.service.DataCategoryService;
import com.shareskill.service.DataResourceService;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLSimpleType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class DataControllerImp_json implements DataController_json {
    @Autowired
    DataResourceService dataResourceService;
    @Autowired
    DataCategoryService dataCategoryService;
    private final static Log logger = LogFactory.getLog(DataControllerImp_json.class);
    final static String RESULT = "result";
    final static String MESSAGE = "message";
    final static String TRUE = "true";
    final static String FALSE = "false";
    public DataResourceService getDataResourceService() {
        return dataResourceService;
    }

    public void setDataResourceService(DataResourceService dataResourceService) {
        this.dataResourceService = dataResourceService;
    }

    public DataCategoryService getDataCategoryService() {
        return dataCategoryService;
    }

    public void setDataCategoryService(DataCategoryService dataCategoryService) {
        this.dataCategoryService = dataCategoryService;
    }
    @ResponseBody
    @RequestMapping("/json/browseData")
    @Override
    public List<TDataresource> browseData() {
        List<TDataresource> dataresourceList=dataResourceService.browseAllDataResource();
        for (TDataresource data : dataresourceList) {
            if (data.getUserId() != null) {//不需要用户信息
                data.setYhnc(data.getUserId().getYhnc());
                data.setUserId(null);
            }
        }
        return dataresourceList;
    }

    @ResponseBody
    @RequestMapping("/json/delDataRes")
    @Override
    public List<Object> delDataRes(@Validated(TDataresource.TDataresourceD.class) TDataresource data, BindingResult bindingResult) {
        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT,FALSE);
        mapList.put(MESSAGE, message);
        jsonList.add(mapList);
        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            return jsonList;
        }
        if (dataResourceService.delDataResById(data.getId())) {
            mapList.put(RESULT, TRUE);
            message.append("删除成功");

        }else{
            message.append("删除失败,未知错误");
            return jsonList;
        }
        return jsonList;
    }
}
