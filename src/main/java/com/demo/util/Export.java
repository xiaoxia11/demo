package com.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.NumberUtils;

import com.demo.bean.UserDomain;

/**
 * 导出Excel表格
 * @author Administrator
 *
 */
public class Export {
	
	public static final String[] keys = {"id","name","age","sex","txt"};
	public static final String[] value = {"编号","姓名","年龄","性别","备注"};
	
	/**
	 * 获取数据对应表格
	 * @param list
	 * @return
	 */
	public static List<Map<String,Object>> creatExcel(List<UserDomain>list){
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sheetName", "人员清单");
		String[] key = keys;
		listMap.add(map);		
		for (UserDomain user : list) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put(key[0], user.getId());
			m.put(key[1], user.getName());
			m.put(key[2], user.getAge());
			m.put(key[3], user.getSex());
			m.put(key[4], user.getTxt());
			listMap.add(m);
		}
		return listMap;
	}
	
	public static void creatExcelRecord(HttpServletRequest request,HttpServletResponse response,
			String[] columnNames,String[] keys,List<Map<String,Object>>list,String fileName) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		creatWorkbook(list, keys, columnNames).write(os);
		exportCommon(request, response, fileName, os);
	}
	
	/**
	 * 设置表格样式
	 * @param list
	 * @param keys
	 * @param columnNames
	 * @return
	 */
	public static Workbook creatWorkbook(List<Map<String,Object>>list,String[] keys,String[] columnNames) {
		//创建Excel表格
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		//列宽
		int columnWidth = 5300;
		//设置列宽列数：宽度（像素）
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth(i, columnWidth);
		}
		//设置冻结列
		sheet.createFreezePane(1, 1);
		//创建第一行、列名称
		Row row = sheet.createRow((short)0);
		row.setHeight((short)550);
		//设置列名单元格样式
		CellStyle style = getHeadStyle(wb);
		//设置内容单元格样式
		CellStyle style2 = getContentStyle(wb);		
		//设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(style);
		}
		//设置每行每列值
		for (short i = 1; i < list.size(); i++) {
			//row行，cell方格，row和cell都是从0开始
			Row createRow = sheet.createRow(i);
			//在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = createRow.createCell(j);
				String value = list.get(i).get(keys[j]) == null ? "" : 
					list.get(i).get(keys[j]).toString();
				cell.setCellValue(value);
				if(value.length()>100) {
					sheet.setColumnWidth(j, (columnWidth * 2));
				}
				cell.setCellStyle(style2);
			}
		}
		return wb;
	}
	
	/**
	 * 导出
	 * @throws Exception 
	 */
	public static void exportCommon(HttpServletRequest request,HttpServletResponse response,
			String fileName,ByteArrayOutputStream os) throws Exception {
		final String userAgent = request.getHeader("USER-AGENT");
		if(userAgent.toLowerCase().indexOf("firefox")>0) {
			fileName = new String(fileName.getBytes(),"ISO-8859-1");			
		}else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		byte[] content = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(content);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String((fileName+".et")));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out);
			byte[]b = new byte[2048];
			int bytesRead = 0;
			while (-1 !=(bytesRead = bis.read(b,0,b.length))) {
				bos.write(b,0,bytesRead);
			}
		} catch (final Exception e) {
			throw e;
		} finally {
			if(bis != null) {
				bis.close();
			}
			if(bos != null) {
				bos.close();
			}
		}
	}
	
	/**
	 * 设置列名单元格样式
	 * 
	 */
	public static CellStyle getHeadStyle (Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font f = wb.createFont();		
		f.setFontHeightInPoints((short)12);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(f);
		//设置单元格边框
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		//设置单元格内容显示不下时，自动换行
		style.setWrapText(true);
		//设置单元格垂直居中对齐
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//设置背景颜色
		style.setFillForegroundColor(NumberUtils.convertNumberToTargetClass
				(IndexedColors.GREY_25_PERCENT.getIndex(), Short.class));
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}
	
	/**
	 * 设置内容单元格样式
	 * 
	 */
	public  static CellStyle getContentStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font f = wb.createFont();
		f.setFontHeightInPoints((short)10);
		f.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(f);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		return style;
	}

}
