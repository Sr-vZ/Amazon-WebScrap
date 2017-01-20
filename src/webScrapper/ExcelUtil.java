/**
 * 
 */
package webScrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @author 608619925 
 * Sourav De 
 * 11 Jan 2017
 *
 */
public class ExcelUtil {

	/**
	 * @param args
	 */
	public static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	@SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

        File file = new File("C:/Users/608619925/Downloads/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe");				
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
        WebDriver driver = new PhantomJSDriver();
		String excelFilePath = "Scrub Retail Data - Template 2017.xlsx"; // can be .xls or .xlsx
 
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
 
		Workbook workbook = getWorkbook(inputStream, excelFilePath);
	    Sheet firstSheet = workbook.getSheetAt(0);
//	    Iterator<Row> iterator = firstSheet.iterator();
//	    while (iterator.hasNext()) {
//	        Row nextRow = iterator.next();
//	        Iterator<Cell> cellIterator = nextRow.cellIterator();
//	        Cell nextCell = cellIterator.next();
//	        System.out.println(getCellValue(nextCell)); 
//	        while (cellIterator.hasNext()) {
//	            Cell nextCell = cellIterator.next();
//	            int columnIndex = nextCell.getColumnIndex();
//	            
//	             
//	        }
	    for (int i =1;i<firstSheet.getPhysicalNumberOfRows();i++){
	    	String searchStr =firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("A")).toString();
	    	System.out.println(firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("A")));
	    	Cell cell = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("C"));
	    	//c.setCellValue("test");
	    	if(cell == null){
	    	    cell = firstSheet.getRow(i).createCell(CellReference.convertColStringToIndex("C"));
	    	}
	    	//ScrapperUtil1.fetchAmazon(driver, searchStr);
	    	cell.setCellValue(ScrapperUtil1.fetchAmazon(driver, searchStr));
	    }
	    
	    //workbook.close();
	    inputStream.close();

	    FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
	    workbook.write(outputStream);
	    outputStream.close();
	    workbook.close();
	    

	}

}
