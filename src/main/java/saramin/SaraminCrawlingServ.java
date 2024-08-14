package saramin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaraminCrawling
 */
@WebServlet("/SaraminCrawlingServ")
public class SaraminCrawlingServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaraminCrawlingServ() {
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
		// HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		// 분기
		String op = request.getParameter("operator");
		String viewPage = "view/test/saramin.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);

		try {
			if(op.equals("getRegion")) {
				System.out.println("getRegion");
				SaraminRegionDAO saraminRegionDAO = new SaraminRegionDAO(application);
				saraminRegionDAO.crawlingRegion();
			}
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println("keyword serv error : " + e);
		}
	}

}
