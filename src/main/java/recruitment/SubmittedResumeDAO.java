package recruitment;

import javax.servlet.ServletContext;

import common.DBConnect;

public class SubmittedResumeDAO extends DBConnect {
	public SubmittedResumeDAO (ServletContext application) {
	      super(application);
	   }
	
	public int selectExists(String recIdx, String mEmail) {
		int result = 0;
		
	    String query = "SELECT EXISTS(SELECT * FROM submitted_resume WHERE rec_idx = (?) AND m_email = (?)) AS result;";
	    
		try {
	         psmt = con.prepareStatement(query);
	         psmt.setInt(1,  Integer.parseInt(recIdx));
	         psmt.setString(2,  mEmail);
	         rs = psmt.executeQuery();
	         
	         if(rs.next()) {
	        	 result = rs.getInt("result");
	         }

	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
		
		return result;
	}

	   public int insertSubmittedResume(String recIdx, String resumeInfoIdx, String mEmail) {
		      int rs = 0;
		      
		     
		      String sql = "INSERT INTO submitted_resume(rec_idx, resume_info_idx, m_email) VALUES (?,?,?)";
		      try {
		    	 
		    	 psmt = con.prepareStatement(sql);
		    	  
		         psmt.setInt(1, Integer.parseInt(recIdx));
		         psmt.setInt(2,  Integer.parseInt(resumeInfoIdx));
		         psmt.setString(3, mEmail);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }

		      return rs;
		   }
}
