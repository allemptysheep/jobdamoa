package company;

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

import java.sql.*;
/**
 * Servlet implementation class SignupServ
 */
@WebServlet("/CompanyInfoServ")
public class CompanyInfoServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyInfoServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(request.getContextPath());
		// .append("Served at: ")
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// encode
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		
	 	PrintWriter out = response.getWriter();
		
		String op = request.getParameter("operator");
		
		CompanyDAO dao = new CompanyDAO(application);
		
		
		String c_name = request.getParameter("companyName");
		String c_ceo_name = request.getParameter("companyCeoName");
		String c_establishment_date =  request.getParameter("establishmentDate");
		int c_income = Integer.parseInt( request.getParameter("companyIncome"));
		int employee_number = Integer.parseInt( request.getParameter("employeeNumber"));
		int average_salary = Integer.parseInt( request.getParameter("averageSalary"));
		String capital_stock = request.getParameter("capitalStock");
		String occupation = request.getParameter("opccupation");
		String c_zipcode = request.getParameter("zipCode");
		String c_road_address = request.getParameter("roadAddredd");
		String c_jibnun_address = request.getParameter("jibunAddress");
		String c_detail_address = request.getParameter("detailAddress");
		String ref_address = request.getParameter("refAddress");
		String m_email = session.getAttribute("mEmail").toString();
		
		
		try {
			String viewPage = "/";
			 if (op.equals("edit")) {
				 
				int uirs = dao.insertOrUpdateCompanyInfo(c_name, c_ceo_name, c_establishment_date, c_income, employee_number, average_salary, capital_stock, occupation, c_zipcode, c_road_address, c_jibnun_address, c_detail_address, ref_address, m_email);
	
				System.out.println("uirs : "+uirs);
				
				if(uirs == 1){
					// 등록 성공
					request.setAttribute("rs", "1");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Company Info success:" + m_email);
				} else {
					// 등록 실패
					request.setAttribute("rs", "0");
					LogDAO insertLog = new LogDAO(application);
					insertLog.insertLog(request, session, "Company Info :" + m_email);
				}
				
				viewPage = "/view/member/myCompanyEditProcess.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} catch (ServletException e) {
		       System.out.println("keyword serv error : " + e);
		}
	}

}
