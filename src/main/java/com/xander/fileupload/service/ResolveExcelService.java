package com.xander.fileupload.service;

import com.xander.fileupload.User;
import com.xander.fileupload.dao.UserDao;
import com.xander.fileupload.response.BusinessException;
import com.xander.fileupload.response.ReturnCode;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Service("resolveExcelService")
public class ResolveExcelService{
    @Autowired
    private UserDao userDao;
    /**
     * 注册url
     */
    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";
    /**
     * 学号的正则
     */
    public static final String SID_REG = "[BQH][\\d]{8}";

    public List<User> resolveExcel(MultipartFile file) throws BusinessException {
        userDao.deleteAll();
        List<User> list = new ArrayList<>();
        if (file == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "对象不能为空");
        }
        //获取名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误");
        }
        if (workbook == null) {
            throw new BusinessException(ReturnCode.CODE_FAIL, "格式错误");
        } else {
            //获取所有的工作表的的数量
            int numOfSheet = workbook.getNumberOfSheets();
            //遍历这个这些表
            for (int i = 0; i < numOfSheet; i++) {
                //获取一个sheet也就是一个工作簿
                Sheet sheet = workbook.getSheetAt(i);
                int lastRowNum = sheet.getLastRowNum();
                //从第一行开始第一行一般是标题
                for (int j = 1; j <= lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    User user = new User();
                    //获取电话单元格
                    if (row.getCell(0) != null) {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        String sid = row.getCell(0).getStringCellValue();
                        //todo 正则比对
                        boolean match = Pattern.matches(SID_REG, sid);
                        if (!match) {
                            throw new BusinessException(ReturnCode.CODE_FAIL, "电话格式错误");
                        }
                        user.setSid(sid);
                    }
                    //姓名
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        String name = row.getCell(1).getStringCellValue();
                        user.setName(name);
                    }
                    list.add(user);
                }
            }

            for(User i : list){
                userDao.insertUserByParam(i);
            }
        }
        return list;

    }


}
