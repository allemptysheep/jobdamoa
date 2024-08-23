package recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;

import common.DBConnect;

public class RecruitmentDAO extends DBConnect {
		public RecruitmentDAO (ServletContext application) {
	      super(application);
	   }
		
		public int registerRecruitment(String rec_title, String rec_contents, String c_name, String rec_hire_type, String rec_work_history, String region_name, String region_code, String gu_name, String gu_code, String rec_apply_startdate, String rec_apply_enddate, String rec_apply_method, String m_email) {
			int rs = 0;
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMdd");
			
			 
			
			String sql = "INSERT INTO recruitment(rec_title, rec_contents, c_name, rec_hire_type, rec_work_history, region_name, region_code, gu_name, gu_code, rec_apply_startdate, rec_apply_enddate, rec_apply_method, m_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		      
		      try {
		    	 java.util.Date startdate_unconverted = sdf.parse(rec_apply_startdate);
			     long temp_starttime = startdate_unconverted.getTime();
			     java.sql.Date startdate_converted = new java.sql.Date(temp_starttime);
			     
			     // String 타입으로 선언된 날짜 정보를 데이터베이스에 넣기 위해 변환처리
			         
			     java.util.Date enddate_unconverted = sdf.parse(rec_apply_enddate);
			     long temp_endtime = enddate_unconverted.getTime();
			     java.sql.Date enddate_converted = new java.sql.Date(temp_endtime);
		    	  
		         psmt = con.prepareStatement(sql);
		         psmt.setString(1, rec_title);
		         psmt.setString(2, rec_contents);
		         psmt.setString(3, c_name);
		         psmt.setString(4, rec_hire_type);
		         psmt.setString(5, rec_work_history);
		         psmt.setString(6, region_name);
		         psmt.setString(7, region_code);
		         psmt.setString(8, gu_name);
		         psmt.setString(9, gu_code);
		         psmt.setDate(10, startdate_converted);
		         psmt.setDate(11, enddate_converted);
		         psmt.setString(12, rec_apply_method);
		         psmt.setString(13, m_email);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		}
		
		public RecruitmentDTO getRecruitList(int start, int end) {
			RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
			List<Object> recruitmentList = new ArrayList<Object>(); 
		    String query = "SELECT * FROM recruitment LIMIT ?, ?;";
		    
			try {
		         psmt = con.prepareStatement(query);
		         psmt.setInt(1, start);
				 psmt.setInt(2, end);
		         rs = psmt.executeQuery();
		         
		         while (rs.next()) {
					   JSONObject jsonObject = new JSONObject();
					   jsonObject.put("rec_idx", rs.getInt("rec_idx"));
					   jsonObject.put("rec_title", rs.getString("rec_title"));
					   jsonObject.put("rec_contents", rs.getString("rec_contents"));
					   jsonObject.put("c_name", rs.getString("c_name"));
					   jsonObject.put("rec_hire_type", rs.getString("rec_hire_type"));
					   jsonObject.put("rec_work_history", rs.getString("rec_work_history"));
					   jsonObject.put("region_name", rs.getString("region_name"));
					   jsonObject.put("region_code", rs.getString("region_code"));
					   jsonObject.put("gu_name", rs.getString("gu_name"));
					   jsonObject.put("gu_code", rs.getString("gu_code"));
					   jsonObject.put("rec_apply_startdate", rs.getDate("rec_apply_startdate"));
					   jsonObject.put("rec_apply_enddate", rs.getDate("rec_apply_enddate"));
					   jsonObject.put("rec_apply_method", rs.getString("rec_apply_method"));
					   jsonObject.put("m_email", rs.getString("m_email"));
					   
					   recruitmentList.add(jsonObject);
		         }
		         System.out.println(recruitmentList);
		         recruitmentDTO.setRecruitmentData(recruitmentList);
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
			
			return recruitmentDTO;
		}
		
		
		// 전체 호출
		public RecruitmentDTO getRecruitList() {
			RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
			List<Object> recruitmentList = new ArrayList<Object>(); 
		    String query = "SELECT * FROM recruitment;";
		    
			try {
		         psmt = con.prepareStatement(query);
		         rs = psmt.executeQuery();
		         
		         while (rs.next()) {
					   JSONObject jsonObject = new JSONObject();
					   jsonObject.put("rec_idx", rs.getInt("rec_idx"));
					   jsonObject.put("rec_title", rs.getString("rec_title"));
					   jsonObject.put("rec_contents", rs.getString("rec_contents"));
					   jsonObject.put("c_name", rs.getString("c_name"));
					   jsonObject.put("rec_hire_type", rs.getString("rec_hire_type"));
					   jsonObject.put("rec_work_history", rs.getString("rec_work_history"));
					   jsonObject.put("region_name", rs.getString("region_name"));
					   jsonObject.put("region_code", rs.getString("region_code"));
					   jsonObject.put("gu_name", rs.getString("gu_name"));
					   jsonObject.put("gu_code", rs.getString("gu_code"));
					   jsonObject.put("rec_apply_startdate", rs.getDate("rec_apply_startdate"));
					   jsonObject.put("rec_apply_enddate", rs.getDate("rec_apply_enddate"));
					   jsonObject.put("rec_apply_method", rs.getString("rec_apply_method"));
					   jsonObject.put("m_email", rs.getString("m_email"));
					   
					   recruitmentList.add(jsonObject);
		         }
		         System.out.println(recruitmentList);
		         recruitmentDTO.setRecruitmentData(recruitmentList);
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
			
			return recruitmentDTO;
		}
		
		public RecruitmentDTO getRecruitDTO(int rec_idx) {
			RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
		    String query = "SELECT * FROM recruitment WHERE rec_idx = (?);";
		    
			try {
		         psmt = con.prepareStatement(query);
		         psmt.setInt(1, rec_idx);
		         rs = psmt.executeQuery();
		         while (rs.next()) {
					  recruitmentDTO.setRec_idx(rs.getInt("rec_idx"));
					  recruitmentDTO.setRec_title(rs.getString("rec_title"));
					  recruitmentDTO.setRec_contents(rs.getString("rec_contents"));
					  recruitmentDTO.setC_name(rs.getString("c_name"));
					  recruitmentDTO.setRec_hire_type(rs.getString("rec_hire_type"));
					  recruitmentDTO.setRec_work_history(rs.getString("rec_work_history"));
					  recruitmentDTO.setRegion_name(rs.getString("region_name"));
					  recruitmentDTO.setRegion_code(rs.getString("region_code"));
					  recruitmentDTO.setGu_name(rs.getString("gu_name"));
					  recruitmentDTO.setGu_code(rs.getString("gu_code"));
					  recruitmentDTO.setRec_apply_startdate(rs.getString("rec_apply_startdate").replace("-", ""));
					  recruitmentDTO.setRec_apply_enddate(rs.getString("rec_apply_enddate").replace("-", ""));
					  recruitmentDTO.setRec_apply_method(rs.getString("rec_apply_method"));
					  recruitmentDTO.setM_email(rs.getString("m_email"));
		         }
		         System.out.println("조회");
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
			
			return recruitmentDTO;
		}
		
		public int editRecruitment(String rec_title, String rec_contents, String c_name, String rec_hire_type, String rec_work_history, String region_name, String region_code, String gu_name, String gu_code, String rec_apply_startdate, String rec_apply_enddate, String rec_apply_method, int rec_idx) {
			int rs = 0;
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMdd");
			
			 
			
			String sql = "UPDATE recruitment SET rec_title = (?),  rec_contents = (?), c_name = (?), rec_hire_type = (?), rec_work_history = (?), region_name = (?), region_code = (?),"
					+ " gu_name = (?), gu_code = (?), rec_apply_startdate = (?), rec_apply_enddate = (?), rec_apply_method = (?) WHERE rec_idx = (?)";
		      
		      try {
		    	 java.util.Date startdate_unconverted = sdf.parse(rec_apply_startdate);
			     long temp_starttime = startdate_unconverted.getTime();
			     java.sql.Date startdate_converted = new java.sql.Date(temp_starttime);
			     
			     // String 타입으로 선언된 날짜 정보를 데이터베이스에 넣기 위해 변환처리
			         
			     java.util.Date enddate_unconverted = sdf.parse(rec_apply_enddate);
			     long temp_endtime = enddate_unconverted.getTime();
			     java.sql.Date enddate_converted = new java.sql.Date(temp_endtime);
		    	  
		         psmt = con.prepareStatement(sql);
		         psmt.setString(1, rec_title);
		         psmt.setString(2, rec_contents);
		         psmt.setString(3, c_name);
		         psmt.setString(4, rec_hire_type);
		         psmt.setString(5, rec_work_history);
		         psmt.setString(6, region_name);
		         psmt.setString(7, region_code);
		         psmt.setString(8, gu_name);
		         psmt.setString(9, gu_code);
		         psmt.setDate(10, startdate_converted);
		         psmt.setDate(11, enddate_converted);
		         psmt.setString(12, rec_apply_method);
		         psmt.setInt(13, rec_idx);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		}
		
		public int deleteRecruitment(int rec_idx, String m_email) {
			int rs = 0;
			
			String sql = "DELETE FROM recruitment WHERE rec_idx = (?) AND m_email = (?)";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, rec_idx);
				psmt.setString(2, m_email);
				
				rs = psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return rs;
		}
		
		public int selectCount() {

			int totalCount=0;
			
			String sql="select count(*) as cnt from recruitment";
			
			
			try {
				psmt=con.prepareStatement(sql);
				
				rs=psmt.executeQuery();
				
				//검색 건수가 1건이므로 
				rs.next();
				
				//검색 총건수
				totalCount=rs.getInt("cnt");
				
				System.out.println("totalCount:"+totalCount);
				
			} catch (Exception e) {
				System.out.println("오류");
				e.printStackTrace();
			}
			
			return totalCount;
		}
}
