/**
 * 
 */
package amazonWebScrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author SoURaV
 *
 */
public class ExcelUtils {

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
		String excelFilePath = "Scrub Retail Data - Template 2017.xlsx"; // can be .xls or .xlsx
		 
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
 
		Workbook workbook = getWorkbook(inputStream, excelFilePath);
	    org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
//	    while (iterator.hasNext()) {
//	        Row nextRow = iterator.next();
//	        Iterator<Cell> cellIterator = nextRow.cellIterator();
//	        //Cell nextCell = cellIterator.next();
//	        
//	        while (cellIterator.hasNext()) {
//	            Cell nextCell = cellIterator.next();
//	            int columnIndex = nextCell.getColumnIndex();
//	            System.out.print(getCellValue(nextCell)); 
//	             
//	        }
//	        
//	    }
	    for (int i =0;i<firstSheet.getPhysicalNumberOfRows();i++){
	    	System.out.println(firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("A")));
	    }
	 
	    workbook.close();
	    inputStream.close();		

	}

}
