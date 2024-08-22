package search;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class mainSearchServ
 */
@WebServlet("/MainSearchServ")
public class MainSearchServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchServ() {
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// session
		// HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		// 분기
		String op = request.getParameter("operator");
		String viewPage = "/view/search/searchList.jsp";
		
		MainSearchDAO mainSearchDAO = new MainSearchDAO(application);

		try {
			if(op.equals("search")) {
				System.out.println("search");
				String keyword = request.getParameter("keyword");
				String regionSList = request.getParameter("regionList");
				String guSList = request.getParameter("guList");

				System.out.println("keyword : " + keyword);
				System.out.println("regionList : " + regionSList.toString());
				System.out.println("guList : " + guSList.toString());
				
				regionSList = regionSList.replace("[", "");
				regionSList = regionSList.replace("]", "");
				String[] region = regionSList.split(", ");
				
				regionSList = guSList.replace("[", "");
				regionSList = guSList.replace("]", "");
				String[] gu = guSList.split(",");

				//System.out.println(region);
				//System.out.println(gu);
				request.setAttribute("keyword", keyword);
				request.setAttribute("regionList", regionSList);
				request.setAttribute("guList", guSList);
				
			 	MainSearchListDTO mainSearchListDTO =  mainSearchDAO.mainSearch(keyword, region, gu);

				request.setAttribute("mainSearchListDTO", mainSearchListDTO);
				
			} else if (op.equals("select")) {
				viewPage = "/view/search/searchList.jsp";

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println("search serv error : " + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
