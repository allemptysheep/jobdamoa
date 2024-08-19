package log;

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
 * Servlet implementation class LogServ
 */
@WebServlet("/LogServ")
public class LogServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServ() {
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
		// encode
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();

		try {

			String viewPage = "/admin/log.jsp";

			String op = (String) request.getParameter("operator");
			System.out.println("operation : " + op);

			if (op.equals("getLog")) {
				viewPage = "/admin/logResult.jsp";

				String searchWord = (String) request.getParameter("searchWord");
				
				String memberEmail = "";
				if((String)request.getParameter("memberEmail") != "") {
					memberEmail = (String) request.getParameter("memberEmail");
				}

				System.out.println("searchWord : " + searchWord);
				System.out.println("memberEmail : " + memberEmail);


				System.out.println("page_now 1 : " + request.getParameter("page_now"));
				// paging
				int page_now = 1;
				if (request.getParameter("page_now") != null) {
					page_now = Integer.parseInt(request.getParameter("page_now"));
				}

				System.out.println("page_now : " + page_now);

				int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
				int blockSize = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

				System.out.println("pageSize : " + pageSize);
				System.out.println("blockSize : " + blockSize);

				int first = 0;
				int last = 0;
				try {
					if (request.getParameter("first").equals("0")) {
						first = 0;
					} else {
						first = pageSize * (page_now - 1);
					}
					if (request.getParameter("last").equals("10")) {
						last = 10;
					} else {
						last = first + pageSize;
					}
				} catch (Exception e) {
					// TODO: handle exception
					first = pageSize * (page_now - 1);
					last = first + pageSize;
				}


				LogDAO logDAO = new LogDAO(application);
				LogDTO logDTO = logDAO.selectLog(searchWord, memberEmail, Integer.toString(first), Integer.toString(last));
				
				int totalCount = logDAO.selectLogCount(searchWord, memberEmail);
				
				System.out.println("totalCount : " + totalCount);

				int totalPage = (int) Math.ceil((double) totalCount / (double) pageSize);
				System.out.println("totalPage : " + totalPage);
				System.out.println("first : " + first);
				System.out.println("last : " + last);

				request.setAttribute("page_now", Integer.toString(page_now));
				request.setAttribute("pageSize", Integer.toString(pageSize));
				request.setAttribute("blockSize", Integer.toString(blockSize));
				request.setAttribute("totalCount", Integer.toString(totalCount));
				request.setAttribute("totalPage", Integer.toString(totalPage));
				request.setAttribute("first", Integer.toString(first));
				request.setAttribute("last", Integer.toString(last));



				request.setAttribute("searchWord", searchWord);
				if (memberEmail.equals("")) {
					request.setAttribute("memberEmail", "");
				} else {
					request.setAttribute("memberEmail", memberEmail);
				}
				request.setAttribute("memberEmail", memberEmail);
				request.setAttribute("logDTO", logDTO.getLogData());
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

}
