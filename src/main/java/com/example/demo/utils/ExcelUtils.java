package com.example.demo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelUtils {
    /**
     * 查询下拉列表值，写入到隐藏sheet页中
     * @Title: genearteOtherSheet
     * @author hegg
     * @date 2019年12月30日 下午5:03:15
     * @return 返回类型 void
     * @param sheet 为数据字典sheet页
     * @param index 为数据字典对应的值，保存在sheet页第几列，同一个sheet页多次调用本方法（index不可以重复，否则后面的列会覆盖前面的列）
     * @param columnDesc 为数据字典sheet页第几列保存对应的值的描述
     * @param selectList 下拉框内容
     * @param mainSheet 为主sheet页用来实现下拉框作为展示
     * @param isjilian
     * @param workbook 为当前Workbook
     * @param mainParentIndex 为主sheet页mainSheet第几列需要被设置为下拉框
     * @param father 当前列为子级下拉框的内容受父级哪一列的影响
     */
    public static void genearteOtherSheet2(Sheet sheet, int index, String columnDesc, String[] selectList, XSSFSheet mainSheet, boolean isjilian, Workbook workbook, int mainParentIndex, String father) {
        Row titleRow = null;
        // 创建下拉列表值存储工作表
        int totalRowNumber = sheet.getLastRowNum();//获取sheet页有效行
        if(totalRowNumber == 0){
            titleRow = sheet.createRow(0);
        } else {
            titleRow = sheet.getRow(0);
        }
        Cell titleCell = titleRow.createCell(index);
        titleCell.setCellValue(columnDesc);

        for (int i = 0; i < selectList.length; i++) {
            Row row = null;
            if(totalRowNumber >= i+1){
                row = sheet.getRow(i+1);
            } else {
                row = sheet.createRow(i+1);
            }
            Cell cell = row.createCell(index);
            cell.setCellValue(selectList[i]);
        }
        // 将下标转换为字母0=A，1=B，
        String word = getIndexWord(index + 1);
        //设置名称为typelist的sheet页中哪些单元格的内容为下拉框，例如：=Sheet1!$C$2:$C$8意思是将C列第2行至第8行的7个单元格作为下拉框的内容
        String formula = "typelist!$" + word + "$2:$" + word + "$" + (selectList.length + 1);
        if(isjilian){
            // 名称管理器设置name
            Name naturalName = workbook.createName();
            naturalName.setNameName(columnDesc);// setNameName方法中参数必须父级内容一样
            naturalName.setRefersToFormula(formula);

            // 证件类型和股东类型为级联关系，这里取mainSheet页中 股东类型 所在列的下标并转换为字母，因为 证件类型 取决于 股东类型，最后面的2代表excel中第多少行
            //当前列为子级下拉框的内容受父级哪一列的影响
            String indirectFormula = "INDIRECT($" + father + "2)";//=INDIRECT($C2)
            mainSheet.addValidationData(SetDataValidation(workbook, indirectFormula, 1, mainParentIndex, 1, mainParentIndex));
        } else {
            // 设置下拉列表值绑定到主sheet页具体哪个单元格起作用
            mainSheet.addValidationData(SetDataValidation(workbook, formula, 1, mainParentIndex, 1, mainParentIndex));
        }
    }

    /**
     * 设置下拉列表值绑定到主sheet页具体哪个单元格起作用
     * @Title: SetDataValidation
     * @author hegg
     * @date 2019年12月30日 下午5:03:37
     * @return 返回类型 DataValidation
     * @param
     */
    public static DataValidation SetDataValidation(Workbook workbook, String strFormula, int firstRow, int firstCol, int endRow, int endCol) {
        // 表示A列1-59行作为下拉列表来源数据
        // String formula = "typelist!$A$1:$A$59" ;
        // 原顺序为 起始行 起始列 终止行 终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)workbook.getSheet("typelist"));
        DataValidationConstraint formulaListConstraint = dvHelper.createFormulaListConstraint(strFormula);
        DataValidation dataValidation = dvHelper.createValidation(formulaListConstraint, regions);

        return dataValidation;
    }

    /**
     * 将下标转换为字母，调用该方法时必须+1
     * @Title: getIndexWord
     * @author hegg
     * @date 2020年1月1日 下午8:59:14
     * @return 返回类型 String
     * @param
     */
    public static String getIndexWord(Integer i){
        String[] word = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int d = i/26;
        int x = i%26;
        String w = "";
        if(x == 0){
            if(d>1){
                w= word[d-2] + "" + word[25];
            } else {
                w = word[i-1];
            }
        } else {
            if(d>=1){
                w= word[d-1] + "" + word[x-1];
            } else {
                w = word[x-1];
            }
        }

        Map<Integer, String> a = new LinkedHashMap<Integer, String>();
        a.put(i, w);
        return a.get(i);
    }

}
