package com.example.demo.controller;

import com.example.demo.dao.TestDao;
import com.example.demo.entity.TableEntity;
import com.example.demo.entity.TestTable;
import com.example.demo.utils.ExcelLinkage;
import com.example.demo.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class TestController {

    @Autowired
    TestDao testDao;

    @GetMapping("/test")
    public String test(){
        System.out.println("测试断点");
        return  "test";
    }

    @RequestMapping("/excel")
    public void turnToTest(HttpServletResponse response){
        String fname = "useData";// Excel文件名
        try {
//            //正题——创建Excel HSSWorkbook————.xls
//            Workbook workbook = new XSSFWorkbook();
//            //创建sheet页
//            //使用次数
//            XSSFSheet mainSheet= (XSSFSheet) workbook.createSheet("mainSheet");
//            //收入
//            XSSFSheet sheet= (XSSFSheet) workbook.createSheet("sheet");
//
//            //以上——创建Excel
//            String[] associatePartyType = new String[]{"a1","a2"};
//            String[] legal = new String[]{"b1", "b2"};
//
//            ExcelUtils.genearteOtherSheet2(sheet, 1, "股东类型", associatePartyType, mainSheet, false, workbook, 2,"");
//            ExcelUtils.genearteOtherSheet2(sheet, 2, "法人", legal, mainSheet, true, workbook, 3, "C");
//            ExcelUtils.genearteOtherSheet2(sheet, 2, "test", legal, mainSheet, true, workbook, 3, "C");

            HSSFWorkbook workbook = new HSSFWorkbook();//excel文件对象  
            HSSFSheet userinfosheet1 = workbook.createSheet();//工作表对象
            //创建一个隐藏sheet
            this.createHideSheet(workbook, "dictionary");
            //设置名称数据集
            this.creatExcelNameList(workbook);
            //创建一行数据
            this.creatAppRow(userinfosheet1, "许果",1);
            this.creatAppRow(userinfosheet1, "刘德华",2);
            this.creatAppRow(userinfosheet1, "刘本龙",3);

//            ExcelLinkage excelLinkage = new ExcelLinkage();
////            excelLinkage.creatHideSheet(workbook,"dicSheet");
////
//
//            // 设置sheet 名称
//            HSSFSheet excelSheet = workbook.createSheet("excel");
//            // 设置样式
//            excelLinkage.setDataCellStyles(workbook, excelSheet);
//            // 创建一个隐藏页和隐藏数据集
//            excelLinkage.creatHideSheet(workbook, "shutDataSource");
//            // 设置名称数据集
//            excelLinkage.creatExcelNameList(workbook, "shutDataSource");
//            // 创建一行数据
//            for (int i = 0; i < 50; i++) {
//                excelLinkage.creatAppRow(excelSheet,i);
//
//            }

            //输出
            OutputStream os = response.getOutputStream();
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls"); // 设定输出文件头,该方法有两个参数，分别表示应答头的名字和值。
            response.setContentType("application/msexcel");
            workbook.write(os);
            os.flush();
            os.close();
            System.out.println("文件生成");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/saveTable")
    public String saveTestEntity(){
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTestContext("ceshi");
        testDao.insert(tableEntity);
        return "保存成功";
    }


    /**
     * 名称管理
     * @param workbook
     */
    public void creatExcelNameList(HSSFWorkbook workbook){
        Name name = workbook.createName();
        name.setNameName("province");
        name.setRefersToFormula("dictionary!$A$1:$E$1");

        name = workbook.createName();
        name.setNameName("浙江");
        name.setRefersToFormula("dictionary!$B$2:$K$2");

        name = workbook.createName();
        name.setNameName("山东");
        name.setRefersToFormula("dictionary!$B$3:$I$3");

        name = workbook.createName();
        name.setNameName("江西");
        name.setRefersToFormula("dictionary!$B$4:$E$4");

        name = workbook.createName();
        name.setNameName("江苏");
        name.setRefersToFormula("dictionary!$B$5:$I$5");

        name = workbook.createName();
        name.setNameName("四川");
        name.setRefersToFormula("dictionary!$B$6:$K$6");
    }


    /**
     * 创建隐藏sheet保存用于选择的数据字典信息
     * @param workbook
     * @param hideSheetName
     */
    public void createHideSheet(HSSFWorkbook workbook,String hideSheetName){
        HSSFSheet dictionary = workbook.createSheet(hideSheetName);//隐藏一些信息
        //设置下拉列表的内容
        String[] provinceList = {"浙江","山东","江西","江苏","四川"};
        String[] zjProvinceList = {"浙江","杭州","宁波","温州","台州","绍兴","金华","湖州","丽水","衢州","舟山"};
        String[] sdProvinceList = {"山东","济南","青岛","烟台","东营","菏泽","淄博","济宁","威海"};
        String[] jxProvinceList = {"江西","南昌","新余","鹰潭","抚州"};
        String[] jsProvinceList = {"江苏","南京","苏州","无锡","常州","南通","泰州","连云港","徐州"};
        String[] scProvinceList = {"四川","成都","绵阳","自贡","泸州","宜宾","攀枝花","广安","达州","广元","遂宁"};
        //在隐藏页设置选择信息
//        HSSFRow provinceRow = dictionary.createRow(0);
//        this.creatRow(provinceRow, provinceList);
//        HSSFRow zjProvinceRow = dictionary.createRow(1);
//        this.creatRow(zjProvinceRow, zjProvinceList);
//        HSSFRow sdProvinceRow = dictionary.createRow(2);
//        this.creatRow(sdProvinceRow, sdProvinceList);
//        HSSFRow jxProvinceRow = dictionary.createRow(3);
//        this.creatRow(jxProvinceRow, jxProvinceList);
//        HSSFRow jsProvinceRow = dictionary.createRow(4);
//        this.creatRow(jsProvinceRow, jsProvinceList);
//        HSSFRow scProvinceRow = dictionary.createRow(5);
//        this.creatRow(scProvinceRow, scProvinceList);
        //设置隐藏页标志
        workbook.setSheetHidden(workbook.getSheetIndex(hideSheetName), true);
    }

    /**
     * 创建一列应用数据
     * @param 
     * @param userName
     */
    public void creatAppRow(HSSFSheet sheet,String userName,int naturalRowIndex){
        //构造一个信息输入表单，用户姓名，出生省份，出生城市
        //要求省份是可以下拉选择的，出生城市根据所选择的省份级联下拉选择
        //在第一行第一个单元格，插入下拉框
        HSSFRow row = sheet.createRow(naturalRowIndex-1);
        HSSFCell provinceLableCell = row.createCell(0);
        provinceLableCell.setCellValue("省份:");
        HSSFCell provinceCell = row.createCell(1);
        provinceCell.setCellValue("请选择");
        HSSFCell cityLableCell = row.createCell(2);
        cityLableCell.setCellValue("城市:");
        HSSFCell cityCell = row.createCell(3);
        cityCell.setCellValue("请选择");

        //得到验证对象
        DataValidation validation = this.getDataValidationByFormula("province",naturalRowIndex,2);
        //工作表添加验证数据
        sheet.addValidationData(validation);


        /*
         * 网上很多资料都是错的 。这里INDIRECT($B1)	D后面必须是1，excel会自动处理
         */
        validation = this.getDataValidationByFormula("INDIRECT($B1)",naturalRowIndex,4);
        //工作表添加验证数据
        sheet.addValidationData(validation);
    }

    /**
     * 创建一列数据
     * @param currentRow
     * @param textList
     */
    public void creatRow(HSSFRow currentRow, String[] textList){
        if(textList!=null&&textList.length>0){
            int i = 0;
            for(String cellValue : textList){
                HSSFCell cell = currentRow.createCell(i++);
                cell.setCellValue(cellValue);
            }
        }
    }

    /**
     * 使用已定义的数据源方式设置一个数据验证
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @return
     */
    public static DataValidation getDataValidationByFormula(String formulaString,int naturalRowIndex,int naturalColumnIndex){
        //创建公式约束（数据有效性）
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);

        int firstRow = naturalRowIndex-1;
        int lastRow = naturalRowIndex-1;
        int firstCol = naturalColumnIndex-1;
        int lastCol = naturalColumnIndex-1;
        CellRangeAddressList regions=new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
        //数据有效性对象
        DataValidation validation = new HSSFDataValidation(regions,constraint);
        return validation;
    }


}
