package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.LogDAO;

import java.sql.*;
/**
 * Servlet implementation class SignupServ
 */
@WebServlet("/MemberSignupServ")
public class MemberSignupServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSignupServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(request.getContextPath());
		// .append("Served at: ")
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// encode
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		
	 	PrintWriter out = response.getWriter();
		
		String op = request.getParameter("operator");

		MemberDAO dao = new MemberDAO(application);
		
		try {
			String viewPage = "/";
			if(op.equals("iddupchk")) {
	
				String mEmail = request.getParameter("id");
	
				int isDup = dao.checkDup(mEmail);
						
				System.out.println("isDup : "+isDup);
				out.print(isDup);
				
			} else if (op.equals("submit")) {
				
				String email = request.getParameter("member_email");
				String passwd = request.getParameter("member_password");
				String lName = request.getParameter("member_last_name");
				String fName = request.getParameter("member_first_name");
				String nickname = request.getParameter("member_Nickname");
				
				String zipcode = request.getParameter("zipCode");
				String roadAdd = request.getParameter("roadAddredd");
				String jibunAdd = request.getParameter("jibunAddress");
				String detailAdd = request.getParameter("detailAddress");
				String refAdd = request.getParameter("refAddress");
	
				String phoneNum = request.getParameter("member_Phonenum");
	
				// 비밀번호 암호화
				Encrypt en = new Encrypt(application);
				// salt 생성
				String salt = en.getSalt();
				// 암호, salt 를 이용하여 암호화
				String enPwd = en.getEncrypt(passwd, salt);
				// 데이터 베이스 저장
				int enrs = en.insertSalt(email, salt);
				int uirs = dao.insertMemberInfo(email, enPwd, lName, fName, nickname, zipcode, roadAdd, jibunAdd, detailAdd, refAdd, phoneNum);
	
				System.out.println("enrs : "+enrs);
				System.out.println("uirs : "+uirs);
				
				if(enrs == 1 || uirs == 1){
					// 회원가입 성공
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "signUp success:" + email);
				} else {
					// 회원가입 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "signUp fail:" + email);
				}
				
				viewPage = "/view/member/signUp_process.jsp";
				
			} else if (op.equals("findEmail")) {

				String phoneNum = request.getParameter("member_Phonenum");
				String memberEmail = dao.findEmail(phoneNum);

				System.out.println(memberEmail);
				request.setAttribute("memberEmail", memberEmail);
				
				LogDAO insertLog = new LogDAO(application);
				insertLog.insertLog(request, session, "email search:" + memberEmail);
				
				
				viewPage = "/view/member/findEmailComplete.jsp";
				
			} else if (op.equals("findPassword")) {

				String memberEmail = request.getParameter("member_email");
				String phoneNum = request.getParameter("member_Phonenum");
				String result = dao.findPassword(phoneNum);
				
				LogDAO insertLog = new LogDAO(application);
				insertLog.insertLog(request, session, "password search:" + memberEmail);
				
				viewPage = "/view/member/findPasswordComplete.jsp";
			} else if (op.equals("PhoneNumAuth")) {
				// 번호 보내고 데이터 베이스에 저장.
				
				out.print("");
			} else if (op.equals("changePassword")) {
				
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} catch (ServletException e) {
		       System.out.println("keyword serv error : " + e);
		}
	}

}
