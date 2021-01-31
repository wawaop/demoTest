package com.example.demo.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * Excel 读取时间测试
 */
@RestController
public class ExcelReadController {

    @PostMapping("/readExcelByPoi")
    public String readExcelByPoi(MultipartFile multipartFile) throws IOException {
        Long start = System.currentTimeMillis();
        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for(int rowindex =0 ;rowindex < sheet.getLastRowNum();rowindex++){
            Row row = sheet.getRow(rowindex);
            for(int cellIndex =0 ;cellIndex<row.getLastCellNum();cellIndex++){
                Cell cell = row.getCell(cellIndex);
                System.out.println(cell.getNumericCellValue());
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("执行时间为："+ (start - end) /1000 + "秒");
        return "ByPOI 成功";
    }

    @PostMapping("/readExcelByEasyExcel")
    public String reaExcelByEasyExcel(MultipartFile multipartFile){
        return "ByEasyExcel 成功";
    }
}
