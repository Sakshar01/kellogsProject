package kellogsTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDataDriven {
	// Identify Test Case column by scanning all the row
	// Once you have find test case scan that column and find purchase category test
	// case
	// Scan that row and take out the data and feed into test
//	FileInputStream excelFileObject = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/LocalesList_URLs.xlsx");
//	XSSFWorkbook workBookObject = new XSSFWorkbook(excelFileObject);
//	XSSFSheet sheet = workBookObject.getSheetAt(i);
	boolean row ;
	// TODO Auto-generated method stub
	/*
	 * Workbook object is important to access the file so once you create the object
	 * pass file object as the argument
	 */
	public ArrayList<String> getData(String testCaseName) throws IOException {
		System.out.println("Inaisew");
		ArrayList<String> array = new ArrayList<String>();
		FileInputStream excelFileObject = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/LocalesList_URLs.xlsx");
		XSSFWorkbook workBookObject = new XSSFWorkbook(excelFileObject);
		int numberOfExcelSheets = workBookObject.getNumberOfSheets();
//		System.out.println(numberOfExcelSheets);
		Iterator<Row> row;
		for (int i = 0; i < numberOfExcelSheets; i++) {
			if (workBookObject.getSheetName(i).equalsIgnoreCase("firstSheet")) {
				XSSFSheet sheet = workBookObject.getSheetAt(i);
				row = sheet.iterator(); // Sheet is collection of all rows
				Row firstRow = row.next();
				Iterator<Cell> cell = firstRow.cellIterator();// row is collection of cells
				int column = 0, k = 0;
				/*while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
						System.out.println("value of K " +k);
						column = k;
					}	
					k++;
				}*/
//				System.out.println("through this "+column);
				Row r = row.next();
				int flagFound=0;
				while (row.hasNext() && flagFound ==0) {
					flagFound =0;
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						System.out.println("TestCase name" +testCaseName);
						flagFound = 1;
						Iterator<Cell> cellIndex = r.cellIterator();
						while (cellIndex.hasNext()) {
							Cell cellIndexContent = cellIndex.next();
							if (cellIndexContent.getCellType() == CellType.STRING) {
								array.add(cellIndexContent.getStringCellValue());
							} else {
								array.add(NumberToTextConverter.toText(cellIndexContent.getNumericCellValue()));
							}
						}
					}
					r = row.next();
				}
			}

		}
		return array;
	}
}
