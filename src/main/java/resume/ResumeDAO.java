package resume;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;

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
	         
				rs = psmt.executeUpdate();
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
		         
		         rs = psmt.executeUpdate();
		         
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
		         
		         rs = psmt.executeUpdate();
		         
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
		         
		         rs = psmt.executeUpdate();
		         
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
		         
		         rs = psmt.executeUpdate();
		         
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
		         
		         rs = psmt.executeUpdate();
		         
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
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	  	   
	   // resume list select
	   public List<Object>  selectResumeList() {
		  ResumeListDTO resumeListDTO = new ResumeListDTO();
		  List<Object> rList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM resume_info";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         rs = psmt.executeQuery();
	         
	         // while row 당 처리
	         while (rs.next()) {
	        	 // {data: "data"}
	        	rs.getInt("resume_info_idx");
	        	
				JSONObject jsonObject = new JSONObject();							// {}
				jsonObject.put("resumeInfoIdx", rs.getInt("resume_info_idx"));		// {resumeInfoIdx: 1}
				jsonObject.put("mEmail", rs.getString("m_email"));					// {resumeInfoIdx: 1, mEmail: test@gmail.com}
				jsonObject.put("resumeName", rs.getString("name"));					// {resumeInfoIdx: 1, mEmail: test@gmail.com, resumeName: test}
				
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
	   

	   // resume list select
	   public ResumeDTO selectResume(String resumeInfoIdx) {
		   ResumeDTO resumeDTO = new ResumeDTO();
		   int idx = Integer.parseInt(resumeInfoIdx);
		   
		   String sql = "select distinct(i.resume_info_idx),i.m_email,i.name,b.email,b.m_name,b.m_eng_name,b.m_birth,b.m_phone_num,b.m_zipcode,b.m_road_address,b.m_jibun_address,b.m_detail_address,b.m_ref_address,e.edu_name,e.edu_class,e.edu_ent,e.edu_grad,e.edu_grad_en,p.exp_loc,p.exp_rank,p.exp_resp,p.exp_start_work,p.exp_end_work,t.ext_date,t.ext_content,t.ext_name,t.ext_ect,h.hope_job,h.hope_pay,h.hope_loc,h.hope_pres,q.qua_date,q.qua_name,q.qua_rank,q.qua_appr from resume_info i,resume_basic b,resume_edu e,resume_exp p,resume_ext t,resume_hope h,resume_qua q where i.resume_info_idx = b.resume_info_idx = e.resume_info_idx = p.resume_info_idx = t.resume_info_idx = h.resume_info_idx = q.resume_info_idx and i.resume_info_idx ="+idx;

			try{
							
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
						
				
				if (rs.next()){
					 
					resumeDTO.setKey(rs.getInt("resume_info_idx"));
				    resumeDTO.setName(rs.getString("name"));
					resumeDTO.setMname(rs.getString("m_name"));
					resumeDTO.setEname(rs.getString("m_eng_name"));
					resumeDTO.setBirth(rs.getString("m_birth"));
					resumeDTO.setPhone(rs.getString("m_phone_num"));
					resumeDTO.setEmail(rs.getString("email"));
					resumeDTO.setZ1(rs.getString("m_zipcode"));
					resumeDTO.setZ2(rs.getString("m_road_address"));
					resumeDTO.setZ3(rs.getString("m_jibun_address"));
					resumeDTO.setZ4(rs.getString("m_detail_address"));
					resumeDTO.setZ5(rs.getString("m_ref_address"));
					resumeDTO.setH1(rs.getString("edu_name"));
					resumeDTO.setH2(rs.getString("edu_class"));
					resumeDTO.setH3(rs.getString("edu_ent"));
					resumeDTO.setH4(rs.getString("edu_grad"));
					resumeDTO.setH5(rs.getString("edu_grad_en"));
					resumeDTO.setWloc(rs.getString("exp_loc"));
					resumeDTO.setWrank(rs.getString("exp_rank"));
					resumeDTO.setMwork(rs.getString("exp_resp"));
					resumeDTO.setSwork(rs.getString("exp_start_work"));
					resumeDTO.setEwork(rs.getString("exp_end_work"));
					resumeDTO.setDqua(rs.getString("qua_date"));
					resumeDTO.setNqua(rs.getString("qua_name"));
					resumeDTO.setRqua(rs.getString("qua_rank"));
					resumeDTO.setPqua(rs.getString("qua_appr"));
					resumeDTO.setJhope(rs.getString("hope_job"));
					resumeDTO.setPhope(rs.getString("hope_pay"));
					resumeDTO.setLhope(rs.getString("hope_loc"));
					resumeDTO.setPresv(rs.getString("hope_pres"));
					resumeDTO.setDact(rs.getString("ext_date"));
					resumeDTO.setCact(rs.getString("ext_content"));
					resumeDTO.setNact(rs.getString("ext_name"));
					resumeDTO.setHact(rs.getString("ext_ect"));

				}
				}catch (Exception e) {
					// TODO: handle exception
				}			
		   return resumeDTO;
	   }
	   
	   public int updateResumeInfo(String name,int key,String mEmail) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_info set name = ? where resume_info_idx = ? and m_email = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, name);
		   psmt.setInt(2, key);
		   psmt.setString(3, mEmail);
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeBasic(String mname,String ename,String birth,String phone,String email,String z1,String z2,String z3,String z4,String z5,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_basic set m_name = ?,m_eng_name = ?,m_birth = ?,m_phone_num = ?,email = ?,m_zipcode = ?,"
 		   			  + "m_road_address = ?,m_jibun_address = ?,m_detail_address = ?,m_ref_address = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
		   psmt.setString(1, mname);
		   psmt.setString(2, ename);
		   psmt.setString(3, birth);
		   psmt.setString(4, phone);
		   psmt.setString(5, email);
		   psmt.setString(6, z1);
		   psmt.setString(7, z2);
		   psmt.setString(8, z3);
		   psmt.setString(9, z4);
		   psmt.setString(10, z5);
		   psmt.setInt(11, key);

		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeEdu(String h1,String h2,String h3,String h4,String h5,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_edu set edu_name = ?,edu_class = ?,edu_ent = ?,edu_grad = ?,edu_grad_en = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, h1);
		   psmt.setString(2, h2);
		   psmt.setString(3, h3);
		   psmt.setString(4, h4);
		   psmt.setString(5, h5);
		   psmt.setInt(6, key);
		   		   
//		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeExp(String wloc,String wrank,String mwork,String swork,String ework,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_exp set exp_loc = ?,exp_rank = ?,exp_resp = ?,exp_start_work = ?,exp_end_work = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, wloc);
		   psmt.setString(2, wrank);
		   psmt.setString(3, mwork);
		   psmt.setString(4, swork);
		   psmt.setString(5, ework);
		   psmt.setInt(6, key);
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeQua(String dqua,String nqua,String rqua,String pqua,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_qua set qua_date = ?,qua_name = ?,qua_rank = ?,qua_appr = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, dqua);
		   psmt.setString(2, nqua);
		   psmt.setString(3, rqua);
		   psmt.setString(4, pqua);
		   psmt.setInt(5, key);

		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeHope(String jhope,String phope,String lhope,String presv,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_hope set hope_job = ?,hope_pay = ?,hope_loc = ?,hope_pres = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, jhope);
		   psmt.setString(2, phope);
		   psmt.setString(3, lhope);
		   psmt.setString(4, presv);
		   psmt.setInt(5, key);

		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeExt(String dact,String cact,String nact,String hact,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_ext set ext_date = ?,ext_content = ?,ext_name = ?,ext_ect = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, dact);
		   psmt.setString(2, cact);
		   psmt.setString(3, nact);
		   psmt.setString(4, hact);
		   psmt.setInt(5, key);

		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeInfo(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_info where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeBasic(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_info where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeEdu(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_edu where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeExp(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_exp where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeQua(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_qua where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeHope(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_hope where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeExt(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_ext where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setInt(1, key);
		   
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
}
