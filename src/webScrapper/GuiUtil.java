/**
 * 
 */
package webScrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitAudioContext;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author 608619925 
 * Sourav De 
 * 12 Jan 2017
 *
 */
public class GuiUtil extends Application {

	/**
	 * @param args
	 */
    static WebDriver AmazonDriver,GoogleDriver;
    @Override
    public void start(Stage stage) {

        initUI(stage);
    }
    
    private void initUI(Stage stage) {
   	    File file1 = new File("phantomjs.exe");				
        System.setProperty("phantomjs.binary.path", file1.getAbsolutePath());		
        AmazonDriver = new PhantomJSDriver();
        GoogleDriver = new PhantomJSDriver();
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(8));
        
//        ColumnConstraints cons1 = new ColumnConstraints();
//        cons1.setHgrow(Priority.NEVER);
//        root.getColumnConstraints().add(cons1);
//
//        ColumnConstraints cons2 = new ColumnConstraints();
//        cons2.setHgrow(Priority.ALWAYS);
//        
//        root.getColumnConstraints().addAll(cons1, cons2);
//        
//        RowConstraints rcons1 = new RowConstraints();
//        rcons1.setVgrow(Priority.NEVER);
//        
//        RowConstraints rcons2 = new RowConstraints();
//        rcons2.setVgrow(Priority.ALWAYS);  
//        
//        root.getRowConstraints().addAll(rcons1, rcons2);
        Alert aw = new Alert(AlertType.ERROR);
        Label sts = new Label();
        Label lbl = new Label("Select File Location:");
        TextField field = new TextField();
        field.setEditable(false);
        
        ProgressBar pb = new ProgressBar();
        pb.setVisible(false);
        
        Button okBtn = new Button("Run!");
        Button selectFile = new Button("...");

        GridPane.setHalignment(okBtn, HPos.CENTER);
        GridPane.setHalignment(selectFile,HPos.RIGHT);
        root.add(lbl, 0, 0);
        root.add(field, 1, 0, 3, 1);
        root.add(selectFile, 1, 0,3,1);
        root.add(okBtn, 2, 2);
        
        root.add(pb,2,3 );
        root.add(sts, 0,3);
        //root.add(selectFile, 3, 3);
        
        Scene scene = new Scene(root,350, 150);

        stage.setTitle("Web Scrapper Utility");
        stage.setScene(scene);
        stage.show();
    
        selectFile.setOnAction(e->{
        	String userDirectoryString = Paths.get(".").toAbsolutePath().normalize().toString();//System.getProperty("user.dir");
        	File userDirectory = new File(userDirectoryString);
        	if(!userDirectory.canRead()) {
        	    userDirectory = new File("c:/");
        	}
           FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".XLSX files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show save file dialog
            File file = fileChooser.showOpenDialog(stage);
            
            if(file != null){
                //SaveFile(Santa_Claus_Is_Coming_To_Town, file);
            	field.setText(file.getAbsolutePath());
            	//ds.pjsFilepath=file.getAbsolutePath();
            }
        });
        
