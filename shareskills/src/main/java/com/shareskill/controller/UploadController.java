package com.shareskill.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UploadController {

    public String uploadPicture(HttpServletRequest request, HttpServletResponse response, @RequestParam("upload")MultipartFile file);
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
                                               @RequestParam Integer resId,
                                               Model model)throws Exception;
    public String test(HttpServletRequest request, HttpServletResponse response);

}
