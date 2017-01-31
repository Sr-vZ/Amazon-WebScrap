/**
 * 
 */
package webScrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;


/**
 * @author 608619925 
 * Sourav De 
 * 11 Jan 2017
 *
 */

public class ScrapperUtil1 {

	/**
	 * @param args
	 */
	public static String fetchAmazon(WebDriver d,String searchStr ){
        d.get("http://camelcamelcamel.com/product/"+searchStr);
        WebElement element=null;
        Boolean isPresent = d.findElements(By.xpath(".//*[@id='content']/div[1]/div[3]/p/span[1]/span")).size() > 0;
        if(isPresent){
        	element = d.findElement(By.xpath(".//*[@id='content']/div[1]/div[3]/p/span[2]"));
        	if (element.getText().contains("Amazon")){
        		element = d.findElement(By.xpath(".//*[@id='content']/div[1]/div[3]/p/span[1]/span"));
        	}else{
        		return "Not in Stock";        		
        	}
//        	element = d.findElement(By.xpath(".//*[@id='content']/div[1]/div[3]/p/span[1]/span"));	
        }else if(d.findElements(By.className("green")).size()>0){
        	element=d.findElement(By.className("green"));
        	//return "";
        }else{
        	return "Not in Stock";
        }            			
        //System.out.println("Page URL is: " + d.getCurrentUrl());
        return element.getText();
	}
	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand=new Random();
	    
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public static String fetchGoogleShopping(WebDriver d, String searchStr,boolean flag )
	{
		try{
//		File file = new File("phantomjs.exe");				
//        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
//        WebDriver d = new PhantomJSDriver();
//		String service = "C:\\Users\\608619925\\Desktop\\Misc Projects\\testBench\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
//		System.setProperty("webdriver.ie.driver", service);
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//		InternetExplorerDriver  ieDriver = new InternetExplorerDriver(capabilities);
//		ieDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		d=ieDriver;
		String[] uaList={"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
		               	 "Mozilla/5.0 (compatible; MSIE 9.0; AOL 9.7; AOLBuild 4343.19; Windows NT 6.1; WOW64; Trident/5.0; FunWebProducts)",
		                 "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
		                 "Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16",
		                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A",
		                 "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201",
		                 "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
		                 "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
		                 "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)",
		                 "Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))",
		                 "Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 6.0; en-US)",
		                 "Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)",
		                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A",
		                 "Mozilla/5.0 (Windows NT 5.2; RW; rv:7.0a1) Gecko/20091211 SeaMonkey/9.23a1pre",
		                 "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; Avant Browser; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)"};
		
		String userAgent = uaList[randInt(0, uaList.length-1)];
		System.setProperty("phantomjs.page.settings.userAgent", userAgent);
		
		//https://www.google.co.uk/search?sclient=psy-ab&site=&source=hp&btnG=Search&tbm=shop&q=Dewalt+DCD771C2
//        d.get("https://www.google.com/?q="+searchStr);
//        d.get("https://www.google.com/search?sclient=psy-ab&site=&source=hp&btnG=Search&tbm=shop&q="+searchStr.replaceAll(" ", "+"));
        d.get("https://www.google.com/search?hl=en&output=search&gws_rd=ssl&tbs=vw:l&tbm=shop&q="+searchStr.replaceAll(" ", "+"));
        //d.findElement(By.xpath(".//[@title='Search'")).sendKeys(searchStr);
        //d.switchTo().window(null);
//		WebDriverWait wait = new WebDriverWait(d, 30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
        //d.findElement(By.id("q")).sendKeys(Keys.ENTER);
        
		if (flag){
	        try {
				TimeUnit.SECONDS.sleep(randInt(10, 100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Document doc = Jsoup.parse(d.getPageSource());
        String res= doc.body().text();
        //d.findElement(By.id("tsf")).submit();
        //*[@id='rhs_block']/table/tbody/tr/td/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/div/div[2]/b
        //*[@id='tvcap']/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/b
//        System.out.println(d.getPageSource().toString());
//        WebElement element=null;
//        Boolean isPresent = false;//d.findElements(By.xpath("div.sh-sr__shop-result-group._G2d > div.sh-pr__product-results > div._ARe.psli > div.pslicont > div.pslmain > div.pslline > div._tyb.shop__secondary > span.price > b")).size() > 0;
//        //d.wait(randInt(1000, 10000));
//        if(isPresent){
//        element = d.findElement(By.cssSelector("div.sh-sr__shop-result-group._G2d > div.sh-pr__product-results > div._ARe.psli > div.pslicont > div.pslmain > div.pslline > div._tyb.shop__secondary > span.price > b"));	
////        }else if(d.findElements(By.xpath("//*[@id='tvcap']/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/b")).size()>0){
////        	element=d.findElement(By.xpath("//*[@id='tvcap']/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/b"));
////        	//return "";
//        }else{
//        	//System.out.println("Page URL is: " + d.getPageSource().toString());
////        	d.close();
////	        d.quit();
//        	return "Not Found";
//        }            			
//        System.out.println("Page URL is: " + d.manage().ime().getActiveEngine());
//        d.close();
//        d.quit();
        System.out.println(res);
        res=res.replaceAll(",", "");
        Pattern p = Pattern.compile("(\\$).\\d*\\.\\d{2}");
        String[] priceList = new String[20];
        int i=0;
		if (res.contains("sponsored")){
			
			res = res.substring(res.indexOf("sponsored"));
			Matcher m = p.matcher(res);
			while (m.find()) {
			    res = m.group(0);
				priceList[i]=m.group(0);
			    // s now contains "BAR"
				
			    break;
//			    i++;
			}
//				if(i>1){
//					//res=priceList[1];
//					if (res.contains(priceList[0]+" from")){
//						res=priceList[1];
//					}else{
//						res=priceList[0];
//					}
//				}else{
//					res=priceList[0];
//				}
				
			return res;
        }else{
        	return "Not Found!";
        }
		
		}catch(NullPointerException | StringIndexOutOfBoundsException e){
			return "Invalid search term!";
		}
        
	}
	public static void waitForFrame(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("master-1")));
	}

	 public static void checkPageIsReady(WebDriver d) {

		  JavascriptExecutor js = (JavascriptExecutor) d;


		  //Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   System.out.println("Page Is loaded.");
		   return; 
		  } 

		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  //You can replace your value with 25 If you wants to Increase or decrease wait time.
		  for (int i=0; i<30; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return _googCsaShowAfdSurvey").toString().equals("1")){ 
		    break; 
		   }   
		  }
		 }
	public static String fetchGoogleAds(WebDriver d, String searchStr, boolean flag)
	{
	try{
		
	
//		File file = new File("phantomjs.exe");				
//        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
//        WebDriver d = new PhantomJSDriver();
//		String service = "C:\\Users\\608619925\\Desktop\\Misc Projects\\testBench\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
//		System.setProperty("webdriver.ie.driver", service);
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability("ignoreZoomSetting", true);
//		capabilities.setCapability("nativeEvents",false);
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//		
//		InternetExplorerDriver  ieDriver = new InternetExplorerDriver(capabilities);
//		ieDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		d=ieDriver;
		String[] uaList={"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
		               	 "Mozilla/5.0 (compatible; MSIE 9.0; AOL 9.7; AOLBuild 4343.19; Windows NT 6.1; WOW64; Trident/5.0; FunWebProducts)",
		                 "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
		                 "Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16",
		                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A",
		                 "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201",
		                 "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
		                 "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
		                 "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)",
		                 "Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))",
		                 "Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 6.0; en-US)",
		                 "Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)",
		                 "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A",
		                 "Mozilla/5.0 (Windows NT 5.2; RW; rv:7.0a1) Gecko/20091211 SeaMonkey/9.23a1pre",
		                 "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; Avant Browser; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)"};
		
		String userAgent = uaList[randInt(0, uaList.length-1)];
		System.setProperty("phantomjs.page.settings.userAgent", userAgent);
//		File file = new File("gstest.html");
		//d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//"https://google-developers.appspot.com/adsense-for-shopping/docs/sample"
		if (searchStr==null||searchStr.length()==0){
			return "Search term is Blank!";
		}
		d.get("https://www.google.co.in/#q="+searchStr.replaceAll(" ", "+"));
//		checkPageIsReady(d);
//		synchronized (d){
//			d.wait(5);
//		}
		if (flag){
	        try {
				TimeUnit.SECONDS.sleep(randInt(5,15));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Document doc = Jsoup.parse(d.getPageSource());
//		String res = d.getPageSource().toString().replaceAll("\\<.*?>","");
		String res = doc.text().toLowerCase();
		//res.substring(arg0)
		//waitForFrame(d);
		//checkPageIsReady(d);
		//html/body/div/div[2]/div[1]/div/div[2]/div[1]/span
//		((JavascriptExecutor) d).executeAsyncScript("pageOptions.query='dewalt';");
//		((JavascriptExecutor) d).executeAsyncScript("_googCsa('plas',pageOptions,afshblock);");
		//d.switchTo().frame("master-1");
		//d.wait(100);
		//html/body/div/div[2]/div[1]/div/div[2]/div[1]/span
		//html body div#adBlock div div#e1.qc_.c_ div.tc_ div#e5 div.vc_ span.i_
		//System.out.println(d.findElement(By.id("master-1")).getText());
		//WebElement ele = d.findElement(By.xpath("//*[@id='tvcap']/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/b"));
		Pattern p = Pattern.compile("(\\$|\\£|\\₹).\\d*\\.\\d{2}");
		//System.out.println(res);
		String subres="";
		if (res.contains("sponsor")){
			
			subres = res.substring(res.indexOf("sponsor"));
			subres=subres.replaceAll(",", "");
			Matcher m = p.matcher(subres);
			while (m.find()) {
			    subres = m.group(0);
			    // s now contains "BAR"
			}
//			res = res.substring(res.indexOf(searchStr), res.indexOf(".00")+3).replaceAll(searchStr, "").replaceAll("[^\\d.,]", "");
//			res=res.replaceAll("[.*\\d]", "");
			return subres;
		}
		else{
			res= res.replaceAll(",", "");
			Matcher m = p.matcher(res);
			while (m.find()) {
			    subres = m.group(0);
			    // s now contains "BAR"
			}
			if (subres.length()==0){
				return "Not Found!";
			}else{
				return subres;
			}
			
		}
		}catch(NullPointerException|StringIndexOutOfBoundsException e){
			e.printStackTrace();
			return "Invalid Search term";
		}
	}
//	public static String fetchAdsHU(String searchStr) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
//		WebClient client = new WebClient();
//		HtmlPage page = client.getPage("https://google-developers.appspot.com/adsense-for-shopping/docs/sample");
//		System.out.println(page.toString());
//		return searchStr;
//	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// fetch the document over HTTP
//		try {
//
//		      }
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        File file = new File("phantomjs.exe");				
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        
        String userAgent = "Mozilla/5.0 (Linux; U; Android 2.3.3; en-us; LG-LU3000 Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent", userAgent);

        WebDriver driver = new PhantomJSDriver(caps);
        
		//String service = "C:\\Users\\608619925\\Desktop\\Misc Projects\\testBench\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";
		

		//WebDriver Initialization
//		System.setProperty("webdriver.ie.driver", service);
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//		InternetExplorerDriver  ieDriver = new InternetExplorerDriver(capabilities);
//        ieDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        String s=fetchAmazon(driver, "B00302KB5O");
        System.out.println(s);
        s=fetchGoogleAds(driver,"honey can do KCH-06145",false);
        System.out.println(s);
        s=fetchGoogleShopping(driver,"Tingley Rubber O56007",false);
        System.out.println(s);
        
        driver.close();
        driver.quit();
//        ieDriver.close();
	}
}
