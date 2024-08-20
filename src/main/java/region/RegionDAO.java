package region;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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

public class RegionDAO extends DBConnect {
	
	   public RegionDAO (ServletContext application) {
		      super(application);
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
	         psmt.setString(4, "jobdamoa");
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
	         psmt.setString(4, "jobdamoa");
	         psmt.setString(5, guName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   // select region
	   public RegionDTO getRegion() {
		  RegionDTO regionDTO = new RegionDTO();
		  List<Object> regionList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM region WHERE job_site=(?)";
	      String jobSite = "jobdamoa";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, jobSite);
	         rs = psmt.executeQuery();
	         
	         while (rs.next()) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("idx", rs.getString("idx"));
				   jsonObject.put("regionName", rs.getString("region_name"));
				   jsonObject.put("regionCode", rs.getString("region_code"));
				   jsonObject.put("jobSite", rs.getString("job_site"));
				   
				   regionList.add(jsonObject);
	         }
	         System.out.println(regionList);
	         regionDTO.setRegionData(regionList);
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return regionDTO;
	   }
	   
	   // select gu
	   public RegionDTO getGu() {
		  RegionDTO regionDTO = new RegionDTO();
		  List<Object> guList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM gu WHERE job_site=(?)";
	      String jobSite = "jobdamoa";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, jobSite);
	         rs = psmt.executeQuery();
	         
	         while (rs.next()) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("guName", rs.getString("gu_name"));
				   jsonObject.put("guCode", rs.getString("gu_code"));
				   jsonObject.put("regionCode", rs.getString("region_code"));
				   jsonObject.put("jobSite", rs.getString("job_site"));
				   
				   guList.add(jsonObject);
	         }
	         regionDTO.setGuData(guList);
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return regionDTO;
	   }
}
