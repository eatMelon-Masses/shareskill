package com.shareskill.controller;

import com.shareskill.model.TDataresource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataController {
    /**��д���ݱ�*/
    public String writeDataResource(Model model);
    /**������Դ����*/
    public String addDataResource(HttpServletRequest request, HttpServletResponse response,Model model, TDataresource data,@RequestParam(value = "upload",required = false) MultipartFile file);

    /**ɾ����Դ����*/
    public String delDataResourceById(Model model,Integer id);

    /**�޸���Դ����*/
    public String updateDataResource(Model model,TDataresource data);

    /**�鿴��Դ����*/
    public String viewDataResourceById(Model model,Integer id);

    /**
     * ��ת����ҳ��������
     */
    public String searchDataByCateAndKey(HttpServletRequest request,Model model, Integer cateId,Integer Page, String keyWord);
    /**
     * �����Դ����
     */
    public String browseDataResourceByUser(HttpServletRequest request,Model model,Integer Page);

    /**
     * �����ؽ���
     * @return
     */
    public String showDownLoad(HttpServletRequest request,Model model);
}
