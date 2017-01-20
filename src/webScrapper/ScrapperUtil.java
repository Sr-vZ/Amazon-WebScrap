/**
 * 
 */
package webScrapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author 608619925 
 * Sourav De 
 * 11 Jan 2017
 *
 */
public class ScrapperUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String searchQuery = "B006LXOJC0";
		WebClient client = new WebClient();
		
		//ProxyConfig proxyConfig = new ProxyConfig("124.88.67.10",843);
		//client.getOptions().setProxyConfig(proxyConfig);

		  
		//client.getOptions().setCssEnabled(false);  
		//client.getOptions().setJavaScriptEnabled(false);  
		try {  
		  //String searchUrl = "https://www.amazon.com/dp/" ;// + searchQuery; //URLEncoder.encode(searchQuery, "UTF-8");
			String searchUrl = "http://camelcamelcamel.com/product/"+searchQuery;
		  HtmlPage page = client.getPage(searchUrl);
		  System.out.println(page.asXml());
		
		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath(".//*[@id='content']/div[1]/div[3]/p/span[1]/span") ;  
		if(items.isEmpty()){  
		  System.out.println("No items found !");
		}else{
		for(HtmlElement item : items){  
//		  HtmlAnchor itemAnchor = ((HtmlAnchor)    item.getFirstByXPath(".//span[@class='txt']/span[@class='pl']/a"));
//
//		  String itemName = itemAnchor.asText();
//		  String itemUrl = itemAnchor.getHrefAttribute() ;
//		  HtmlElement spanPrice = ((HtmlElement) item.getFirstByXPath(".//span[@class='txt']/span[@class='l2']/span[@class='price']")) ;
//		  // It is possible that an item doesn't have any price
//		  String itemPrice = spanPrice == null ? "no price" : spanPrice.asText() ;
			String itemPrice=item.asText();
			String itemName=searchQuery;
			String itemUrl=searchUrl;
			
			

		  System.out.println( String.format("Name : %s Url : %s Price : %s", itemName, itemPrice, itemUrl));
		  }
		}
		
		} catch (FailingHttpStatusCodeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Failing Http Status Code Exception. " + e.getMessage());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Malformed URL exception. " + e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("IO Exception. " + e.getMessage());
        }

		client.close();
	
	}
		
	

}
