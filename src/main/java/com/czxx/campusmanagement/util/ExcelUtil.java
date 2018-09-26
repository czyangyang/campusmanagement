package com.czxx.campusmanagement.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
 
/**
 * Excel工具类
 *
 */
public class ExcelUtil {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * 获得path的后缀名
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path){
		if(path==null || EMPTY.equals(path.trim())){
			return EMPTY;
		}
		if(path.contains(POINT)){
			return path.substring(path.lastIndexOf(POINT)+1,path.length());
		}
		return EMPTY;
	}
	/**
	 * 单元格格式
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public static String getHValue(HSSFCell hssfCell){
		 if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			 return String.valueOf(hssfCell.getBooleanCellValue());
		 } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			 String cellValue = "";
			 if(HSSFDateUtil.isCellDateFormatted(hssfCell)){				
				 Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
				 cellValue = sdf.format(date);
			 }else{
				 DecimalFormat df = new DecimalFormat("#.##");
				 cellValue = df.format(hssfCell.getNumericCellValue());
				 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
				 if(strArr.equals("00")){
					 cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				 }	
			 }
			 return cellValue;
		 } else {
			return String.valueOf(hssfCell.getStringCellValue());
		 }
	}
	/**
	 * 单元格格式
	 * @param xssfCell
	 * @return
	 */
	public static String getXValue(XSSFCell xssfCell){
		 if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			 return String.valueOf(xssfCell.getBooleanCellValue());
		 } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			 String cellValue = "";
			 if(XSSFDateUtil.isCellDateFormatted(xssfCell)){
				 Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
				 cellValue = sdf.format(date);
			 }else{
				 DecimalFormat df = new DecimalFormat("#.##");
				 cellValue = df.format(xssfCell.getNumericCellValue());
				 String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
				 if(strArr.equals("00")){
					 cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				 }	
			 }
			 return cellValue;
		 } else {
			return String.valueOf(xssfCell.getStringCellValue());
		 }
	}	
	
	/**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }
}

