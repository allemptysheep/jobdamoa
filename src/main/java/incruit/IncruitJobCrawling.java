package incruit;

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

public class IncruitJobCrawling {
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
        String url = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";   

        driver.get(url);
        
        ////////////////////////////////////////////////////////
        
        try {
           
        	// 지역 파라미터 local=F000
        	// 직종 파라미터
        	
           /**
            * 지역 가져오기
            * 
            * 
            */

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.loc.circleType.dev-tab.dev-local.on > dt > p")));
            System.out.println("기다림");
            WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.loc.circleType.dev-tab.dev-local.on > dd.ly_sub > div.ly_sub_cnt.colm2-ty2.clear > dl.detail_sec.barType > dd > div.nano-content.dev-main"));
        	
            List<WebElement> list = citySelectBTN.findElements(By.tagName("li"));
            System.err.println(list.size());
            
            for(int i = 0; i < list.size(); i++) {
            	WebElement li = list.get(i);
            	if(li.getAttribute("class").equals("item hyphen")) {
            		continue;
            	}
            	
            	System.out.println(li.getAttribute("class"));
            	System.err.println(li.getText());
            	System.out.println(li.getAttribute("data-value-json"));
            }
            
            /**
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
            
            **/
        	
            /*
             * 직군 가져오기
             * 
             * 
             */
            /*
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
               // 버튼 클릭 시 자동으로 아래 버튼이 보여서 이동 안해도 됨
                // a.moveToElement(jobBtn).perform();
                // 버튼 클릭
                if (jobBtn.isDisplayed() && jobBtn.isEnabled()) {
                   System.out.println("직업 분야 : " + jobBtn.getText());
                   System.out.println("직업 분야 코드 : " + jobBtn.findElement(By.tagName("button")).getAttribute("data-mcls_cd_no"));
                   jobBtn.click();
                }
            }
          */
      } catch (Exception e) {
         // TODO: handle exception
         System.out.println("error saramin : " + e);
      }
        
        // 드라이버 닫기
      // driver.quit();
      
   }
}
