package search;


import java.util.List;

import lombok.Data;

@Data
public class MainSearchDTO {
	
	private int idx; // index값, 기본키가 아니기 때문에 중복 값 존재 할 수 있음
	/*
	 * 지역정보
	 * region_name : 지역명 (ex. 서울, 부산)
	 * region_code : 지역 코드 (ex. 11 : 서울 (incruit 기준))
	 * job_site : 채용 사이트명 (jobkorea, incruit, saramin)
	 */
	private String region_name;
	private String region_code;
	private String job_site;
	
	/*
	 * 구 정보
	 * gu_name : 구 이름 (ex. 강남구, 동대문구)
	 * gu_code : 구 코드 (ex 1111 : 강남구 (incruit 기준))
	 */
	private String gu_name;
	private String gu_code;
	
	private List<Object> regionData;
	
	
}	
