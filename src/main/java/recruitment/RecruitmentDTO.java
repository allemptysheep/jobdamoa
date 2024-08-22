package recruitment;

import java.util.List;

import lombok.Data;

@Data
public class RecruitmentDTO {
	List<Object> RecruitmentData;
	int rec_idx;
	String rec_title;
	String rec_contents;
	String c_name;
	String rec_hire_type;
	String rec_work_history;
	String region_name;
	String region_code;
	String gu_name;
	String gu_code;
	String rec_apply_startdate;
	String rec_apply_enddate;
	String rec_apply_method;
	String m_email;
}
