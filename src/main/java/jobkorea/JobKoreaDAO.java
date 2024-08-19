package jobkorea;

import javax.servlet.ServletContext;
import common.DBConnect;

public class JobKoreaDAO extends DBConnect {
	
	public JobKoreaDAO(ServletContext application) {
		super(application);
	}

	public JobKoreaDTO test() {
		JobKoreaDTO jobKoreaDTO = new JobKoreaDTO();
		jobKoreaDTO.name = "seresr";

		return jobKoreaDTO;
	}
}
