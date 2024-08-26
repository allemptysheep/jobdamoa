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

/**
 * Servlet implementation class SliderServ
 */
@WebServlet("/SliderServ")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 1,
		maxRequestSize = 1024 * 1024 * 10
		)
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
		MultipartRequest multi = new MultipartRequest(request, uploadPath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		System.out.println("test slider serv");

		String op = multi.getParameter("operator");
		SliderDAO sliderDAO = new SliderDAO(application);
		sliderDAO.deleteMainSlider(op);
		String inputName = "sliderFile-" + op;
		
		System.out.println("op : " + op);
		System.out.println("inputName : " + inputName);
		
		try {
			// 파일 이름 지정
			Enumeration files = multi.getFileNames();
			System.out.println(files);
			while(files.hasMoreElements()){
				String planFile = (String)files.nextElement();						// 첨부파일 존재하면
				System.out.println(planFile);
				String planFileName = multi.getOriginalFileName(planFile);		// 사용자가 올린 파일명
				String planFileRName = multi.getFilesystemName(planFile);			// 중복 파일 있을때 
				String planFileSName = multi.getFilesystemName(planFile);			// 썸네일 파일

				System.out.println(planFileName);
				System.out.println(planFileName);
				System.out.println(planFileRName);
				sliderDAO.insertMainSlider(op, planFileName, planFileRName);
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
