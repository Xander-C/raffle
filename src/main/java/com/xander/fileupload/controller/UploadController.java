package com.xander.fileupload.controller;

import com.xander.fileupload.User;
import com.xander.fileupload.response.ApiResponse;
import com.xander.fileupload.response.BusinessException;
import com.xander.fileupload.service.DaoService;
import com.xander.fileupload.service.ResolveExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    @Resource(name="resolveExcelService")
    private ResolveExcelService resolveExcelService;

    @Resource(name = "daoService")
    private DaoService daoService;


    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse uploadExcel(@RequestParam("file") MultipartFile file) {
        Object result;
        try {
            result = resolveExcelService.resolveExcel(file);
        } catch (BusinessException e) {
            e.printStackTrace();
            return ApiResponse.failOf(-1, e.getErrMsg());
        }
        return ApiResponse.successOf(result);
    }

    @RequestMapping("/upload")
    public String Redirect(){
        return "raffle/raffle";
    }

    @RequestMapping("/raffle")
    @ResponseBody
    public User getRand(){return daoService.getRand();}

    @RequestMapping("/listDatabase")
    @ResponseBody
    public List<User> findAllUser01(){
        return daoService.findAllUser();
    }



}