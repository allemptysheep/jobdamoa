package incruit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class IncruitJobCrawling {
   public static void main(String[] args) {
//	   System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");
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
            String gu_code = "";
            String region_code = "";
            
            // 17개시도 정보가 나올때까지 클릭하여 정보를 호출 해준다.
            for (int i = 0; i < RegionList.size(); i++) {
                if(!RegionList.get(i).getText().equals("전국")) {
                    // 지역 클릭가능 할때 까지 대기
                    wait.until(ExpectedConditions.elementToBeClickable(RegionList.get(i)));
                    RegionList.get(i).click();
//                    System.out.println("지역 클릭 하기");
                    System.out.println(RegionList.get(i).getText() + " 클릭 됨 "); // 대분류 이름 호출
                    rgn2 = RegionList.get(i).getAttribute("value");
                    region_code = rgn2.substring(6); // &rgn2=11 , 파라미터 키값은 제외하고 호출
                    // 지역 5개 선택제한으로 인해 초기화버튼
                    wait.until(ExpectedConditions.elementToBeClickable(clearbtn));
//                    System.out.println("초기화버튼 클릭 대기");
                    clearbtn.click();                        
                }
            }
            
            List<WebElement> gu_list_container = driver.findElements(By.cssSelector("#rgn3_div > ul")); // 3차 선택지 (XX군,XX구) 각 div > ul 저장
          
            for(int i = 0; i < gu_list_container.size(); i++) {
                List<WebElement> gu_list = gu_list_container.get(i).findElements(By.tagName("li")); // 각 구 정보 저장
                
                for (int j = 0; j < gu_list.size(); j++) {
                    WebElement guli = gu_list.get(j);
                    System.out.println(guli.getAttribute("textContent"));
                    rgn3 = guli.findElement(By.className("shb-chk")).getAttribute("value");
                    gu_code = rgn3.substring(6); // &rgn3=1111 , 서울 강남구, 파라미터 키값은 제외하고 호출
                    System.out.println("값은" + gu_code); 
            }
         }
         

            
            /*
             * 직무 선택 하기
             */
            /*
            String occ3 = "";
            String job_code = "";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#dropFirstList1"))).click(); // 직무 선택 버튼 대기 후 클릭
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#dropFirstDown1 > div.shb-tablay.dropFirst > ul"))); // 직종, 직무 대분류 버튼 대기
            List<WebElement> job_selector_1st = driver.findElements(By.cssSelector("#dropFirstDown1 > div.shb-tablay.dropFirst > ul > li")); // 1차 카테고리 담기
            String job_category_first = "";
            */
            
            /*
             * 모든 직종 div를 호출할때까지 실행한다.
             */
            /*
            for(int i = 0; i < job_selector_1st.size(); i++) {
                job_selector_1st.get(i).click();
//                wait.until(ExpectedConditions.elementToBeClickable(job_selector_1st.get(i))).click(); // 대분류 버튼 클릭가능 대기 후 클릭
                job_category_first = job_selector_1st.get(i).getAttribute("textContent");
                System.out.println(job_selector_1st.get(i).getAttribute("textContent")); // 대분류 이름 호출
                
                if(!job_category_first.equals("전체보기")) {
//                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#dropFirstDown1 > div.shb-tablay.dropFirst > div#occ2_div > ul")));
                    List<WebElement> job_selector_2nd_container = driver.findElements(By.cssSelector("#dropFirstDown1 > div.shb-tablay.dropFirst > div#occ2_div > ul:nth-child(1)"));
                    
                    for(int j = 0; j < job_selector_2nd_container.size(); j++) {
//                        System.out.println(job_selector_2nd_container.size());
                        List<WebElement> job_selector_2nd = job_selector_2nd_container.get(j).findElements(By.cssSelector("#dropFirstDown1 > div.shb-tablay.dropFirst > div#occ2_div > ul:nth-child(1) > li"));
                        for(int k = 0; k < job_selector_2nd.size(); k++) {
                            if(!job_selector_2nd.get(k).getAttribute("textContent").equals("전체보기")) {
//                                System.out.println( job_selector_2nd.get(k).getAttribute("textContent"));
                                job_selector_2nd.get(k).click();
                                //  초기화버튼
//                                wait.until(ExpectedConditions.elementToBeClickable(clearbtn));
//                                System.out.println("초기화버튼 클릭 대기");    
                                clearbtn.click();
                            }
                        }
                    }
                }  
            }
           
            List<WebElement> job_list_container = driver.findElements(By.cssSelector("#occ3_div > ul")); // 3차 선택지 (XX군,XX구) 각 div > ul 저장
            System.out.println("직업코드 추출 시작");
            for(int i = 0; i < job_list_container.size(); i++) {
                List<WebElement> job_list = job_list_container.get(i).findElements(By.tagName("li")); // 각 구 정보 저장
                
                for (int j = 0; j < job_list.size(); j++) {
                    WebElement jobli = job_list.get(j);
                    System.out.println(jobli.getAttribute("textContent"));
                    occ3 = jobli.findElement(By.className("shb-chk")).getAttribute("value");
                    job_code = occ3.substring(6); // &occ3=16539 , 서울 강남구, 파라미터 키값은 제외하고 호출
                    System.out.println("값은" + job_code); 
                }
            }  
            */  
        } catch (Exception e) {
            System.out.println("Incruit Error : " + e);
            e.printStackTrace();
        }
    }
   
   	
}
