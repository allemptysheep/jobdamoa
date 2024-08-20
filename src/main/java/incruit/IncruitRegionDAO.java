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
		 return 1;
	 }
	 
	 // 20개 검색해서 가져오기
	 
	 public int crawlingIncruitTop20(String keyword) {
		 return 1;
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
