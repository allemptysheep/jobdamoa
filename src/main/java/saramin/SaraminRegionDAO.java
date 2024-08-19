package saramin;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

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

public class SaraminRegionDAO extends DBConnect {
	
	   public SaraminRegionDAO (ServletContext application) {
		      super(application);
		   }
	   
	   public int crawlingRegion() {
		   	int rs = 1;
		   	// System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	        // 화면에 안보이게
	        ChromeOptions chromeOptions = new ChromeOptions();
	        
	        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
	        // chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	        // chromeOptions.setExperimentalOption("useAutomationExtension", false);
	        // chromeOptions.addArguments("--headless");
	        // chromeOptions.addArguments("--no-sandbox");

	        WebDriver driver = new ChromeDriver( chromeOptions);

	        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 페이지 로드까지 최대 30초까지 대기한다
	        
	        // Stating the Javascript Executor driver
	        JavascriptExecutor js = (JavascriptExecutor)driver; 
	        
	        // Stating the actions
	        Actions a = new Actions(driver);
	        ////////////////////////////////////////////////////////
	        
	        String url = "https://www.saramin.co.kr/zf_user/search?search_area=main";
	        driver.get(url);
	        
	        ////////////////////////////////////////////////////////
	        
	        try {
	        	
	        	/**
	        	 * 지역 가져오기
	        	 * 
	        	 * 
	        	 */
	            // 지역 선택 누르기
	        	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#sp_main_wrapper > div.main_option > ul > li.area_section > button")));
	    		System.out.println("지역 선택 버튼 기다리기");
	            WebElement citySelectBTN = driver.findElement(By.cssSelector("#sp_main_wrapper > div.main_option > ul > li.area_section > button"));

	            // 지역 선택 버튼 클릭
	    		citySelectBTN.click();
	    		System.out.println("지역 선택 버튼 클릭");
	    		
	    		// 지역 누르기 (서울, 경기)
	            for (int i = 1; i <=18; i++) {
	            	
	            	String selectorId = (100000 + i*1000) + "";
	            	//// 지역 버튼 클릭
	            	String selector = "depth1_btn_" + selectorId;
	            	// System.out.println(selector);
	            	// 버튼 찾기
	                WebElement button = driver.findElement(By.id(selector));
	                // 버튼 으로 이동
	                a.moveToElement(button).perform();
	                // 버튼 클릭
	                if (button.isDisplayed() && button.isEnabled()) {
	                	button.click();
	                }
	                
	                String regionText = button.getText().split(" \\(")[0];
	                System.out.println("지역 : " + regionText);
	                System.out.println("지역 값 : " + selectorId);
	                
	                // region 저장
	                insertRegion(i-1, regionText, selectorId);
	                System.out.println();

	                
	                //// 지역 버튼 해제
	            	String selectorSelected = "sp_preview_area_" + selectorId;
	            	// System.out.println(selectorSelected);
	            	// 버튼 찾기
	                WebElement btnSelected = driver.findElement(By.id(selectorSelected));
	                // 버튼 으로 이동
	                a.moveToElement(btnSelected).perform();
	                // 버튼 다시 클릭 후 해제
	                if (btnSelected.isDisplayed() && btnSelected.isEnabled()) {
	                	btnSelected.click();
	                }

	                
	                // 구 데이터 가져오기
	                String guSelector =  "#sp_area_lastDepth_" + selectorId + " > li";
	                List<WebElement> guUl = driver.findElements(By.cssSelector(guSelector));
	                System.out.println("지역 수 : " + guUl.size());
	        		for (int j = 0; j < guUl.size(); j++) {
	        			WebElement guli = guUl.get(j);
	                    a.moveToElement(guli).perform();
	                    String guName = guli.getText().split(" \\(")[0];
	                    String guDataCode = guli.findElement(By.tagName("input")).getAttribute("value");
	        			System.out.println("구 : " + guName);
	                    System.out.println("값 : " + guDataCode);
	                    
	                    insertGu(guName, guDataCode, selectorId);
	        			}
	                System.out.println();
	                System.out.println();
	            } 
	            
	        }catch (Exception e) {
				// TODO: handle exception
	        	rs = 0;
	        	System.out.println("crawling saramin err : " + e);
			}
	            
		   return rs;
	   }
	   
	   // 지역 정보 삽입
	   public int insertRegion(int idx, String regionName, String regionCode) {
		   int rs = 0;
		   
	      String sql = "INSERT INTO region(idx, region_name, region_code, job_site) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE region_name = (?)";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setInt(1, idx);
	         psmt.setString(2, regionName);
	         psmt.setString(3, regionCode);
	         psmt.setString(4, "saramin");
	         psmt.setString(5, regionName);
	         
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
	         psmt.setString(4, "saramin");
	         psmt.setString(5, guName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
}
