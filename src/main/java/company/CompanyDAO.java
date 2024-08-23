package company;

import javax.servlet.ServletContext;

import common.DBConnect;


public class CompanyDAO extends DBConnect {
		public CompanyDAO (ServletContext application) {
	      super(application);
	   }
		
	   public int insertOrUpdateCompanyInfo(String c_name, String c_ceo_name, String c_establishment_date, String c_income, String employee_number, String average_salary, String capital_stock, String occupation, String c_zipcode, String c_road_address, String c_jibun_address, String c_detail_address, String c_ref_address, String m_email) {		   
		   int rs = 0;
		   
		   String sql = "INSERT INTO company (c_name, c_ceo_name, c_establishment_date, c_income, employee_number, average_salary, capital_stock, occupation, c_zipcode, c_road_address, c_jibun_address, c_detail_address, c_ref_address, m_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE m_email = (?)";
		   
		   try {
		         psmt = con.prepareStatement(sql);
		         psmt.setString(1, c_name);
		         psmt.setString(2, c_ceo_name);
		         psmt.setString(3, c_establishment_date);
		         psmt.setString(4, c_income);
		         psmt.setString(5, employee_number);
		         psmt.setString(6, average_salary);
		         psmt.setString(7, capital_stock);
		         psmt.setString(8, occupation);
		         psmt.setString(9, c_zipcode);
		         psmt.setString(10, c_road_address);
		         psmt.setString(11, c_jibun_address);
		         psmt.setString(12, c_detail_address);
		         psmt.setString(13, c_ref_address);
		         psmt.setString(14, m_email);
		         psmt.setString(15, m_email);
		         
		         rs = psmt.executeUpdate();
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
		   
		   return rs;
	   }
	   
	   public CompanyDTO getCompanyDTO(String m_email) {
			CompanyDTO companyDTO = new CompanyDTO();
		    String query = "SELECT * FROM company WHERE m_email = (?);";
		    
			try {
		         psmt = con.prepareStatement(query);
		         psmt.setString(1, m_email);
		         rs = psmt.executeQuery();
		         while (rs.next()) {
					  companyDTO.setC_idx(rs.getInt("c_idx"));
					  companyDTO.setC_ceo_name(rs.getString("c_name"));
					  companyDTO.setC_establishment_date("c_establishment_date");
					  companyDTO.setC_income(rs.getInt("c_income"));
					  companyDTO.setEmployee_number(rs.getInt("employee_number"));
					  companyDTO.setAverage_salary(rs.getInt("average_salery"));
					  companyDTO.setCapital_stock(rs.getString("capital_stock"));
					  companyDTO.setOccupation(rs.getString("occupation"));
					  companyDTO.setC_zipcode(rs.getString("c_zip_code"));
					  companyDTO.setC_road_address(rs.getString("c_road_address"));
					  companyDTO.setC_jibun_address(rs.getString("c_jibun_address"));
					  companyDTO.setC_detail_address(rs.getString("c_detail_address"));
					  companyDTO.setC_ref_address(rs.getString("c_ref_address"));
					  companyDTO.setC_regidate(rs.getString("c_regidate"));
					  companyDTO.setC_update(rs.getString("c_update"));
					  companyDTO.setM_email(rs.getString("m_email"));
		         }
		         System.out.println("조회");
		      } catch (Exception e) {
		         // TODO: handle exception
		         e.printStackTrace();
		      }
			
			return companyDTO;
		}
}
