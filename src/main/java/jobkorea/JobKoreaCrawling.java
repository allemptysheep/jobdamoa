package jobkorea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
	
public class JobKoreaCrawling {
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
//	        String url2 = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";   
	
//	        driver.get(url2);        	
	
	        
	        ////////////////////////////////////////////////////////
	        
	        try {
	        	String url = "jdbc:mariadb://localhost:3306/jobdamoa";
	        	String user = "user";
	        	String password = "1234";
	
	        	LocalDateTime now = LocalDateTime.now();
	        	
//	        	String sql_d = "delete from gu";
//	
//				Connection con_d = DriverManager.getConnection(url, user, password);
//				PreparedStatement psmt_d = con_d.prepareStatement(sql_d);
//	
//				psmt_d.executeUpdate();
//	
//				psmt_d.close();
//				con_d.close();
				
	      	
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        try {
	
	            Class.forName("org.mariadb.jdbc.Driver");
	
	        	String url = "jdbc:mariadb://localhost:3306/jobdamoa";
	        	String user = "user";
	        	String password = "1234";
	
	        	
	       	String sql_d2 = "delete from region";
	
				Connection con_d2 = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt_d2 = con_d2.prepareStatement(sql_d2);

				pstmt_d2.executeUpdate();
	
				pstmt_d2.close();
				con_d2.close(); 
			
	        	  	
	        	
	
	
		                 		
		List<WebElement> region = driver.findElements(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.loc.circleType.dev-tab.dev-local.on > dd.ly_sub > div.ly_sub_cnt.colm2-ty2.clear > dl.detail_sec.barType > dd > div.nano-content.dev-main > ul > li"));
		List<WebElement> job = driver.findElements(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.job.circleType.dev-tab.dev-duty > dd.ly_sub > div.ly_sub_cnt.colm3-ty1.clear > dl:nth-child(1) > dd > div.nano-content.dev-main > ul > li"));
		String subName = "";
		String subCode = "";
		String jsonString_1 = "";
		String jsonString_2 = "";
		String jsondecodeStrong_1 = "";
		String jsondecodeStrong_2 = "";
		JSONObject jsonObject_test = new JSONObject();
		JSONObject jsonObject_test2 = new JSONObject();

		for(int l = 0; l <21; l++) {
			jsonString_2 = job.get(l).getAttribute("data-value-json");
			jsondecodeStrong_2 = org.jsoup.parser.Parser.unescapeEntities(jsonString_2,true);
			jsonObject_test2 = new JSONObject(jsondecodeStrong_2);
			
			String jobName1 = jsonObject_test2.optString("groupName");
			JSONArray subList2 = jsonObject_test2.optJSONArray("subList");
			
			
			System.err.println(jobName1);
			
			   if(subList2 != null) {
				   for(int m = 0; m < subList2.length(); m++) {
					   JSONObject subItem2 = subList2.getJSONObject(m);
				    	String jobCode = subItem2.optString("subCode");
				    	String jobName2 = subItem2.optString("subName");
				    	
				    	System.err.println(jobCode);
				    	System.out.println(jobName2);
				   }
		}
		
		
		
		
		
		}
		for (int i = 0; i < 17; i++) {
			
        jsonString_1 = region.get(i).getAttribute("data-value-json");
        
        jsondecodeStrong_1 = org.jsoup.parser.Parser.unescapeEntities(jsonString_1, true);
        
        jsonObject_test = new JSONObject(jsondecodeStrong_1);
        
        String groupName = jsonObject_test.optString("groupName");
        String groupCode = jsonObject_test.optString("groupCode");
        JSONArray subList = jsonObject_test.optJSONArray("subList");
      
        
        
//        System.err.println("지역 : " + groupName);
//        System.out.println("그룹 코드는 : " + groupCode);
        
    	String sql_r = "insert into region values(?,?,?,?)";
    	
    		Connection con_r= DriverManager.getConnection(url,user,password);
    		PreparedStatement psmt_r = con_r.prepareStatement(sql_r);

    		psmt_r.setInt(1, i+1);
	    	psmt_r.setString(2, groupName);
	    	psmt_r.setString(3, groupCode);
	    	psmt_r.setString(4, "JobKorea");
	   	
	    	psmt_r.executeUpdate();
	
	    	psmt_r.close();
	    	con_r.close();
        
        
   if(subList != null) {
	   
   
    for(int j = 0; j < subList.length(); j++) {
    	
    	JSONObject subItem = subList.getJSONObject(j);
    	subCode = subItem.optString("subCode");
    	subName = subItem.optString("subName");
    	if(subName.contains("전체")) {
    		continue;
    	}
//    	System.out.println("구 이름 : " + subName);
//    	System.out.println("구 코드 : " + subCode);
				    
    	 
//    	String sql = "insert into gu values(?,?,?,?)";
//    	
//    	Connection con= DriverManager.getConnection(url,user,password);
//    	PreparedStatement psmt = con.prepareStatement(sql);
//    	
//    	psmt.setString(1, subName);
//    	psmt.setString(2, subCode);
//    	psmt.setString(3, groupCode);
//    	psmt.setString(4, "JobKorea");
//    	
//    	
//    	psmt.executeUpdate();
//    	
//    	psmt.close();
//    	con.close();
    	
    	
    
		              
	        	/** 지역 파라미터 
	        	 * local = (
	        	 * I = 서울, B = 경기, K = 인천,
	        	 * G = 대전, 1 = 세종, O = 충남,
	        	 * P = 충북, E = 광주, L = 전남,
	        	 * M = 전북, F = 대구, D = 경북,
	        	 * H = 부산, J = 울산, C = 경남,
	        	 * A = 강원, N = 제주, Q = 전국,
	        	 * R = 아시아·중동, X = 중국·홍콩, Z = 일본,
	        	 * Y = 미국, S = 북아메리카, T = 남아메리카,
	        	 * U = 유럽, V = 오세아니아, W = 아프리카   
	        	 + 000 + 10n
	        	 )  --세종시는 1000 하나뿐--
	        	 **/
	        	
	        	/** 직종 파라미터 duty = (
	        	 * 기획·전략 = 1000185 ~ 1000190
	        	 * 법무·사무·총무 = 1000191 ~ 1000200
	        	 * 인사·HR = 1000201 ~ 1000206
	        	 * 회계·사무 = 1000207 ~ 1000215
	        	 * 마케팅·광고·MD = 1000216 ~ 1000228
	        	 * 개발·데이터 = 1000229 ~ 1000247
	        	 * 디자인 = 1000248 ~ 1000264
	        	 * 물류·무역 = 1000265 ~ 1000269
	        	 * 운전·운송·배송 = 1000270 ~ 1000276
	        	 * 영업 = 1000277 ~ 1000285
	        	 * 고객상담·TM = 1000286 ~ 1000288
	        	 * 금융·보험 = 1000289 ~ 1000296
	        	 * 식·음료 = 1000297 ~ 1000308
	        	 * 고객서비스·리테일 = 1000309 ~ 1000325
	        	 * 엔지니어링·설계 = 1000326 ~ 1000337
	        	 * 제조·생산 = 1000338 ~ 1000343
	        	 * 교육 =  1000344 ~ 1000353
	        	 * 건축·시설 = 1000354 ~ 1000371
	        	 * 의료·바이오 = 1000372 ~ 1000385
	        	 * 미디어·문화·스포츠 = 1000386 ~ 1000406
	        	 * 공공·복지 = 1000407 ~ 1000413
				 )
	        	 **/
	        	
	        	/** 경력 파라미터 career ul 클래스명 expSel // career_text ul 클래스명 expInp 
	        	 * career = (
	        	 * 1 = 신입
	        	 * 2 = 1 ~ 3년
	        	 * 3 = 4 ~ 6년
	        	 * 4 = 7 ~ 9년
	        	 * 5 = 10 ~ 15년
	        	 * 6 = 16 ~ 20년
	        	 * 7 = 21년이상
	        	 * 8 = 경력무관
	        	 * \\career_text 입력값 ~ 입력값 (년)\\
	        	**/
	        	
	        	/** 기업형태 파라미터 cotype = (
	        	 * 1 = 대기업
	        	 * 2 = 30대그룹사
	        	 * 3 = 매출1000대기업
	        	 * 4 = 중견기업
	        	 * 5 = 강소기업
	        	 * 6 = 외국계기업
	        	 * 7 = 중소기업
	        	 * 8 = 벤처기업
	        	 * 9 = 공공기관·공기업
	        	 * 10 = 비영리단체·협회재단
	        	 * 11 = 외국기관·단체
	        	 * 12 = 코스피상장
	        	 * 13 = 코스닥상장
	        	 * 14 = 코넥스상장
	        	 * 15 = 해외상장
	        	 )
	        	 **/
	        	
	        	/** 고용형태 파라미터 jobtype = (
	        	 * 1 = 정규직
	        	 * 2 = 계약직
	        	 * 3 = 인턴
	        	 * 4 = 파견직
	        	 * 5 = 도급
	        	 * 6 = 프리랜서
	        	 * 7 = 아르바이트
	        	 * 8 = 연수생/교육생
	        	 * 9 = 병역특례
	        	 * 10 = 위촉직/개인사업자
	        	 )
	        	 */
	        	
	        	/** 산업 파라미터 industry = (
	        	 * 서비스업 = 1000001 ~ 1000010
	        	 * 금융·은행업 = 1000011 ~ 1000013
	        	 * IT·정보통신업 = 1000038 ~ 1000047
	        	 * 판매·유통업 = 1000014 ~ 1000016
	        	 * 제조·생산·화학업 = 1000052 ~ 1000063
	        	 * 교육업 = 1000021 ~ 1000024
	        	 * 건설업 = 1000017 ~ 1000020
	        	 * 의료·제약업 = 1000025 ~ 1000029
	        	 * 미디어·광고업 = 1000030 ~ 1000035
	        	 * 문화·예술·디자인업 = 1000036 ~ 1000037
	        	 * 기관·협회 = 1000048 ~ 1000051
	        	 )
	        	 */
	        	
	        	/** 직급/직책/급여 position = (
	        	 * 직급 
	        	 * 1 = 사원급
	        	 * 2 = 주임 ~ 대리급
	        	 * 3 = 과장 ~ 차장급
	        	 * 4 = 부장급
	        	 * 5 = 임원 ~ CEO
	        	 * 직책 
	        	 * 8 = 팀장/매니저/실장
	        	 * 9 = 파트장/그룹장
	        	 * 10 = 본부장/센터장
	        	 * 11 = 지점장/지사장/원장/국장/공장장
	        	 * 12 = 조장/반장/직장
	        	 * 급여 pay = (
	        	 * 1 = ~ 2500만원
	        	 * 2 = 2500 ~ 3000만원
	        	 * 3 = 3000 ~ 3500만원
	        	 * 4 = 3500 ~ 4000만원
	        	 * 5 = 4000 ~ 5000만원
	        	 * 6 = 5000만원 ~
	        	 * \\ payinput = 옵션값 입력값 만원이상 \\
	        	 ) 
	        	 */
	        	
	        	
	        	
	           /**
	            * 지역 가져오기
	            * 
	            * 
	            */
	
	            
	            
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
    }
		                }
	                }    
	            
	        } catch (Exception e) {
	            // TODO: handle exception
	            System.out.println("Jobkorea Error: " + e);
	         }
	           
	           // 드라이버 닫기
	         // driver.quit();
	         
	      }
	   }        	//직무 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.job.circleType.dev-tab.dev-duty > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div > dl.job.circleType.dev-tab.dev-duty > dd.ly_sub > div.ly_sub_cnt.colm3-ty1.clear > dl.detail_sec.barType > dd > div.nano-content.dev-main"));
	
	//지역파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.loc.circleType.dev-tab.dev-local > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div > dl.loc.circleType.dev-tab.dev-local > dd.ly_sub > div.ly_sub_cnt.colm2-ty2.clear > dl.detail_sec.barType > dd > div.nano-content.dev-main"));
	
	//경력 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.exp.circleType.dev-tab.dev-career > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.exp.circleType.dev-tab.dev-career > dd > div.nano-content"));
	
	//학력 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.edu.circleType.dev-tab.dev-edu > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.edu.circleType.dev-tab.dev-edu > dd > div.nano-content"));
	
	//기업형태 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.cty.circleType.dev-tab.dev-cotype > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.cty.circleType.dev-tab.dev-cotype > dd.nano.has-scrollbar > div.nano-content"));
	
	//고용형태 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.hty.circleType.dev-tab.dev-jobtype > dt > p")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div:nth-child(1) > dl.hty.circleType.dev-tab.dev-jobtype > dd > div.nano-content"));
	
	//산업 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ids.dev-industry.dev-tab > dt > button")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ids.dev-industry.dev-tab > dd.ly_sub > div.ly_sub_cnt.colm3-ty1.clear > dl:nth-child(1) > dd > div.nano-content.dev-main"));
	
	//직급 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dt > button")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dd.ly_sub > div > div.ly_sub_wrap.clear > dl:nth-child(1) > dd > div.nano-content"));
	
	//직책 파라미터 -x
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dt > button")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dd.ly_sub > div > div.ly_sub_wrap.clear > dl.detail_sec.circleType.clear > dd.list > div.nano-content"));
	
	//급여 파라미터
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dt > button")));
	//WebElement citySelectBTN = driver.findElement(By.cssSelector("#devSearchForm > div.detailArea > div > div.detail-search.subOption.clear > dl.ppp.dev-tab.dev-position > dd.ly_sub > div > div.ly_sub_wrap.clear > dl:nth-child(3) > dd > div"));
	
	
	//System.out.println("기다림");
	
	//List<WebElement> list = citySelectBTN.findElements(By.tagName("input"));
	//List<WebElement> list2 = citySelectBTN.findElements(By.tagName("span"));
	//List<WebElement> list3 = citySelectBTN.findElements(By.tagName("li"));
	
	//System.err.println(list.size());
	//System.err.println(list2.size());
	//System.err.println(list3.size());
	
