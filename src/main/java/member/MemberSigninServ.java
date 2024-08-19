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

import com.mysql.cj.Session;

// import admin.PlanDAO;
import log.LogDAO;


/**
 * Servlet implementation class MemberSigninServ
 */
@WebServlet("/MemberSigninServ")
public class MemberSigninServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSigninServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ServletContext application = this.getServletContext();
		
		// session
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();

		String mEmail = request.getParameter("member_email");
		String mPwd = request.getParameter("member_password");
		
		Encrypt en = new Encrypt(application);
		String com = en.compPwd(mEmail, mPwd);
		
		System.out.println("serv email : "+mEmail);
		System.out.println("serv pass : "+ com);
		
		String viewPage = "/";
		
		if (com == "") {
			response.setContentType("text/html; charset=UTF-8");
			response.sendRedirect("/view/member/signIn_error.jsp"); 
		} else {
			// 비밀번호가 있음.
			MemberDAO memberDAO = new MemberDAO(application);
			MemberDTO memberDTO = memberDAO.getMemberDTO(mEmail, com);
			
			// PlanDAO planDAO = new PlanDAO(application);
			// int pLevel = planDAO.selectMemberPlan(mEmail);
			
			System.out.println("getEmail : "+memberDTO.getEmail());
			
			//전화번호 형식 변경
			//String phoneNumRegEx = "(\\d{3})(\\d{3,4})(\\d{4})";
			//memberDTO.getPhoneNum().replaceAll(phoneNumRegEx, "$1-$2-$3")
			if (memberDTO.getEmail() != null) {
				session.setAttribute("mEmail", memberDTO.getEmail());
				session.setAttribute("mName", memberDTO.getName());
				session.setAttribute("mPhoneNum", memberDTO.getPhoneNum());
				session.setAttribute("mNickName", memberDTO.getNickName());
				session.setAttribute("mLevel", memberDTO.getLevel());
				session.setAttribute("mUUID", memberDTO.getMUUID());
				// session.setAttribute("pLevel", pLevel);
				LogDAO insertLog = new LogDAO(application);
				insertLog.insertLog(request, session, "signIn success");
			} else {
				session.setAttribute("mEmail", null);
				session.setAttribute("mName", null);
				session.setAttribute("mPhoneNum", null);
				session.setAttribute("mNickName", null);
				session.setAttribute("mLevel", null);
				session.setAttribute("mUUID", null);
				// session.setAttribute("pLevel", null);
				LogDAO insertLog = new LogDAO(application);
				insertLog.insertLog(request, session, "signIn fail:" + mEmail);
			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
