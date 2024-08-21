package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.LogDAO;

/**
 * Servlet implementation class RegionServ
 */
@WebServlet("/AdminRegionServ")
public class AdminRegionServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegionServ() {
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
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		// 분기
		String op = request.getParameter("operator");
		String viewPage = "/view/admin/regionProcess.jsp";

		AdminRegionDAO adminRegionDAO = new AdminRegionDAO(application);
		LogDAO insertLog = new LogDAO(application);
		
		try {
			if(op.equals("regionAdd")) {
				/**
				 * region add
				 */
				System.out.println("regionAdd");
				String regionName = request.getParameter("regionName");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");
				
				System.out.println("regionName : " + regionName);
				System.out.println("regionCode : " + regionCode);
				System.out.println("country : " + country);
				
				int rs = adminRegionDAO.insertRegion(regionName, regionCode, country);

				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "addS");
					insertLog.insertLog(request, session, "region insert success:" + regionName + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "addF");
					insertLog.insertLog(request, session, "region insert fail:" + regionName + ":" + regionCode + ":" + country);
				}
				
			} else if (op.equals("regionEdit")) {
				/**
				 * region edit
				 */
				System.out.println("regionEdit");
				int idx = Integer.parseInt(request.getParameter("idx"));
				String regionName = request.getParameter("regionName");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");
				
				int rs = adminRegionDAO.regionEdit(idx, regionName, regionCode, country);
				
				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "editS");
					insertLog.insertLog(request, session, "region Edit success:" + idx + regionName + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "editF");
					insertLog.insertLog(request, session, "region Edit fail:" + idx + regionName + ":" + regionCode + ":" + country);
				}
				
			} else if (op.equals("regionDelete")) {
				/**
				 * region delete
				 */
				System.out.println("retionDelete");
				int idx = Integer.parseInt(request.getParameter("idx"));
				String regionName = request.getParameter("regionName");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");
				
				int rs = adminRegionDAO.regionDelete(idx);
				
				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "delS");
					insertLog.insertLog(request, session, "region Delete success:" + idx + regionName + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "delF");
					insertLog.insertLog(request, session, "region Delete fail:" + idx + regionName + ":" + regionCode + ":" + country);
				}
				
			} else if (op.equals("guAdd")) {
				/**
				 * gu add
				 */
				System.out.println("guAdd");
				String guName = request.getParameter("guName");
				String guCode = request.getParameter("guCode");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");

				int rs = adminRegionDAO.insertGu(guName, guCode, regionCode, country);
				
				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "addS");
					insertLog.insertLog(request, session, "gu insert success:" + guName + ":" + guCode + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "addF");
					insertLog.insertLog(request, session, "gu insert fail:" + guName + ":" + guCode + ":" + regionCode + ":" + country);
				}
				
			} else if (op.equals("guEdit")) {
				/**
				 * gu edit
				 */
				System.out.println("guEdit");
				String guName = request.getParameter("guName");
				String guCode = request.getParameter("guCode");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");
				
				int rs = adminRegionDAO.guEdit(guName, guCode, regionCode, country);
				
				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "editS");
					insertLog.insertLog(request, session, "gu Edit success:" + guName + guCode + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "editF");
					insertLog.insertLog(request, session, "gu Edit fail:" + guName + guCode + ":" + regionCode + ":" + country);
				}
				
				
			}else if (op.equals("guDelete")) {
				/**
				 * gu delete
				 */
				System.out.println("guDelete");
				String guName = request.getParameter("guName");
				String guCode = request.getParameter("guCode");
				String regionCode = request.getParameter("regionCode");
				String country = request.getParameter("country");
				
				int rs = adminRegionDAO.guDelete(guCode);
				
				if (rs == 1) {
					// region 추가 완료.
					request.setAttribute("rs", "delS");
					insertLog.insertLog(request, session, "gu Delete success:" + guName + guCode + ":" + regionCode + ":" + country);
				} else {
					// region 추가 실패
					request.setAttribute("rs", "delF");
					insertLog.insertLog(request, session, "gu Delete fail:" + guName + guCode + ":" + regionCode + ":" + country);
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println("admin region serv error : " + e);
		}
	
	}

}
