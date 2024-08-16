package incruit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saramin.SaraminRegionDAO;

/**
 * Servlet implementation class IncruitControllerServ
 */
@WebServlet("/IncruitControllerServ")
public class IncruitControllerServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IncruitControllerServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// session
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		//////////////////////////////////////////////////////////////

		String keyword = (String) request.getParameter("keyword");
		String nId = (String) request.getParameter("nId");

		request.setAttribute("keyword", keyword);

		//

		
		
		//

		//
		String op = request.getParameter("operator");
		String viewPage = "view/test/incruittest.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		/*
		 <button type="submit" class="btn btn-light" id="submit_delete" name="operator" value="delete" ><fmt:message key="Member.Delete" /></button>
		 */
		try {
			if(op.equals("getRegion")) {
				System.out.println("getRegion");
				IncruitRegionDAO incruitRegionDAO = new IncruitRegionDAO(application);
				incruitRegionDAO.crawlingIncruitRegion();
			}
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println("keyword serv error : " + e);
		}

	}

}
