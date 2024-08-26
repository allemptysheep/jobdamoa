package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.FileUtil;

/**
 * Servlet implementation class SliderServ
 */
@WebServlet("/SliderServ")
public class SliderServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SliderServ() {
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
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//
		ServletContext application = this.getServletContext();
		// session
		HttpSession session = request.getSession();

		String viewPage = "/view/admin/adminSlider.jsp";
		String uri = request.getRequestURI();
		// 업로드 경로
		String uploadPath = getServletContext().getRealPath("/resources/slider-img");
		System.out.println("test slider serv");

		String op = request.getParameter("operator");
		SliderDAO sliderDAO = new SliderDAO(application);
		sliderDAO.deleteMainSlider(op);
		String inputName = "sliderFile-" + op;
		
		System.out.println("op : " + op);
		System.out.println("inputName : " + inputName);
		
		try {
			ArrayList<String> listFileName = FileUtil.multipleFile(request, uploadPath, inputName);
			System.out.println(listFileName);
			 for(String originalFileName : listFileName) {
				 System.out.println(originalFileName);
				 String savedFileName = FileUtil.renameFile(uploadPath, originalFileName);
				 System.out.println(savedFileName);
				 sliderDAO.insertMainSlider(op, originalFileName, savedFileName);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 지시자
		// String op = multi.getParameter("operator");

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