        okBtn.setOnAction(e->{
        	if (field.getText()==null||field.getText().isEmpty()){
        		//aw.setAlertType(AlertType.ERROR);
        		aw.setContentText("No Input file selected! Select an Excel file.");
        		aw.showAndWait();
        	}
        	try {
        	
        	pb.setProgress(0);
    		String excelFilePath = field.getText();
   		 	FileInputStream inputStream;
			
			inputStream = new FileInputStream(new File(excelFilePath));
			
   		 	Workbook workbook = ExcelUtil.getWorkbook(inputStream, excelFilePath);
    	    org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
    	    
//    	    File file1 = new File("phantomjs.exe");				
//            System.setProperty("phantomjs.binary.path", file1.getAbsolutePath());		
//            WebDriver driver = new PhantomJSDriver();
            int n = firstSheet.getPhysicalNumberOfRows();
            String[] azList =new String[n];
            String[] gsList =new String[n];
            String[] azPrice =new String[n];
            String[] gsPrice =new String[n];
            
    	    for (int i =1;i<n;i++){
    	    	Row row = firstSheet.getRow(i);
    	    	if(row!=null){ 
    	    	//System.out.println(firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("A")));
    	    	azList[i] = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("A")).toString();
    	    	gsList[i] = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("B")).toString();
    	    	System.out.println(azList[i]+gsList[i]);
//    	    	Cell cell = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("C"));
//    	    	
//    	    	if(cell == null){
//    	    	    cell = firstSheet.getRow(i).createCell(CellReference.convertColStringToIndex("C"));
//    	    	}
    	    	//ScrapperUtil1.fetchAmazon(driver, searchStr);
    	    	//cell.setCellValue(ScrapperUtil.fetchAmazon(driver, searchStr));

    	    	
    	    		}
    	    	}
			Thread fetchAmazonTask = new Thread(new Runnable()  {
			    @Override
			    public void run() {
			    	
			//int j=0;    
    	    for (int i=1;i<n-1;i++){
    	    	final float counter = i*1.0f;
    	    	new Thread(new Runnable() {
                	
                    @Override public void run() {
                    
                        
                        javafx.application.Platform.runLater(new Runnable() {
                            @Override public void run() {
                            	Double p=(double)(counter/(n-1));
                            	pb.setVisible(true);
                                pb.setProgress(p);
                                sts.setText("Fetching data "+(int)counter+" of "+(n-1));
                                if (p>=1){
                                	sts.setText("Complete!");
                                	
                                }
                            }
                        });
                    
                    	}
                    }).start();
//    	    	Row row = firstSheet.getRow(i);
//    	    	if(row!=null){
//    	    	Cell cell = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("C"));
//    	    	//Cell cell1 = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("D"));
//    	    	if(cell == null){
//    	    	    cell = firstSheet.getRow(i).createCell(CellReference.convertColStringToIndex("C"));
//    	    	    //cell1 = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("D"));
//    	    	}
//    	    	cell.setCellValue(ScrapperUtil1.fetchAmazon(AmazonDriver, azList[i]));
//    	    	//cell1.setCellValue(ScrapperUtil1.fetchGoogleAds(GoogleDriver, gsList[j]));
//    	    	}
    	    	azPrice[i]=ScrapperUtil1.fetchAmazon(AmazonDriver, azList[i]);
    	    	gsPrice[i]=ScrapperUtil1.fetchGoogleAds(GoogleDriver, gsList[i]);
    	    	System.out.println(i+" "+azPrice[i]+" "+gsPrice[i]);
    	    }
    	    try{
    	    	
    	    
    	   // workbook.close();
    	    for (int i=1;i<n-1;i++){
    	    	Row row = firstSheet.getRow(i);
    	    	if(row!=null){
    	    	Cell cell = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("C"));
    	    	Cell cell1 = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("D"));
    	    	if(cell == null){
    	    	    cell = firstSheet.getRow(i).createCell(CellReference.convertColStringToIndex("C"));
    	    	    //cell1 = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("D"));
    	    	}
    	    	if(cell1 == null){
    	    		cell1 = firstSheet.getRow(i).getCell(CellReference.convertColStringToIndex("D"));
    	    	}
    	    	cell.setCellValue(azPrice[i]);
    	    	cell1.setCellValue(gsPrice[i]);
    	    	}	
    	    }
    	    inputStream.close();		
    	    
    	    FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
    	    workbook.write(outputStream);
    	    outputStream.close();
    	    workbook.close();
    	    }catch(IOException e){
    	    	e.printStackTrace();
    	    }
    	    
			}
			});
			fetchAmazonTask.start();
			
    	    
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
    }
        	
	public void stop(){
		AmazonDriver.close();
		AmazonDriver.quit();
		GoogleDriver.close();
		GoogleDriver.quit();
		
	    System.out.println("Driver is closing");
	    
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
