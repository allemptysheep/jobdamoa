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
	   
	   public int insertResumeBasic(int key,String mname,String ename,String birth,String phone,String email,String zipCode,String roadAdd,String jibunAdd,String detailAdd,String refAdd) {
		      int rs = 0;
		     
		      String sql = "INSERT INTO resume_basic(resume_info_idx,email,m_name,m_eng_name,m_birth,m_phone_num,m_zipcode,m_road_address,m_jibun_address,m_detail_address,m_ref_address) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,email);
		         psmt.setString(3,mname);
		         psmt.setString(4,ename);
		         psmt.setString(5,birth);
		         psmt.setString(6,phone);
		         psmt.setString(7,zipCode);
		         psmt.setString(8,roadAdd);
		         psmt.setString(9,jibunAdd);
		         psmt.setString(10,detailAdd);
		         psmt.setString(11,refAdd);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeEdu(int key,String eduName,String eduClass,String eduEnt,String eduGrad,String eduGradEn) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_edu(resume_info_idx,edu_name,edu_class,edu_ent,edu_grad,edu_grad_en) VALUES (?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,eduName);
		         psmt.setString(3,eduClass);
		         psmt.setString(4,eduEnt);
		         psmt.setString(5,eduGrad);
		         psmt.setString(6,eduGradEn);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeExp(int key,String expLoc,String expRank,String expResp,String expSWork,String expEWork) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_exp(resume_info_idx,exp_loc,exp_rank,exp_resp,exp_start_work,exp_end_work) VALUES (?,?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,expLoc);
		         psmt.setString(3,expRank);
		         psmt.setString(4,expResp);
		         psmt.setString(5,expSWork);
		         psmt.setString(6,expEWork);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeQua(int key,String quaDate,String quaName,String quaRank,String quaAppr) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_qua(resume_info_idx,qua_date,qua_name,qua_rank,qua_appr) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,quaDate);
		         psmt.setString(3,quaName);
		         psmt.setString(4,quaRank);
		         psmt.setString(5,quaAppr);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeHope(int key,String hopeJob,String hopePay,String hopeLoc,String hopePres) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO resume_hope(resume_info_idx,hope_job,hope_pay,hope_loc,hope_pres) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,hopeJob);
		         psmt.setString(3,hopePay);
		         psmt.setString(4,hopeLoc);
		         psmt.setString(5,hopePres);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	   public int insertResumeExt(int key,String extDate,String extContent,String extName,String extEct) {
		      int rs = 0;
		      
		      // ext_date
		      // extDate
		     
		      String sql = "INSERT INTO resume_ext(resume_info_idx,ext_date,ext_content,ext_name,ext_ect) VALUES (?,?,?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1,key);
		         psmt.setString(2,extDate);
		         psmt.setString(3,extContent);
		         psmt.setString(4,extName);
		         psmt.setString(5,extEct);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
	  	   
	   // resume list select
	   public List<Object>  selectResumeList(String mEmail) {
		  ResumeListDTO resumeListDTO = new ResumeListDTO();
		  List<Object> rList = new ArrayList<Object>(); 
	      String query = "SELECT * FROM resume_info WHERE m_email = (?)";
	      
	      try {
	         psmt = con.prepareStatement(query);
	         psmt.setString(1,mEmail);
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
		   String idx = resumeInfoIdx;
		   
		   String sql = "select distinct(i.resume_info_idx),i.m_email,i.name,b.email,b.m_name,b.m_eng_name,b.m_birth,b.m_phone_num,b.m_zipcode,b.m_road_address,b.m_jibun_address,b.m_detail_address,b.m_ref_address,e.edu_name,e.edu_class,e.edu_ent,e.edu_grad,e.edu_grad_en,p.exp_loc,p.exp_rank,p.exp_resp,p.exp_start_work,p.exp_end_work,t.ext_date,t.ext_content,t.ext_name,t.ext_ect,h.hope_job,h.hope_pay,h.hope_loc,h.hope_pres,q.qua_date,q.qua_name,q.qua_rank,q.qua_appr from resume_info as i\r\n"
				   	  + "left join resume_basic as b on b.resume_info_idx = i.resume_info_idx\r\n"
				   	  + "left join resume_edu as e on e.resume_info_idx = i.resume_info_idx\r\n"
				   	  + "left join resume_exp as p on p.resume_info_idx = i.resume_info_idx\r\n"
				   	  + "left join resume_qua as q on q.resume_info_idx = i.resume_info_idx\r\n"
				   	  + "left join resume_hope as h on h.resume_info_idx = i.resume_info_idx\r\n"
				   	  + "left join resume_ext as t on t.resume_info_idx = i.resume_info_idx\r\n"	
				   	  + "where i.resume_info_idx ="+idx;

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
					resumeDTO.setZipcode(rs.getString("m_zipcode"));
					resumeDTO.setRoadAdd(rs.getString("m_road_address"));
					resumeDTO.setJibunAdd(rs.getString("m_jibun_address"));
					resumeDTO.setDetailAdd(rs.getString("m_detail_address"));
					resumeDTO.setRefAdd(rs.getString("m_ref_address"));
					resumeDTO.setEduName(rs.getString("edu_name"));
					resumeDTO.setEduClass(rs.getString("edu_class"));
					resumeDTO.setEduEnt(rs.getString("edu_ent"));
					resumeDTO.setEduGrad(rs.getString("edu_grad"));
					resumeDTO.setEduGradEn(rs.getString("edu_grad_en"));
					resumeDTO.setExpLoc(rs.getString("exp_loc"));
					resumeDTO.setExpRank(rs.getString("exp_rank"));
					resumeDTO.setExpResp(rs.getString("exp_resp"));
					resumeDTO.setExpSWork(rs.getString("exp_start_work"));
					resumeDTO.setExpEWork(rs.getString("exp_end_work"));
					resumeDTO.setQuaDate(rs.getString("qua_date"));
					resumeDTO.setQuaName(rs.getString("qua_name"));
					resumeDTO.setQuaRank(rs.getString("qua_rank"));
					resumeDTO.setQuaAppr(rs.getString("qua_appr"));
					resumeDTO.setHopeJob(rs.getString("hope_job"));
					resumeDTO.setHopePay(rs.getString("hope_pay"));
					resumeDTO.setHopeLoc(rs.getString("hope_loc"));
					resumeDTO.setHopePres(rs.getString("hope_pres"));
					resumeDTO.setExtDate(rs.getString("ext_date"));
					resumeDTO.setExtContent(rs.getString("ext_content"));
					resumeDTO.setExtName(rs.getString("ext_name"));
					resumeDTO.setExtEct(rs.getString("ext_ect"));

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
	   public int updateResumeBasic(String mname,String ename,String birth,String phone,String email,String zipCode,String roadAdd,String jibunAdd,String detailAdd,String refAdd,int key) {
		   int rs = 0;
		   
		   String sql = "update resume_basic set m_name = ?,m_eng_name = ?,m_birth = ?,m_phone_num = ?,email = ?,m_zipcode = ?,m_road_address = ?,m_jibun_address = ?,m_detail_address = ?,m_ref_address = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
	         psmt.setString(1,mname);
	         psmt.setString(2,ename);
	         psmt.setString(3,birth);
	         psmt.setString(4,phone);
	         psmt.setString(5,email);
	         psmt.setString(6,zipCode);
	         psmt.setString(7,roadAdd);
	         psmt.setString(8,jibunAdd);
	         psmt.setString(9,detailAdd);
	         psmt.setString(10,refAdd);
             psmt.setInt(11,key);


		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   
	   public int updateResumeEdu(String eduName,String eduClass,String eduEnt,String eduGrad,String eduGradEn,int key) {
		   int rs = 0;
		   
		   String sql = "update resume_edu set edu_name = ?,edu_class = ?,edu_ent = ?,edu_grad = ?,edu_grad_en = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, eduName);
		   psmt.setString(2, eduClass);
		   psmt.setString(3, eduEnt);
		   psmt.setString(4, eduGrad);
		   psmt.setString(5, eduGradEn);
		   psmt.setInt(6, key);
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeExp(String expLoc,String expRank,String expResp,String expSWork,String expEWork,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_exp set exp_loc = ?,exp_rank = ?,exp_resp = ?,exp_start_work = ?,exp_end_work = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, expLoc);
		   psmt.setString(2, expRank);
		   psmt.setString(3, expResp);
		   psmt.setString(4, expSWork);
		   psmt.setString(5, expEWork);
		   psmt.setInt(6, key);
		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeQua(String quaDate,String quaName,String quaRank,String quaAppr,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_qua set qua_date = ?,qua_name = ?,qua_rank = ?,qua_appr = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, quaDate);
		   psmt.setString(2, quaName);
		   psmt.setString(3, quaRank);
		   psmt.setString(4, quaAppr);
		   psmt.setInt(5, key);

		   		   
		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeHope(String hopeJob,String hopePay,String hopeLoc,String hopePres,int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "update resume_hope set hope_job = ?,hope_pay = ?,hope_loc = ?,hope_pres = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, hopeJob);
		   psmt.setString(2, hopePay);
		   psmt.setString(3, hopeLoc);
		   psmt.setString(4, hopePres);
		   psmt.setInt(5, key);

		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int updateResumeExt(String extDate,String extContent,String extName,String extEct,int key) {
		   int rs = 0;

		   
		   String sql = "update resume_ext set ext_date = ?,ext_content = ?,ext_name = ?,ext_ect = ? where resume_info_idx = ?";
		   try {
		   psmt = con.prepareStatement(sql);
		   
			   
		   psmt.setString(1, extDate);
		   psmt.setString(2, extContent);
		   psmt.setString(3, extName);
		   psmt.setString(4, extEct);
		   psmt.setInt(5, key);

		   rs = psmt.executeUpdate();
		   
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeInfo(int key) {
		   int rs = 0;
		   
		   String sql = "delete from resume_info where resume_info_idx = ?";
		   try {
			   psmt = con.prepareStatement(sql);
			   psmt.setInt(1, key);
			   rs = psmt.executeUpdate();
			   
			   System.out.println("delete resume info rs : " + rs);
		   
		   }catch (Exception e) {
			   e.printStackTrace();
		}
		   return rs;
	   }
	   public int deleteResumeBasic(int key) {
		   int rs = 0;

		   ResumeDTO resumeDTO = new ResumeDTO();
		   
		   
		   String sql = "delete from resume_basic where resume_info_idx = ?";
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
