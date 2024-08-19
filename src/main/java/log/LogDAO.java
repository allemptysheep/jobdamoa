package log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import common.DBConnect;
import member.MemberDTO;

public class LogDAO extends DBConnect{
	
	public LogDAO (ServletContext application) {
		super(application);
	}
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    // logger.info("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        // logger.info("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        // logger.info(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        // logger.info("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        // logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        // logger.info("> getRemoteAddr : "+ip);
	    }
	    // logger.info("> Result : IP Address : "+ip);

	    return ip;
	}
	
	public int insertLog(HttpServletRequest request, HttpSession session, String text) {
		String ip = getClientIP(request);
		Locale locale = request.getLocale();
		String mEmail = (String)session.getAttribute("mEmail");
		
		// System.out.println(ip);
		// System.out.println(locale);
		// System.out.println(mEmail);
		
		int rs = 0;
		
		String sql = "INSERT INTO log(log_ip, log_locale, log_text, log_datetime, m_email) VALUES (?, ?, ?, NOW(), ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, ip);
			psmt.setString(2, locale.toString());
			psmt.setString(3, text);
			psmt.setString(4, mEmail);
			
			rs = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int selectLogCount(String logText, String memberEmail) {

		int totalCount = 0;

		System.out.println("----- select log count -----");
		String query = "SELECT count(*) FROM log WHERE log_text LIKE '%" + logText + "%'";
		if(memberEmail != "") {
			memberEmail = "%"+memberEmail+"%";
			query += " AND m_email LIKE " + memberEmail;
		}

		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
			System.out.println("DB totalCount :" + totalCount);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 로그 가져오기
	public LogDTO selectLog(String logText, String memberEmail, String start, String end) {
		LogDTO logDTO= new LogDTO();
		List<Object> logList = new ArrayList<Object>();
		
		/*
		String query = "SELECT * FROM log WHERE log_text = (?)";
		if(memberEmail != "") {
			memberEmail = "%"+memberEmail+"%";
			query += " AND m_email LIKE (?)";
		}
		 */
		
		String query = "SELECT * FROM (" + "SELECT log.*, @ROWNUM := @ROWNUM+1 AS rNum FROM (" + "SELECT * FROM log WHERE log_text LIKE '%" + logText + "%'";

		// SELECT * FROM (SELECT board.*, @ROWNUM := @ROWNUM+1 AS rNum FROM (SELECT *
		// FROM board ORDER BY idx desc) board, (SELECT @ROWNUM := 0) tmp) AS boardT
		// WHERE rNum BETWEEN '1' AND '10';

		if (memberEmail != "") {
			memberEmail = "%"+memberEmail+"%";
			query += " AND m_email LIKE '%" + memberEmail + "%' ";
		}
		query += " ORDER BY log_idx DESC " + ") log, (SELECT @ROWNUM := 0) tmp" + ") AS boardT"
				+ " WHERE rNum BETWEEN (?) AND (?) ";
		
		// System.out.println(query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, start);
			psmt.setString(2, end);
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("logIdx", rs.getInt("log_idx"));
				jsonObject.put("logIp", rs.getString("log_ip"));
				jsonObject.put("logLocale", rs.getString("log_locale"));
				jsonObject.put("logText", rs.getString("log_text"));
				jsonObject.put("logDatetime", rs.getDate("log_datetime"));
				jsonObject.put("memberEmail", rs.getString("m_email"));
				
				logList.add(jsonObject);
			}
			
			logDTO.setLogData(logList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return logDTO;
	}
}
