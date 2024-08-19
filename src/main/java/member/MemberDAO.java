package member;


import java.sql.ResultSet;

import javax.servlet.ServletContext;

import common.DBConnect;

public class MemberDAO extends DBConnect {

	public MemberDAO (ServletContext application) {
		super(application);
	}
	
	// login
	public MemberDTO getMemberDTO(String mid, String mpwd) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE m_email =? AND m_pwd=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, mid);
			psmt.setString(2, mpwd);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				System.out.println(rs.getString("m_email"));
				dto.setEmail(rs.getString("m_email"));
				dto.setPass(rs.getString("m_pwd"));
				dto.setName(rs.getString("m_name"));
				dto.setNickName(rs.getString("m_nickname"));
				dto.setLevel(rs.getInt("m_sec_lev"));
				dto.setGrade(rs.getInt("m_grade"));
				dto.setZipcode(rs.getString("m_zipcode"));
				dto.setRoadAddress(rs.getString("m_road_address"));
				dto.setJibunAddress(rs.getString("m_jibun_address"));
				dto.setDetailAddress(rs.getString("m_detail_address"));
				dto.setRefAddress(rs.getString("m_ref_address"));
				dto.setPhoneNum(rs.getString("m_phone_num"));
				dto.setMUUID(rs.getString("m_uuid"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dto;
	}
	
	// login 정보로 자기 정보 가져오기
	public MemberDTO getMemberDTO(String mid) {
		MemberDTO memberDTO = new MemberDTO();
		String query = "SELECT * FROM member WHERE m_email =?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, mid);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				memberDTO.setEmail(rs.getString("m_email"));
				memberDTO.setName(rs.getString("m_name"));
				memberDTO.setNickName(rs.getString("m_nickname"));
				memberDTO.setLevel(rs.getInt("m_sec_lev"));
				memberDTO.setGrade(rs.getInt("m_grade"));
				memberDTO.setZipcode(rs.getString("m_zipcode"));
				memberDTO.setRoadAddress(rs.getString("m_road_address"));
				memberDTO.setJibunAddress(rs.getString("m_jibun_address"));
				memberDTO.setDetailAddress(rs.getString("m_detail_address"));
				memberDTO.setRefAddress(rs.getString("m_ref_address"));
				memberDTO.setPhoneNum(rs.getString("m_phone_num"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return memberDTO;
	}
	
	// 중복 확인
	public int checkDup(String email) {
		int dup = 0;
		String sql = "SELECT EXISTS(SELECT m_email FROM member WHERE m_email = ?)as result;";
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, email);
					
				rs = psmt.executeQuery();
				if (rs.next()) {
					dup = rs.getInt("result");
				}
				// System.out.println("result select : "+ rs.getInt("result"));
				// System.out.println("dupe check com");
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return dup;
	}
	
	// 회원가입
	public int insertMemberInfo(String email, String pwd, String lName, String fName, String nickname, String zipcode, String roadAdd, String jibunAdd, String detailAdd, String refAdd, String phoneNum) {
		int rs = 0;
		
		String name = lName+" "+fName;
		String sql = "INSERT INTO member(M_EMAIL, M_PWD, M_NAME, M_NICKNAME, M_ZIPCODE, M_ROAD_ADDRESS, M_JIBUN_ADDRESS, M_DETAIL_ADDRESS, M_REF_ADDRESS, M_PHONE_NUM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, nickname);
			psmt.setString(5, zipcode);
			psmt.setString(6, roadAdd);
			psmt.setString(7, jibunAdd);
			psmt.setString(8, detailAdd);
			psmt.setString(9, refAdd);
			psmt.setString(10, phoneNum);
			
			rs = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	// 회원정보 수정
	public int editMemberInfo(String email, String pwd, String lName, String fName, String nickname, String zipcode, String roadAdd, String jibunAdd, String detailAdd, String refAdd, String phoneNum) {
		int rs = 0;
		String name = lName+" "+fName;
		String query = "UPDATE member SET M_NAME = ?, M_NICKNAME = ?, M_ZIPCODE = ?, M_ROAD_ADDRESS = ?, M_JIBUN_ADDRESS = ?, M_DETAIL_ADDRESS = ?, M_REF_ADDRESS = ?, M_PHONE_NUM = ? WHERE M_EMAIL = ? AND M_PWD = ?"; 
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, nickname);
			psmt.setString(3, zipcode);
			psmt.setString(4, roadAdd);
			psmt.setString(5, jibunAdd);
			psmt.setString(6, detailAdd);
			psmt.setString(7, refAdd);
			psmt.setString(8, phoneNum);
			psmt.setString(9, email);
			psmt.setString(10, pwd);
			
			rs = psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	// 회원 삭제
	
	// 이메일 찾기
	public String findEmail(String phoneNum) {
		String mEmail = "";
		String query = "SELECT m_email FROM member WHERE m_phone_num =?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, phoneNum);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				mEmail = rs.getString("m_email");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mEmail;
	}
	
	// 비밀번호 찾기
	public String findPassword(String phoneNum) {
		String mEmail = "";
		String query = "SELECT m_email FROM member WHERE m_phone_num =?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, phoneNum);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				mEmail = rs.getString("m_email");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mEmail;
	}
	
	
}
