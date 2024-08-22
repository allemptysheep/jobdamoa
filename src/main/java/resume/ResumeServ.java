package resume;

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
 * Servlet implementation class ResumeServ
 */
@WebServlet("/ResumeServ")
public class ResumeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeServ() {
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
	    String viewPage = "/view/resume/resumeList.jsp";
	    String op = (String)request.getParameter("operator");
	    
	    if(op.equals("submit")) {
	    	System.out.println("op : " + op);
		   
	    	ResumeDAO resumeDAO = new ResumeDAO(application);
	    	
		    String mEmail = (String)session.getAttribute("mEmail");
		    
		    String name = request.getParameter("name");
		    String mname = request.getParameter("mname");
		    String ename = request.getParameter("ename");
		    String birth = request.getParameter("birth");
		    String phone = request.getParameter("phone");
		    String email = request.getParameter("email");
		    String z1 = request.getParameter("z1");
		    String z2 = request.getParameter("z2");
		    String z3 = request.getParameter("z3");
		    String z4 = request.getParameter("z4");
		    String z5 = request.getParameter("z5");
		    
		    String h1 = request.getParameter("h1");
		    String h2 = request.getParameter("h2");
		    String h3 = request.getParameter("h3");
		    String h4 = request.getParameter("h4");
		    String h5 = request.getParameter("h5");
		    
		    String wloc = request.getParameter("wloc");
		    String wrank = request.getParameter("wrank");
		    String mwork = request.getParameter("mwork");
		    String swork = request.getParameter("swork");
		    String ework = request.getParameter("ework");
		    
		    String dqua = request.getParameter("dqua");
		    String nqua = request.getParameter("nqua");
		    String rqua = request.getParameter("rqua");
		    String pqua = request.getParameter("pqua");
		    
		    String jhope = request.getParameter("jhope");
		    String phope = request.getParameter("phope");
		    String lhope = request.getParameter("lhope");
		    String presv = request.getParameter("presv");
		    
		    String dact = request.getParameter("dact");
		    String cact = request.getParameter("cact");
		    String nact = request.getParameter("nact");
		    String hact = request.getParameter("hact");
		    // 리스트 이름 저장, 키를 리턴함.
		    int key = resumeDAO.insertResumeInfo(mEmail, name);
		    
		    int basic = resumeDAO.insertResumeBasic(key,mname, ename, birth, phone, email, z1, z2, z3, z4, z5);
		    
		    int edu = resumeDAO.insertResumeEdu(key, h1, h2, h3, h4, h5);
		    
		    int exp = resumeDAO.insertResumeExp(key, wloc, wrank, mwork, swork, ework);
		    
		    int qua = resumeDAO.insertResumeQua(key, dqua, nqua, rqua, pqua);
		    
		    int hope = resumeDAO.insertResumeHope(key, jhope, phope, lhope, presv);
		    
		    int ext = resumeDAO.insertResumeExt(key, dact, cact, nact, hact);
		    
		    System.out.println("key :"+key);
		    System.out.println("basic : "+basic);
		    System.out.println("edu : "+edu);
		    System.out.println("exp : "+exp);
		    System.out.println("qua : "+qua);
		    System.out.println("hope : "+hope);
		    System.out.println("ext : "+ext);

		    
		    
	    } else if (op.equals("edit")) {
	    	System.out.println("op : " + op);
	    	String name = request.getParameter("name");
	    	
		    String mname = request.getParameter("mname");
		    String ename = request.getParameter("ename");
		    String birth = request.getParameter("birth");
		    String phone = request.getParameter("phone");
		    String email = request.getParameter("email");
		    String z1 = request.getParameter("z1");
		    String z2 = request.getParameter("z2");
		    String z3 = request.getParameter("z3");
		    String z4 = request.getParameter("z4");
		    String z5 = request.getParameter("z5");
		    
		    String h1 = request.getParameter("h1");
		    String h2 = request.getParameter("h2");
		    String h3 = request.getParameter("h3");
		    String h4 = request.getParameter("h4");
		    String h5 = request.getParameter("h5");
		    
		    String wloc = request.getParameter("wloc");
		    String wrank = request.getParameter("wrank");
		    String mwork = request.getParameter("mwork");
		    String swork = request.getParameter("swork");
		    String ework = request.getParameter("ework");
		    
		    String dqua = request.getParameter("dqua");
		    String nqua = request.getParameter("nqua");
		    String rqua = request.getParameter("rqua");
		    String pqua = request.getParameter("pqua");
		    
		    String jhope = request.getParameter("jhope");
		    String phope = request.getParameter("phope");
		    String lhope = request.getParameter("lhope");
		    String presv = request.getParameter("presv");
		    
		    String dact = request.getParameter("dact");
		    String cact = request.getParameter("cact");
		    String nact = request.getParameter("nact");
		    String hact = request.getParameter("hact");
		    
	    	ResumeDTO resumeDTO = new ResumeDTO();
	    	ResumeDAO resumeDAO = new ResumeDAO(application);
	    	String resumeInfoIdx = request.getParameter("resumeInfoIdx");
		    String mEmail = (String)session.getAttribute("mEmail");
	    	
	    	int key = Integer.parseInt(resumeInfoIdx);
	    		    	
	    	
		    int info = resumeDAO.updateResumeInfo(name,key,mEmail);
		    
		    int basic = resumeDAO.updateResumeBasic(mname, ename, birth, phone, mEmail, z1, z2, z3, z4, z5, key);
		    
		    int exp = resumeDAO.updateResumeExp(wloc, wrank, mwork, swork, ework, key);
		    
		    int qua = resumeDAO.updateResumeQua(dqua, nqua, rqua, pqua, key);
		    
		    int hope = resumeDAO.updateResumeHope(jhope, phope, lhope, presv, key);
		    
		    int ext = resumeDAO.updateResumeExt(dact, cact, nact, hact, key);
		    
		    System.out.println("key : "+key);
		    System.out.println(h1+" "+h2+" "+h3+" "+h4+" "+h5);

		    System.out.println("성공 : 1 실패 : 0");
		    System.out.println("info : "+info);
		    System.out.println("basic : "+basic);
		    System.out.println("exp : "+exp);
		    System.out.println("qua : "+qua);
		    System.out.println("hope : "+hope);
		    System.out.println("ext : "+ext);
		    

		    
	    } else if(op.equals("delete")) {
	    	viewPage = "/view/resume/resumeDel.jsp";
	    	ResumeDAO resumeDAO = new ResumeDAO(application);
	    	String resumeInfoIdx = request.getParameter("resumeInfoIdx");
	    	int key = Integer.parseInt(resumeInfoIdx);

		    System.out.println("key : "+key);

	    	 int info = resumeDAO.deleteResumeInfo(key);
			    
			    int basic = resumeDAO.deleteResumeBasic(key);
			    
			    int exp = resumeDAO.deleteResumeExp(key);
			    
			    int qua = resumeDAO.deleteResumeQua(key);
			    
			    int hope = resumeDAO.deleteResumeHope(key);
			    
			    int ext = resumeDAO.deleteResumeExt(key);
	    
	    }
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	    dispatcher.forward(request, response);
	}

}
