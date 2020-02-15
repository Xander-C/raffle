package com.xander.fileupload.controller;

import com.xander.fileupload.User;
import com.xander.fileupload.response.ApiResponse;
import com.xander.fileupload.response.BusinessException;
import com.xander.fileupload.service.DaoService;
import com.xander.fileupload.service.ResolveExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String uploadExcel(@RequestParam("file") MultipartFile file,Model model) {
        Object result;
        try {
            result = resolveExcelService.resolveExcel(file);
        } catch (BusinessException e) {
            e.printStackTrace();
            model.addAttribute("log", ApiResponse.failOf(-1, e.getErrMsg()));
            return "failure";
        }
        model.addAttribute("log", ApiResponse.successOf(result));
        return "success";
    }

    @RequestMapping("/raffle")
    public String getRand(Model model){
        Map<String, String> map = new HashMap<>();
        User user;

        user = daoService.getRand();
        map.put("sid0",user.getSid());
        map.put("name0",user.getName());

        user = daoService.getRand();
        map.put("sid1",user.getSid());
        map.put("name1",user.getName());

        user = daoService.getRand();
        map.put("sid2",user.getSid());
        map.put("name2",user.getName());

        user = daoService.getRand();
        map.put("sid3",user.getSid());
        map.put("name3",user.getName());

        user = daoService.getRand();
        map.put("sid4",user.getSid());
        map.put("name4",user.getName());

        user = daoService.getRand();
        map.put("sid5",user.getSid());
        map.put("name5",user.getName());

        model.addAllAttributes(map);
        return "raffle";
    }

    @RequestMapping("/listDatabase")
    @ResponseBody
    public List<User> findAllUser01(){
        return daoService.findAllUser();
    }

    @RequestMapping("/rand")
    @ResponseBody
    public User randData(){return daoService.getRand();}
}