package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;

import common.DBConnect;
import recruitment.RecruitmentDTO;
import region.RegionDTO;

public class MainSearchDAO extends DBConnect {
	public MainSearchDAO(ServletContext application) {
		super(application);
	}

	public MainSearchListDTO mainSearch(String keyword, String[] regionSList, String[] guSList, String start, String end) {
		MainSearchListDTO mainSearchListDTO = new MainSearchListDTO();
		List<Object> mainSearchList = new ArrayList<Object>();

		ArrayList<String> regionList = new ArrayList<>();
		regionList.addAll(Arrays.asList(regionSList));
		ArrayList<String> guList = new ArrayList<>();
		guList.addAll(Arrays.asList(guSList));

		System.out.println(regionList);
		System.out.println(guList);

		String query = "";
		query += "SELECT * FROM (" + "SELECT recruitment.*, @ROWNUM := @ROWNUM+1 AS rNum FROM (";
		query += " SELECT * FROM  recruitment WHERE ";

		for (int i = 0; i < guList.size(); i++) {
			String code = guList.get(i);
			System.out.println("code : " + code);

			boolean isContains = regionList.contains(code);
			if (i > 0) {
				query += "OR ";
			}

			if (isContains) {
				System.out.println("포함 됨 : " + code);
				query += "region_code = " + code + " ";

			} else {
				System.out.println("포함 안됨 : " + code);
				query += "gu_code = " + code + " ";

			}
		}

		if (keyword != "") {
			keyword = "%" + keyword + "% ";
			query += "AND rec_contents LIKE " + keyword;
		}
		
		query += " ORDER BY rec_idx DESC " + ") recruitment, (SELECT @ROWNUM := 0) tmp" + ") AS boardT"
				+ " WHERE rNum BETWEEN (?) AND (?) ";
		

		System.out.println(query);

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, start);
			psmt.setString(2, end);
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

				mainSearchList.add(jsonObject);
			}
			System.out.println(mainSearchList);
			mainSearchListDTO.setMainSearchData(mainSearchList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mainSearchListDTO;
	}
	
	public int selectCount(String keyword, String[] regionSList, String[] guSList) {
		int totalCount=0;

		ArrayList<String> regionList = new ArrayList<>();
		regionList.addAll(Arrays.asList(regionSList));
		ArrayList<String> guList = new ArrayList<>();
		guList.addAll(Arrays.asList(guSList));

		System.out.println(regionList);
		System.out.println(guList);

		String query = "";
		query += "select count(*) as cnt FROM recruitment WHERE ";

		for (int i = 0; i < guList.size(); i++) {
			String code = guList.get(i);
			System.out.println("code : " + code);

			boolean isContains = regionList.contains(code);
			if (i > 0) {
				query += "OR ";
			}

			if (isContains) {
				System.out.println("포함 됨 : " + code);
				query += "region_code = " + code + " ";

			} else {
				System.out.println("포함 안됨 : " + code);
				query += "gu_code = " + code + " ";

			}
		}

		if (keyword != "") {
			keyword = "%" + keyword + "% ";
			query += "AND rec_contents LIKE " + keyword;
		}

		System.out.println(query);

		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();

			//검색 건수가 1건이므로 
			rs.next();
			
			//검색 총건수
			totalCount=rs.getInt("cnt");
			
			System.out.println("totalCount:"+totalCount);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return totalCount;
	}
}