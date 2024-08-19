package member;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {
	private String email;
	private String pass;
	private String name;
	private String nickName;
	private int level;
	private int grade;
	private String zipcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String refAddress;
	private Date regdate;
	private Date update;
	private String phoneNum;
	private String mUUID;
	//
}
