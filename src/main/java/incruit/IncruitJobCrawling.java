package incruit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IncruitJobCrawling {
	public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 페이지 로드까지 최대 30초까지 대기한다
        
        
        driver.get("https://job.incruit.com/jobdb_list/searchjob.asp?ct=3&ty=2&cd=11");
        
        // 여기에 원하는 동작을 구현합니다.
        try {
        	// "dropFirstList1 아이디를 가진 엘리멘트가 보일때까지 대기(최대 60초)"
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dropFirstList1")));
    		//WebElement는 html의 태그를 가지는 클래스이다.
        	List<WebElement> navEl = driver.findElements(By.id("dropFirstList1"));
    		
    		for (int i = 0; i < navEl.size(); i++) {
    			if(navEl.get(i).getText().equals("직종 · 직무선택")) {
    				navEl.get(i).click();
    				break;
    			}
    		}
    		
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("occ1_div")));
			WebElement secitEl = driver.findElement(By.id("occ1_div")); // 1차카테고리 선택
			List<WebElement> findtag = secitEl.findElements(By.tagName("li"));
			for (int i = 0; i < findtag.size(); i++) {
				if(findtag.get(i).getText().equals("인터넷·IT·통신·모바일·게임")) {
					findtag.get(i).click();
					break;
				}
			}
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("occ2_div")));
			WebElement second_list = driver.findElement(By.id("occ2_div"));
			List<WebElement> findtag_detail = second_list.findElements(By.tagName("li"));
	        for (int i = 0; i < findtag_detail.size(); i++) {
	        	if(findtag_detail.get(i).getText().equals("전체보기")) {
	        		findtag_detail.get(i).click();
					break;
				}
	        }
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.className("shb-btn-search")));
	        driver.findElement(By.className("shb-btn-search")).click();
	        
	        WebElement cBbslist_contenst = driver.findElement(By.className("cBbslist_contenst"));
	        // 채용목록 담기
	        List<WebElement> incruit_list = cBbslist_contenst.findElements(By.className("c_row"));
	        
	        List<String> incruit_company_name = new ArrayList<String>(); // 회사명
	        List<String> incruit_title = new ArrayList<String>(); // 채용 공고 타이틀
	        List<String> incruit_link = new ArrayList<String>(); // 채용 공고 링크
	        List<String> incruit_info_career = new ArrayList<String>(); // 연차(신입,경력 X년)
	        List<String> incruit_info_education = new ArrayList<String>(); // 졸업 여부(고졸, 대졸 등)
	        List<String> incruit_info_region = new ArrayList<String>(); // 지역
	        List<String> incruit_info_contract_type = new ArrayList<String>(); // 계약 타입(정규직, 계약직)
	        List<String> incruit_info_salary = new ArrayList<String>(); // 월급여부(값 없을 시 회사내규)
	        
	        for(int i = 0; i < incruit_list.size(); i++) {
	        	incruit_company_name.add(incruit_list.get(i).findElement(By.className("cpname")).getText());
	        	incruit_title.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.tagName("a")).getText());
	        	incruit_link.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.tagName("a")).getAttribute("href"));
	        	incruit_info_career.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.className("cl_md")).findElement(By.cssSelector("span:nth-child(1)")).getText());
	        	incruit_info_education.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.className("cl_md")).findElement(By.cssSelector("span:nth-child(2)")).getText());
	        	incruit_info_region.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.className("cl_md")).findElement(By.cssSelector("span:nth-child(3)")).getText());
	        	incruit_info_contract_type.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.className("cl_md")).findElement(By.cssSelector("span:nth-child(4)")).getText());
	        	
	        	// 5번째 필드는 월급,연봉 정보, 없는 경우 예외처리(회사내규)
	        	try {
	        		incruit_info_salary.add(incruit_list.get(i).findElement(By.className("cell_mid")).findElement(By.className("cl_md")).findElement(By.cssSelector("span:nth-child(5)")).getText());
	        	} catch (Exception e) {
	        		incruit_info_salary.add("회사내규");
	        	}
	        }
	        
	        for(int i = 0; i < incruit_company_name.size(); i++) {
	        	System.out.println(incruit_company_name.get(i) + " , " + incruit_title.get(i) + " , " + incruit_link.get(i) + " , " + incruit_info_career.get(i) + " , " + incruit_info_education.get(i) + " , " + incruit_info_region.get(i) + " , " + incruit_info_salary.get(i));
	        }
        } catch (Exception e) {
        	
        }
       
    }
}
