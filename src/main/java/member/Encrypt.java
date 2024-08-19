package member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.ServletContext;

import common.DBConnect;

public class Encrypt extends DBConnect {
	public Encrypt(ServletContext application) {
		super(application);
	}

	// salt 생성
	public String getSalt() {

		// 1. Random, byte 객체 생성
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[20];

		// 2. 난수 생성
		r.nextBytes(salt);

		// 3. byte To String (10진수의 문자열로 변경)
		StringBuffer sb = new StringBuffer();
		for (byte b : salt) {
			sb.append(String.format("%02x", b));
		}
		;

		return sb.toString();
	}

	// salt를 이용한 암호화
	public String getEncrypt(String pwd, String salt) {

		String result = "";
		try {
			// 1. SHA256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// 2. 비밀번호와 salt 합친 문자열에 SHA 256 적용
			System.out.println("비밀번호 + salt 적용 전 : " + pwd + salt);
			md.update((pwd + salt).getBytes());
			byte[] pwdsalt = md.digest();

			// 3. byte To String (10진수의 문자열로 변경)
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}

			result = sb.toString();
			System.out.println("비밀번호 + salt 적용 후 : " + result);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	// salt를 이용한 비밀번호 비교
	public String compPwd(String email, String pwd) {

		String result = "";
		String dbpwd = ""; // 데이터 베이스 패스워드
		String dbSalt = ""; // 데이터 베이스 salt

		String enPwd = ""; // 들어온 비밀번호 암호화

		String sql = "SELECT a.m_pwd, b.salt FROM member a JOIN salt b ON a.m_email = b.m_email WHERE a.m_email = ?";
		try {
			// 데이터 베이스의 비밀번호, salt 값 가져오기
			psmt = con.prepareStatement(sql);
			psmt.setString(1, email);

			rs = psmt.executeQuery();
			while (rs.next()) {
				dbpwd = rs.getString("m_pwd");
				dbSalt = rs.getString("salt");
			}
			//

			// 입력받은 비밀번호, 데이터 베이스 salt 값으로 암호화 비밀번호 만들기
			// 1. SHA256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// 2. 비밀번호와 salt 합친 문자열에 SHA 256 적용
			System.out.println("login: 비밀번호 + salt 적용 전 : " + pwd + dbSalt);
			md.update((pwd + dbSalt).getBytes());
			byte[] pwdsalt = md.digest();

			// 3. byte To String (10진수의 문자열로 변경)
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}

			enPwd = sb.toString();
			System.out.println("입력 password : " + pwd);
			System.out.println("DB password : " + dbpwd);
			System.out.println("입력 비밀번호 salt 적용 후 : " + enPwd);

			// 데이터 베이스 값과 암호화 비밀번호 비교
			if (dbpwd.equals(enPwd)) {
				// 비밀번호 같음
				System.out.println("비밀번호 같음.");
				result = enPwd;
			} else {
				// 비밀번호 다름
				System.out.println("비밀번호 다름.");
				result = "";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// salt db 저장
	public int insertSalt(String email, String salt) {
		int rs = 0;
		/* oracle
		String sql = "MERGE INTO salt USING dual ON (m_email = ?) " +
					 "WHEN MATCHED THEN UPDATE SET salt = (?) " +
					 "WHEN NOT MATCHED THEN INSERT (m_email, salt) VALUES (?, ?)";
		*/
		String sql = "INSERT INTO salt " + 
				 "SET m_email = (?), salt = (?) " + 
				 "ON DUPLICATE KEY UPDATE salt = (?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setString(2, salt);
			psmt.setString(3, salt);

			rs = psmt.executeUpdate();
			System.out.println("salt insert com");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
}
