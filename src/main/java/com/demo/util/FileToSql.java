package com.demo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * toSql脚本----两种方式
 * @author Administrator
 *
 */
public class FileToSql {

	/*public static void main(String[] args) throws IOException {
		String filePath = "C:/Users/Administrator/Desktop/test";
		String sqlPath = "C:/Users/Administrator/Desktop/test/test.sql";
		File file = new File(sqlPath);
		Writer out = new BufferedWriter(new FileWriter(file));
		List<String> all = getAll(filePath);
		for (String sql : all) {
			out.write(sql+"\n");
		}
		out.close();
	}
	
	//读数据
	public static List<String> getAll(String file) throws IOException{
		List<String> list = new ArrayList<String>();
		String str = "";
		File f = new File(file);
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file2 = files[i];
			if(file2.isFile()) {
				FileInputStream input = new FileInputStream(file2);
				Workbook wb = null;
				try {
					wb = new HSSFWorkbook(input);
				}catch (Exception e) {
					input = new FileInputStream(file2);
					wb = new XSSFWorkbook();
				}
				//获取sheet对象
				Sheet sheet = wb.getSheetAt(0);
				//总行数
				int rows = sheet.getPhysicalNumberOfRows();
				//最后一行
				int lastRowNum = sheet.getLastRowNum();
				//总列数
				int cells = sheet.getRow(0).getPhysicalNumberOfCells();
				//循环工作表
				for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
					Sheet at = wb.getSheetAt(i);
					if(at == null) {
						continue;
					}
					//循环行row,一般都从第二行开始取数据
					for (int j = 1; j < at.getPhysicalNumberOfRows(); j++) {
						Row row = at.getRow(j);
						if(row == null) {
							continue;
						}
						String id = getCellValue(row.getCell(0));
						String name = getCellValue(row.getCell(1));
						String age = getCellValue(row.getCell(2));
						String sex = getCellValue(row.getCell(3));
						String address = getCellValue(row.getCell(4));
						String txt = getCellValue(row.getCell(5));
						str = "insert into user values('"+id+"','"+name+"','"+age+"','"+sex+"','"
								+address+"','"+txt+"');";
						list.add(str);
					}
				}
			}
		}
		return list;
	}
	
	//匹配类型获取对应值
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if(cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//判断是否日期格式
				if(DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					cellValue = format.format(date);
				}else {
					cellValue = String.valueOf((int)cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:{
				try {
					String.valueOf(cell.getNumericCellValue());
				}catch (Exception e) {
					cellValue = String.valueOf(cell.getRichStringCellValue());
				}
			}
			break;
			case Cell.CELL_TYPE_ERROR:{
				try {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}catch (Exception e) {
					cellValue = String.valueOf(cell.getErrorCellValue());
				}
			}
			break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}*/
	
	private Sheet sheet;           //表格类实例
	private LinkedList[] list;     //保存每个单元格的数据，用的是链表结构的数组
	//读取文件，创建表格
	public void loadExcel(String file) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(file));
			Workbook wb = WorkbookFactory.create(inputStream);
			sheet = wb.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//获取单元格的值
	public String getCellValue(Cell cell) {
		String cellValue = "";
		if(cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				//判断是否日期格式
				if(DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					cellValue = format.format(date);
				}else {
					cellValue = String.valueOf((int)cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:{
				try {
					String.valueOf(cell.getNumericCellValue());
				}catch (Exception e) {
					cellValue = String.valueOf(cell.getRichStringCellValue());
				}
			}
			break;
			case Cell.CELL_TYPE_ERROR:{
				try {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}catch (Exception e) {
					cellValue = String.valueOf(cell.getErrorCellValue());
				}
			}
			break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}
	//获取每个单元格的值
	public void init() {
		//每行数据
		int rows = sheet.getPhysicalNumberOfRows();
		list = new LinkedList[rows];
		for (int i = 0; i < rows; i++) {
			Row row = sheet.getRow(i);
			//每行数据再创建一个新的LinkedList对象
			list[i] = new LinkedList();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//获取单元格的值
				String value = getCellValue(row.getCell(j));
				//将每个值按顺序放入
				list[i].add(value);
			}
		}
	}
	//输出
	public void show(String sql) throws IOException {
		File file = new File(sql);
		Writer out = new BufferedWriter(new FileWriter(file));
		for (int i = 1; i < list.length; i++) {
			out.write("insert into user values(");
			for (int j = 0; j < list[i].size(); j++) {
				if(j<list[i].size()-1) {
					out.write("'"+list[i].get(j)+"',");
				}else {
					out.write("'"+list[i].get(j)+"'");
				}
			}
			out.write(");\n");
		}
		out.close();
	}
	public static void main(String[] args) throws IOException {
		FileToSql toSql = new FileToSql();
		toSql.loadExcel("C:/Users/Administrator/Desktop/test/test.xls");
		toSql.init();
		toSql.show("C:/Users/Administrator/Desktop/test/test.sql");
	}

}
