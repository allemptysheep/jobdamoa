package resume;


import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;


import common.DBConnect;

public class ResumeDAO extends DBConnect{
	// 데이터 처리 함수
	public ResumeDAO (ServletContext application) {
	      super(application);
	 }
	
	   // 
	   public int insertResumeInfo(String email, String name) {
	      int rs = 0;
	      
	      String sql = "INSERT INTO resume_info(M_EMAIL,NAME) VALUES (?, ?)";
	      try {
	         psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	         psmt.setString(1, email);
	         psmt.setString(2, name);
	         
//				rs = psmt.executeUpdate();
	         ResultSet key = psmt.getGeneratedKeys();
	         if(key.next()) {
	        	 rs = key.getInt(1);
					/* System.out.println("key : " + key.getInt(1)); */
	         }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }

	      return rs;
	   }
	   
	   public int insertResumeBasic(int key,String mname,String ename,String birth,String phone,String email,String z1,String z2,String z3,String z4,String z5) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_basic VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,email);
		         psmt.setString(3,mname);
		         psmt.setString(4,ename);
		         psmt.setString(5,birth);
		         psmt.setString(6,phone);
		         psmt.setString(7,z1);
		         psmt.setString(8,z2);
		         psmt.setString(9,z3);
		         psmt.setString(10,z4);
		         psmt.setString(11,z5);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeEdu(int key,String h1,String h2,String h3,String h4,String h5) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_edu(resume_info_idx,edu_name,edu_class,edu_ent,edu_grad,edu_grad_en) VALUES (?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,h1);
		         psmt.setString(3,h2);
		         psmt.setString(4,h3);
		         psmt.setString(5,h4);
		         psmt.setString(6,h5);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeExp(int key,String wloc,String wrank,String mwork,String swork,String ework) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_exp(resume_info_idx,exp_loc,exp_rank,exp_resp,exp_start_work,exp_end_work) VALUES (?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,wloc);
		         psmt.setString(3,wrank);
		         psmt.setString(4,mwork);
		         psmt.setString(5,swork);
		         psmt.setString(6,ework);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeQua(int key,String dqua,String nqua,String rqua,String pqua) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_qua(resume_info_idx,qua_date,qua_name,qua_rank,qua_appr) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,dqua);
		         psmt.setString(3,nqua);
		         psmt.setString(4,rqua);
		         psmt.setString(5,pqua);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeHope(int key,String jhope,String phope,String lhope,String presv) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_hope(resume_info_idx,hope_job,hope_pay,hope_loc,hope_pres) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,jhope);
		         psmt.setString(3,phope);
		         psmt.setString(4,lhope);
		         psmt.setString(5,presv);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeExt(int key,String dact,String cact,String nact,String hact) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_ext(resume_info_idx,ext_date,ext_content,ext_name,ext_ect) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,dact);
		         psmt.setString(3,cact);
		         psmt.setString(4,nact);
		         psmt.setString(5,hact);
		         
//		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   
	   public int updateResumeInfo(String name,int key,String mEmail) {
		   int rs = 0;
		   ResultSet rs_s = null;

		   
		   String sql = "update resume_info set name = ? where resume_info_idx = ? and m_email = ?";
		   String sql_s = "select * from resume_info";
		   try {
		   psmt = con.prepareStatement(sql);
		   stmt = con.createStatement();
		   rs_s = stmt.executeQuery(sql_s);
		   
		   if(rs_s.next()) {
			   int idx = rs_s.getInt("resume_info_idx");
			   
		   psmt.setString(1, name);
		   psmt.setInt(2, idx);
		   psmt.setString(3, mEmail);
		   		   
		   System.out.println("idx : "+idx);
//		   rs = psmt.executeUpdate();
		   }
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   
}
