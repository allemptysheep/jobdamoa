package admin;

import javax.servlet.ServletContext;

import common.DBConnect;

public class AdminRegionDAO extends DBConnect {
	
	   public AdminRegionDAO (ServletContext application) {
		      super(application);
		   }

	   // 지역 정보 삽입
	   public int insertRegion(String regionName, String regionCode, String country) {
		   int rs = 0;
		   
	      String sql = "INSERT INTO region(region_name, region_code, country) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE region_name = (?)";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setString(1, regionName);
	         psmt.setString(2, regionCode);
	         psmt.setString(3, country);
	         psmt.setString(4, regionName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   // 구 정보 삽입
	   public int insertGu(String guName, String guCode, String regionCode, String country) {
		   int rs = 0;
		   
	      String sql = "INSERT INTO gu(gu_name, gu_code, region_code, country) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE gu_name = (?)";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setString(1, guName);
	         psmt.setString(2, guCode);
	         psmt.setString(3, regionCode);
	         psmt.setString(4, country);
	         psmt.setString(5, guName);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   public AdminRegionDTO selectRegion(String regionCode) {
		   AdminRegionDTO adminRegionDTO = new AdminRegionDTO();
		   
		   String query = "SELECT * FROM region WHERE region_code = (?) ";
		      
		      try {
		         psmt = con.prepareStatement(query);
		         psmt.setString(1, regionCode);
		         rs = psmt.executeQuery();
		         
		         if (rs.next()) {
		            adminRegionDTO.setIdx(rs.getInt("idx"));
		            adminRegionDTO.setRegionName(rs.getString("region_name"));
		            adminRegionDTO.setRegionCode(rs.getString("region_code"));
		            adminRegionDTO.setCountry(rs.getString("country"));
		         }
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
		     
		   return adminRegionDTO;
	   }
	   
	   public AdminGuDTO selectGu(String guCode) {
		   AdminGuDTO adminGuDTO = new AdminGuDTO();
		   
		   String query = "SELECT * FROM gu WHERE gu_code = (?) ";
		      
		      try {
		         psmt = con.prepareStatement(query);
		         psmt.setString(1, guCode);
		         rs = psmt.executeQuery();
		         
		         if (rs.next()) {
		            adminGuDTO.setGuName(rs.getString("gu_name"));
		            adminGuDTO.setGuCode(rs.getString("gu_code"));
		            adminGuDTO.setRegionCode(rs.getString("region_code"));
		            adminGuDTO.setCountry(rs.getString("country"));
		         }
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
		      
		   return adminGuDTO;
	   }
	   
	   // 수정
	   public int regionEdit(int idx, String regionName, String regionCode, String country) {
	      int rs = 0;
	      String query = "UPDATE region SET region_name = (?), region_code = (?), country = (?) WHERE idx = (?)"; 
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, regionName);
	         psmt.setString(2, regionCode);
	         psmt.setString(3, country);
	         psmt.setInt(4, idx);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   // 수정
	   public int guEdit(String guName, String guCode, String regionCode, String country) {
	      int rs = 0;
	      String query = "UPDATE gu SET gu_name = (?), region_code = (?), country = (?) WHERE gu_code = (?)"; 
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, guName);
	         psmt.setString(2, regionCode);
	         psmt.setString(3, country);
	         psmt.setString(4, guCode);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   public int regionDelete(int idx) {
		  int rs = 0;
	      String query = "DELETE FROM region WHERE idx = (?)"; 
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setInt(1, idx);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   public int guDelete(String guCode) {
		  int rs = 0;
	      String query = "DELETE FROM gu WHERE gu_code= (?)"; 
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, guCode);
	         
	         rs = psmt.executeUpdate();
	         
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
}
