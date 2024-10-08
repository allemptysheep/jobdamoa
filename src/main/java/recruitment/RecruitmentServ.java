package recruitment;

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
@WebServlet("/RecruitmentServ")
public class RecruitmentServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitmentServ() {
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

		RecruitmentDAO dao = new RecruitmentDAO(application);
		int idx = Integer.parseInt(session.getAttribute("selected_rec_idx").toString());
		String title = request.getParameter("recruitment_title");
		String contents = request.getParameter("recruitment_contents");
		String company_name = request.getParameter("recruitment_c_name");
		
		String hire_type = request.getParameter("recruitment_hire_type");
		String work_history = request.getParameter("recruitment_work_history");
		
		String region_name = request.getParameter("recruitment_region_name");
		String region_code = request.getParameter("recruitment_region_code");
		
		String gu_name = request.getParameter("recruitment_gu_name");
		String gu_code = request.getParameter("recruitment_gu_code");
		
		String apply_startdate = request.getParameter("recruitment_apply_startdate");
		String apply_enddate = request.getParameter("recruitment_apply_enddate");
		
		String apply_method = request.getParameter("recruitment_apply_method");
		String m_email = (String)session.getAttribute("mEmail");
		
		try {
			String viewPage = "/";
			 if (op.equals("submit")) {
				 
				
	
				int uirs = dao.registerRecruitment(title, contents, company_name, hire_type, work_history, region_name, region_code, gu_name, gu_code, apply_startdate, apply_enddate, apply_method, m_email);
	
				System.out.println("uirs : "+uirs);
				
				if(uirs == 1){
					// 등록 성공
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment Register success:" + m_email);
				} else {
					// 등록 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment Register:" + m_email);
				}
				
				viewPage = "/view/recruitment/registerProcess.jsp";
				
			} else if (op.equals("edit")) {
				int rs = dao.editRecruitment(title, contents, company_name, hire_type, work_history, region_name, region_code, gu_name, gu_code, apply_startdate, apply_enddate, apply_method, idx);
				if (rs == 1) {
					// 채용공고 수정 완료.
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment edit success");
				} else {
					// 채용공고 수정 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment edit fail");
				}
				
				viewPage = "./view/recruitment/recruitmentEditProcess.jsp";
			       
			} else if (op.equals("delete")) {
				int rs = dao.deleteRecruitment(idx, m_email);
				if (rs == 1) {
					// 채용공고 수정 완료.
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment delete success");
				} else {
					// 채용공고 수정 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Recruitment delte fail");
				}
				viewPage = "./view/recruitment/recruitmentDeleteProcess.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} catch (ServletException e) {
		       System.out.println("keyword serv error : " + e);
		}
	}

}
