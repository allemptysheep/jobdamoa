package jobkorea;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher;


@WebServlet("/JobKoreaControllerServ")
public class JobKoreaControllerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;



	public JobKoreaControllerServ() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// session
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();
		
		///////////////////////////////////////////////////////

		String viewPage = "./blog/blogResult.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		
		///////////////////////////////////////////////////////
		
		JobKoreaDAO jobKoreaDAO = new JobKoreaDAO(application);
		JobKoreaDTO test = jobKoreaDAO.test();
		
		///////////////////////////////////////////////////////
		
		String keyword = (String)request.getParameter("keyword");
		
		///////////////////////////////////////////////////////
		
		try {
			
//			if() {
//				viewPage = "";
//				request.setAttribute("keyword", keyword);
//			} else if() {
//				viewPage = "";
//				request.setAttribute("keyword", keyword);
//			}
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
		}
	}

}
