package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.LogDAO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// encode
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		
		String op = request.getParameter("operator");
		
		String mEmail = request.getParameter("member_email");
		String mPwd = request.getParameter("member_password");
		String lName = request.getParameter("member_last_name");
		String fName = request.getParameter("member_first_name");
		String nickname = request.getParameter("member_Nickname");
		
		String zipcode = request.getParameter("zipCode");
		String roadAdd = request.getParameter("roadAddredd");
		String jibunAdd = request.getParameter("jibunAddress");
		String detailAdd = request.getParameter("detailAddress");
		String refAdd = request.getParameter("refAddress");

		String phoneNum = request.getParameter("member_Phonenum");
		String viewPage = "/";
		if(op.equals("edit")) {
			// 회원 정보 수정
			
			// 비밀번호 확인
			Encrypt en = new Encrypt(application);
			String com = en.compPwd(mEmail, mPwd);
			if (com == "") {
				response.setContentType("text/html; charset=UTF-8");
				response.sendRedirect("/view/member/edit_error.jsp"); 
			} else {
				// 데이터 베이스 저장
				MemberDAO memberDAO = new MemberDAO(application);
				int rs = memberDAO.editMemberInfo(mEmail, com, lName, fName, nickname, zipcode, roadAdd, jibunAdd, detailAdd, refAdd, phoneNum);
				if (rs == 1) {
					// 회원 정보 수정 완료.
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "member edit success");
				} else {
					// 회원 정보 수정 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "member edit fail");
				}
				
				viewPage = "./view/member/memberEdit_process.jsp";
			       
			}
			

		} else if (op.equals("delete")) {
			// 회원 탈퇴
			viewPage = "./view/member/memberDelete_process.jsp";
		       
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
