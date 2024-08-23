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

/**
 * Servlet implementation class SubmittedResumeServ
 */
@WebServlet("/SubmittedResumeServ")
public class SubmittedResumeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmittedResumeServ() {
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
		
	 	PrintWriter out = response.getWriter();
	 	
		String op = request.getParameter("operator");
		
		String viewPage = "/view/recruitment/recruitmentViewProcess.jsp";

		LogDAO insertLog = new LogDAO(application);
		
		try {
			if (op.equals("submit")) {
				String recIdx = request.getParameter("recIdx");
				String resumeInfoIdx = request.getParameter("resumeInfoIdx");
				String mEmail = request.getParameter("mEmail");
				
				request.setAttribute("recIdx", recIdx);
				SubmittedResumeDAO submittedResumeDAO = new SubmittedResumeDAO(application);
				int rs = submittedResumeDAO.insertSubmittedResume(recIdx, resumeInfoIdx, mEmail);
				// System.out.println("rs : " + rs);
				if(rs == 1){
					// 등록 성공
					request.setAttribute("rs", "1");
					insertLog.insertLog(request, session, "submitted resume success:" + recIdx + ":" + resumeInfoIdx + ":" + mEmail);
				} else {
					// 등록 실패
					request.setAttribute("rs", "0");
					insertLog.insertLog(request, session, "submitted resume fail:" + recIdx + ":" + resumeInfoIdx + ":" + mEmail);
				}
				
			} else {
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
