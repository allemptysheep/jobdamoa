package selfIntro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import resume.ResumeDAO;

/**
 * Servlet implementation class ResumeServ
 */
@WebServlet("/selfIntroServ")
public class SelfIntroServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelfIntroServ() {
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

		// Controller
		
		// 인코딩
	    response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    // 세션
	    // session
	    HttpSession session = request.getSession();
	    ServletContext application = this.getServletContext();
	    
	    //
	    String viewPage = "/view/selfIntro/selfIntroList.jsp";
	    String op = (String)request.getParameter("operator");
	    
	    if(op.equals("write")) {
	    	System.out.println("op : " + op);
		   
	    	SelfIntroDAO selfIntroDAO = new SelfIntroDAO(application);
	    	
		    String mEmail = (String)session.getAttribute("mEmail");
		    
		    String sname = request.getParameter("sname");
		    String squestion1 = request.getParameter("squestion");
		    String squestion2 = request.getParameter("squestion2");
		    String squestion3 = request.getParameter("squestion3");

		
		    // 리스트 이름 저장, 키를 리턴함.
		    int info = selfIntroDAO.insertselfIntroInfo(sname, squestion1, squestion2, squestion3, mEmail);
		    		    		    		    
		    
		    System.out.println("info :"+info);

		    
		    
	    } else if (op.equals("edit")) {
	    	System.out.println("op : " + op);
	    	String mEmail = (String)session.getAttribute("mEmail");
		    
		    String sname = request.getParameter("sname");
		    String squestion1 = request.getParameter("squestion1");
		    String squestion2 = request.getParameter("squestion2");
		    String squestion3 = request.getParameter("squestion3");
		    
	    	SelfIntroDTO selfIntroDTO = new SelfIntroDTO();
	    	SelfIntroDAO selfIntroDAO = new SelfIntroDAO(application);
	    	String selfIntroIdx = request.getParameter("selfIntroIdx");	    	
	    		    	
	    	
		    int info = selfIntroDAO.updateSelfIntroInfo(sname, squestion1, squestion2, squestion3);
		    		
		    
		    System.out.println("성공 : 1 실패 : 0");

		    System.out.println("info : "+info);

		    

		    
	    } else if(op.equals("delete")) {
	    	System.out.println("delete");
	    	SelfIntroDAO selfIntroDAO = new SelfIntroDAO(application);
	    	String selfIntroIdx = request.getParameter("selfIntroIdx");
	    	int idx = Integer.parseInt(selfIntroIdx);
	    	String mEmail = (String)session.getAttribute("mEmail");

		    System.out.println("idx : "+idx);

	    	 int info = selfIntroDAO.deleteSelfIntroInfo(idx, mEmail);
			    
	    }
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	    dispatcher.forward(request, response);
	}

}
