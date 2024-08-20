package saramin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoupTest {
	public static void main(String[] args) {
		try {
			System.out.println("시작");
			String crawlingUrl = "https://www.saramin.co.kr/zf_user/search?search_area=main";
			
            Document doc = Jsoup.connect(crawlingUrl).get();
            // System.out.println(doc);
            Elements li = doc.select("li.depth1_btn_wrapper");
            
            System.out.println(li.size());
            for(Element elem: li) {
            	String title = elem.select("button").text();
            	System.out.println(title);
            }
            
            System.out.println("끝");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("에러남 : " + e);
		}
	}
}
