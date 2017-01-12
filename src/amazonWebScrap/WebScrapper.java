/**
 * 
 */
package amazonWebScrap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Element;

/**
 * @author SoURaV
 *
 */
public class WebScrapper {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public static String fetchData(WebClient c,String searchStr) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		String searchUrl = "http://camelcamelcamel.com/product/"+searchStr;
		HtmlElement item;
		
			HtmlPage page = c.getPage(searchUrl);
			item = page.getFirstByXPath(".//*[@id='content']/div[1]/div[3]/p/span[1]/span");
			
			if (item.asText()==""){
				System.out.println("Price Not Found");
				return "";
			}else{
				System.out.println(item.toString());
				String str=String.format("%s", item.asText());
				return str;
			}
			
		
		//return item.asText();
		
		//return item.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String searchQuery = "B006LXOJC0";
		WebClient client = new WebClient();
		ProxyConfig proxyConfig = new ProxyConfig("103.13.74.190",80);
		client.getOptions().setProxyConfig(proxyConfig);

		  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		try {  
		  //String searchUrl = "https://www.amazon.com/dp/" + searchQuery; URLEncoder.encode(searchQuery, "UTF-8");
			String searchUrl = "http://camelcamelcamel.com/product/"+searchQuery;
		  HtmlPage page = client.getPage(searchUrl);
		  System.out.println(page.toString());
		
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
		System.out.println(fetchData(client, "B00IJ0ALYS"));
	}catch(Exception e){
		  e.printStackTrace();
	}finally{
		client.close();
	}
	
	
	}
}
