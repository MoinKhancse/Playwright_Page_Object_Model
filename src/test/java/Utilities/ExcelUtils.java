package Utilities;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static String email;
	public static String password;
	
	@SuppressWarnings({"resource","unused"})
	public static void readExcel() throws IOException {
		String excelFilePath =".\\testData\\dataTest.xlsx";
		File file = new File(excelFilePath);
		System.out.println(file.getAbsolutePath());
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		
		for(int r=1; r<=rows; r++) {
			XSSFRow row = sheet.getRow(r);
			for(int c=0; c < 1; c++) {
				XSSFCell cell= row.getCell(c);
				
				row=sheet.getRow(r);
				cell = row.getCell(c + 0);
				email=cell.getStringCellValue();
				System.out.println(email);
				
				cell = row.getCell(c+1);
				password=cell.getStringCellValue();
				System.out.println(password);
				
			}
		}
		
	}
//	public void writeExcelData(String email, String password) throws IOException {
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet("UserInfo");
//		
//		Object data[][] = {{"Email", "Password"},{email,password}};
//		
//		int rows = data.length;
//		int cols = data[0].length;
//		
//		for(int r=0; r < rows; r++) {
//			XSSFRow row = sheet.createRow(r);
//			for(int c=0; c < cols; c++) {
//				XSSFCell cell= row.createCell(c);
//				Object value = data[r][c];
//				
//				if(value instanceof String) {
//					cell.setCellValue((String)value);
//				}
//				if(value instanceof Integer) {
//					cell.setCellValue((Integer)value);
//				}
//				if(value instanceof Double) {
//					cell.setCellValue((Double)value);
//				}
//				if(value instanceof Boolean) {
//					cell.setCellValue((Boolean)value);
//				}
//				
//				String filePath =".\\testData\\data.xlsx";
//				FileOutputStream outputStream = new FileOutputStream(filePath);
//				workbook.write(outputStream);
//				outputStream.close();
//				System.out.println("Successfully Write data on excel sheet");
//			}
//		}
//		
//	}

}
