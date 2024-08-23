package selfIntro;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;

import common.DBConnect;

public class SelfIntroDAO extends DBConnect{
	// 데이터 처리 함수
	public SelfIntroDAO (ServletContext application) {
	      super(application);
	 }
	
	   // 
	   public int insertselfIntroInfo(String sname,String squestion1,String squestion2,String squestion3,String mEmail) {
	      int rs = 0;
	      
	      String sql = "INSERT INTO selfintro_info(selfintro_name,selfintro_question1,selfintro_question2,selfintro_question3,m_email) VALUES (?,?,?,?,?)";
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setString(1, sname);
	         psmt.setString(2, squestion1);
	         psmt.setString(3, squestion2);
	         psmt.setString(4, squestion3);
	         psmt.setString(5, mEmail);

				rs = psmt.executeUpdate();
//	         ResultSet key = psmt.getGeneratedKeys();
//	         if(key.next()) {
//	        	 rs = key.getInt(1);
//					/* System.out.println("key : " + key.getInt(1)); */
//	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	  	  	   
	   // resume list select
	   public List<Object>  selectSelfIntroList(String mEmail) {
		  SelfIntroListDTO selfIntroListDTO = new SelfIntroListDTO();
		  List<Object> rList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM selfintro_info where m_email = (?)";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, mEmail);
	         rs = psmt.executeQuery();
	         
	         // while row 당 처리
	         while (rs.next()) {
	        	 // {data: "data"}
	        	rs.getInt("selfintro_idx");
	        	
				JSONObject jsonObject = new JSONObject();							// {}
				jsonObject.put("selfIntroIdx", rs.getInt("selfintro_idx"));		// {resumeInfoIdx: 1}
				jsonObject.put("mEmail", rs.getString("m_email"));					// {resumeInfoIdx: 1, mEmail: test@gmail.com}
				jsonObject.put("selfIntroName", rs.getString("selfintro_name"));					// {resumeInfoIdx: 1, mEmail: test@gmail.com, resumeName: test}
				
				rList.add(jsonObject);	// [{resumeInfoIdx: 1, mEmail: test@gmail.com, resumeName: test}]
	         }
	         
	         // [{resumeInfoIdx: 1, mEmail: test@gmail.com, resumeName: test}, {resumeInfoIdx: 2, mEmail: test@gmail.com, resumeName: test2}]
	         //
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rList;
	   }
	   

	   // selfIntro list select
	   public SelfIntroDTO selectselfIntro(String selfIntroIdx) {
		   SelfIntroDTO selfIntroDTO = new SelfIntroDTO();
		   int idx = Integer.parseInt(selfIntroIdx);
		   
		   String sql = "select * from selfintro_info where selfintro_idx ="+idx;

			try{
							
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
						
				
				if (rs.next()){
					 selfIntroDTO.setSname(rs.getString("selfintro_name"));
					 selfIntroDTO.setSquestion1(rs.getString("selfintro_question1"));
					 selfIntroDTO.setSquestion2(rs.getString("selfintro_question2"));
					 selfIntroDTO.setSquestion3(rs.getString("selfintro_question3"));

				}
				}catch (Exception e) {
					// TODO: handle exception
				}			
		   return selfIntroDTO;
	   }
	   
	   public int updateSelfIntroInfo(String sname,String squestion1,String squestion2,String squestion3) {
		   int rs = 0;

		   
		   String sql = "update selfintro_info set selfintro_name = ?,selfintro_question1 = ?,selfintro_question2 = ?,selfintro_question3 = ? where selfintro_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, sname);
		   psmt.setString(2, squestion1);
		   psmt.setString(3, squestion2);
		   psmt.setString(4, squestion3);
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteSelfIntroInfo(int idx,String mEmail) {
		   int rs = 0;

		   String sql = "delete from selfintro_info where selfintro_idx = ? and m_email = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, idx);
		   psmt.setString(2, mEmail);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
}