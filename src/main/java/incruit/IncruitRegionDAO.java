package incruit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import common.DBConnect;
import search.MainSearchDTO;

public class IncruitRegionDAO extends DBConnect {
	public IncruitRegionDAO (ServletContext application) {
	     super(application);
	 }
	
	 public int crawlingIncruitRegion() {
		 int rs = 1;
//		 System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");
		 ChromeOptions chromeOptions = new ChromeOptions();
	        
	     chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
	     WebDriver driver = new ChromeDriver(chromeOptions);
	     
	     Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 페이지 로드까지 최대 30초까지 대기한다
	     
	  // Stating the Javascript Executor driver
	     JavascriptExecutor js = (JavascriptExecutor)driver; 
	     
	     
	     // Stating the actions
	     Actions a = new Actions(driver);
	
	     String url = "https://job.incruit.com/jobdb_list/searchjob.asp?";
	     
	     String keyword = "";
	     String keywordQuery = "kw=";       
	     String lastParameter = "&today=y";
	     
	     if(keyword != "") {
	      url = url + keywordQuery + keyword + lastParameter;
	     }
	
	     
	     driver.get(url);
	     
	     // 여기에 원하는 동작을 구현합니다.
	     try {
	         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#dropFirstList2"))).click(); // 지역 선택 버튼 대기 후 클릭
	         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#rgn1_list_149"))).click(); // 국내지역 선택 버튼 대기 후 클릭
	         // 지역 가져오기
	         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#rgn2_div > .shb-list-depth.listCell2 > li")));
	         
	         List<WebElement> RegionList = driver.findElements(By.cssSelector("#rgn2_div > .shb-list-depth.listCell2 > li > button"));
	         System.out.println();
	         WebElement clearbtn = driver.findElement(By.cssSelector("#incruit_contents > div.shb_body > div > div > div > div.shb-search-output > div.shb-search-output-btn > button.shb-btn-ref"));
	         String rgn3 = ""; // 구 파라미터
	         String rgn2 = "";
	         String gu_name = "";
	         String gu_code = "";
	         String region_code = "";
	         String region_name = "";
	         
	         // 17개시도 정보가 나올때까지 클릭하여 정보를 호출 해준다.
	         for (int i = 0; i < RegionList.size(); i++) {
	             if(!RegionList.get(i).getText().equals("전국")) {
	                 // 지역 클릭가능 할때 까지 대기
	                 wait.until(ExpectedConditions.elementToBeClickable(RegionList.get(i)));
	                 RegionList.get(i).click();
	//                 System.out.println("지역 클릭 하기");
	                 region_name = RegionList.get(i).getText().substring(0,2); // 지역명 통합을 위해 ~특별자치도등 제외하고 반환
	                 System.out.println(RegionList.get(i).getText() + " 클릭 됨 "); // 대분류 이름 호출
	                 rgn2 = RegionList.get(i).getAttribute("value");
	                 region_code = rgn2.substring(6); // &rgn2=11 , 파라미터 키값은 제외하고 호출
	                 // 지역 5개 선택제한으로 인해 초기화버튼
	                 wait.until(ExpectedConditions.elementToBeClickable(clearbtn));
	//                 System.out.println("초기화버튼 클릭 대기");
	                 clearbtn.click();
	                 insertRegion(i, region_name, region_code);
	             }
	         }
	         
	         List<WebElement> gu_list_container = driver.findElements(By.cssSelector("#rgn3_div > ul")); // 3차 선택지 (XX군,XX구) 각 div > ul 저장
	       
	         for(int i = 0; i < gu_list_container.size(); i++) {
	             List<WebElement> gu_list = gu_list_container.get(i).findElements(By.tagName("li")); // 각 구 정보 저장
	             for (int j = 0; j < gu_list.size(); j++) {
	                 WebElement guli = gu_list.get(j);
	                 System.out.println(guli.getAttribute("textContent"));
	                 gu_name = guli.getAttribute("textContent").split("\\(")[0];
	                 if(gu_name == "전지역") {
	                	 continue;
	                 }
	                 rgn3 = guli.findElement(By.className("shb-chk")).getAttribute("value");
	                 region_code = rgn3.substring(6, 8); // 코드의 앞 두자리
	                 gu_code = rgn3.substring(6); // &rgn3=1111 , 서울 강남구, 파라미터 키값은 제외하고 호출
	                 System.out.println("값은" + gu_code); 
	                 
	                 insertGu(gu_name, gu_code, region_code);
	         }
	      }
	     } catch (Exception e) {
	    	 rs = 0;
	         System.out.println("Incruit Error : " + e);
	         e.printStackTrace();
	     }
	     return rs;
	 }
	 
	 
	 public int insertRegion (int idx, String RegionName, String RegionCode) {
		 int rs = 0;
		   
	      String sql = "INSERT INTO region(idx, region_name, region_code, job_site) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE region_name = (?)";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setInt(1, idx);
	         psmt.setString(2, RegionName);
	         psmt.setString(3, RegionCode);
	         psmt.setString(4, "incruit");
	         psmt.setString(5, RegionName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
	      return rs;
	 }
	 
	// 구 정보 삽입
	   public int insertGu(String guName, String guCode, String regionCode) {
		   int rs = 0;
		   
	      String sql = "INSERT INTO gu(gu_name, gu_code, region_code, job_site) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE gu_name = (?)";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setString(1, guName);
	         psmt.setString(2, guCode);
	         psmt.setString(3, regionCode);
	         psmt.setString(4, "incruit");
	         psmt.setString(5, guName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   public MainSearchDTO selectRegionName () {
		   MainSearchDTO mainSearchDTO = new MainSearchDTO();
		   List<Object> regionList = new ArrayList<Object>(); 
		   
		   String sql = "SELECT DISTINCT region_name FROM region ORDER BY region_code";
		   
		   try {
			   psmt = con.prepareStatement(sql);
			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("regionName", rs.getString("region_name"));
				   
				   regionList.add(jsonObject);
			   }
			   System.out.println(regionList);
			   mainSearchDTO.setRegionData(regionList);
		   } catch (Exception e) {
			   System.out.println("검색 DB오류 : " + e);
		   }
		   
		   return mainSearchDTO;
	   }
	   
	   public MainSearchDTO selectRegion () {
		   MainSearchDTO mainSearchDTO = new MainSearchDTO();
		   List<Object> regionList = new ArrayList<Object>(); 
		   
		   String sql = "SELECT DISTINCT region_name FROM region ORDER BY region_code";
		   
		   try {
			   psmt = con.prepareStatement(sql);
			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				   JSONObject jsonObject = new JSONObject();
//				   jsonObject.put("regionIdx", rs.getInt("idx"));
				   jsonObject.put("regionName", rs.getString("region_name"));
				   jsonObject.put("regionCode", rs.getString("region_code"));
				   jsonObject.put("regionSiteCode", rs.getString("job_site"));
				   
				   regionList.add(jsonObject);
			   }
			   System.out.println(regionList);
			   mainSearchDTO.setRegionData(regionList);
		   } catch (Exception e) {
			   System.out.println("검색 DB오류 : " + e);
		   }
		   
		   return mainSearchDTO;
	   }
	
	
}
