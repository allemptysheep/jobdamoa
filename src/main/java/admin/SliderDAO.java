package admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import common.DBConnect;


public class SliderDAO extends DBConnect{
	public SliderDAO(ServletContext application) {
		super(application);
	}
	
	// slider 저장
	public int insertMainSlider(String sliderName, String oFileName, String sFileName) {
		int rs = 0;
		String sql = "INSERT INTO slider(s_name, s_file, s_file_o, s_regdate, s_update) VALUES (?, ?, ?, NOW(), NOW())";
		try {
			psmt = con.prepareStatement(sql);
			//
			psmt.setString(1, sliderName);
			psmt.setString(2, oFileName);
			psmt.setString(3, sFileName);

			rs = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	// slider 삭제
	public int deleteMainSlider(String sliderName) {
		int rs = 0;
		String sql = "DELETE FROM slider WHERE s_name = (?)";
		try {
			psmt = con.prepareStatement(sql);
			//
			psmt.setString(1, sliderName);

			rs = psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	// slider 가져오기
	public SliderDTO selectSlider(String sliderName) {
		SliderDTO sliderDTO = new SliderDTO();
		List<Object> slideList = new ArrayList<Object>();
		
		String sql = "SELECT * FROM slider WHERE s_name = (?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, sliderName);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sliderIdx", rs.getInt("s_idx"));
				jsonObject.put("sliderName", rs.getString("s_name"));
				jsonObject.put("sliderFile", rs.getString("s_file"));
				jsonObject.put("sliderFileO", rs.getString("s_file_o"));
				jsonObject.put("sliderRegdate", rs.getDate("s_regdate"));
				jsonObject.put("sliderUpdate", rs.getDate("s_update"));
				
				slideList.add(jsonObject);
			}
			
			sliderDTO.setSliderData(slideList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return sliderDTO;
	}
	
	// 모든 slider 가져오기
		public SliderDTO selectAllSlider() {
			SliderDTO sliderDTO = new SliderDTO();
			List<Object> slideList = new ArrayList<Object>();
			
			String sql = "SELECT * FROM slider";
			try {
				psmt = con.prepareStatement(sql);
				
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("sliderIdx", rs.getInt("s_idx"));
					jsonObject.put("sliderName", rs.getString("s_name"));
					jsonObject.put("sliderFile", rs.getString("s_file"));
					jsonObject.put("sliderFileO", rs.getString("s_file_o"));
					jsonObject.put("sliderRegdate", rs.getDate("s_regdate"));
					jsonObject.put("sliderUpdate", rs.getDate("s_update"));
					
					slideList.add(jsonObject);
				}
				
				sliderDTO.setSliderData(slideList);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return sliderDTO;
		}
}
