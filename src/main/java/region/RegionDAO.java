package region;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;

import common.DBConnect;

public class RegionDAO extends DBConnect {
	
	   public RegionDAO (ServletContext application) {
		      super(application);
		   }
	   
	   // select region
	   public RegionDTO getRegion() {
		  RegionDTO regionDTO = new RegionDTO();
		  List<Object> regionList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM region WHERE country=(?)";
	      String country = "korea";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, country);
	         rs = psmt.executeQuery();
	         
	         while (rs.next()) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("idx", rs.getString("idx"));
				   jsonObject.put("regionName", rs.getString("region_name"));
				   jsonObject.put("regionCode", rs.getString("region_code"));
				   jsonObject.put("country", rs.getString("country"));
				   
				   regionList.add(jsonObject);
	         }
	         // System.out.println(regionList);
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
	      String query = "SELECT * FROM gu WHERE country=(?)";
	      String country = "korea";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, country);
	         rs = psmt.executeQuery();
	         
	         while (rs.next()) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("guName", rs.getString("gu_name"));
				   jsonObject.put("guCode", rs.getString("gu_code"));
				   jsonObject.put("regionCode", rs.getString("region_code"));
				   jsonObject.put("country", rs.getString("country"));
				   
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
