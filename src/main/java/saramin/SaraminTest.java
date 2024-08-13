package saramin;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SaraminTest {
	public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 페이지 로드까지 최대 30초까지 대기한다
        
        // Stating the Javascript Executor driver
        JavascriptExecutor js = (JavascriptExecutor)driver; 
        
        // Stating the actions
        Actions a = new Actions(driver);
        ////////////////////////////////////////////////////////
        
        // String url = "https://www.saramin.co.kr/";
        String url = "https://www.saramin.co.kr/zf_user/search?search_area=main";
        
        String keyword = "";
        String keywordQuery = "search?search_area=main&search_done=y&search_optional_item=n&searchType=recently&searchword="; 		
        
        if(keyword != "") {
         url = url + keywordQuery + keyword;
        }

        String locationCode = "";
        String locationQuery = "&loc_mcd=";
        
        String jobCode = "";
        String jobQuery = "&cat_kewd=";
        
        driver.get(url);
        
        ////////////////////////////////////////////////////////
        
        try {
        	
        	/**
        	 * 지역 가져오기
        	 * 
        	 * 
        	 */
        	
        	/*********************
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
                System.out.println("지역 : " + button.getText());
                System.out.println("값 : " + selectorId);
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
                    String guDataCode = guli.findElement(By.tagName("input")).getAttribute("value");
        			System.out.println("구 : " + guli.getText());
                    System.out.println("값 : " + guDataCode);
        			}
                System.out.println();
                System.out.println();
            }
            
            *************************/
            
            /*
             * 직군 가져오기
             * 
             * 
             */
            
        	// <button type="button" class="btn_job" data-mcls_cd_no="16">기획·전략</button>
        	// #sp_main_wrapper > div.main_option.active > div > div.option_content.job_category_section.on > div.details > div.box_jobs > button
        	
        	// <button type="button" data-mcls_cd_no="16" class="first_depth depth1_btn_16">
		    //    <span class="txt">기획·전략 </span>
		    //    <span class="count">(11,848)</span>
		    //  </button>
        	// #depth1_btn_16 > button
            // 직군 누르기
            // 직군 선택 누르기
        	System.out.println("직군 찾기 시작");
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#sp_main_wrapper > div.main_option > ul > li.job_category_section > button")));
            WebElement jobSelectBTN = driver.findElement(By.cssSelector("#sp_main_wrapper > div.main_option > ul > li.job_category_section > button"));

            // 지역 선택 버튼 클릭
            jobSelectBTN.click();
    		System.out.println("직군 선택 버튼 클릭");

    		// job box 나오기 기다리기
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("box_jobs")));
    		//job box 가져오기
            WebElement jobBox = driver.findElement(By.className("box_jobs"));
            WebElement firstButton = jobBox.findElement(By.tagName("button"));
        	// 기획 전약 버튼 클릭
            firstButton.click();

            // job box 데이터 다시 가져오기
            List<WebElement> jobList = driver.findElements(By.className("item_job"));
            System.out.println("jobList Size : " + jobList.size());
            
            for (int i = 0; i < jobList.size(); i++) {
            	WebElement jobBtn = jobList.get(i);
            	
            	String jobMclsText = jobBtn.getText();
            	String jobMclsCode = jobBtn.findElement(By.tagName("button")).getAttribute("data-mcls_cd_no");

            	System.out.println("직업 분야 : " + jobMclsText);
            	System.out.println("직업 분야 코드 : " + jobMclsCode);
            	// 버튼 클릭 시 자동으로 아래 버튼이 보여서 이동 안해도 됨
                // a.moveToElement(jobBtn).perform();
                // 직종 버튼 클릭
                if (jobBtn.isDisplayed() && jobBtn.isEnabled()) {
                	jobBtn.click();
                }
                #sp_job_category_subDepth_16 > div.row.list > div > div.viewport > div > dl:nth-child(1)
                #sp_job_category_subDepth_16 > div.row.list > div > div.viewport > div > dl:nth-child(2)
                List<WebElement> dl = driver.findElements(By.cssSelector("#sp_job_category_subDepth_" + jobMclsCode + " > div.row.list > div > div.viewport > div > dl.row_item"));
                System.out.println("row size : " + dl.size());
                for(int j = 0; j < dl.size(); j++) {
                	WebElement dt = dl.get(i).findElement(By.tagName("dt"));
                	List<WebElement> ddList = dl.get(i).findElements(By.tagName("button"));
                	System.out.println("row text : " + dt.getText());
                	System.out.println("data size : " + ddList.size());
                	
                	
                }
            }
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error saramin : " + e);
		}
        
        // 드라이버 닫기
		// driver.quit();
		
	}
}
