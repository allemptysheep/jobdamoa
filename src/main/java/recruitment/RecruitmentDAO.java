package recruitment;

import javax.servlet.ServletContext;

import common.DBConnect;

public class RecruitmentDAO extends DBConnect {
		public RecruitmentDAO (ServletContext application) {
	      super(application);
	   }
		
		public int registerRecruitment(String rec_title, String rec_contents) {
			int rs = 1;
			return rs;
		}
}
