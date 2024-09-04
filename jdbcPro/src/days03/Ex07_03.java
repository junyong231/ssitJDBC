package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

/**
 * @author junyong
 * @date : 2024. 9. 4. 오후 5:31:25 
 * @subject : 부서추가 (EX01) 프로시저로 해보기 
 * @content:
 * 
 */
public class Ex07_03 {

	public static void main(String[] args) {
		
		String sql = "{CALL UP_CRE(?,?,?)}"; //부서번호 부서명 지역명
	      
	      Connection conn = null;
	      CallableStatement cstmt = null;
	      int rowCount = 0;
	      
	      int pdeptno = 60; // 수정할 부서
	      String pdname= "QC",ploc = "SEOUL"; //수정할 부서명//수정할 지역명
	      
	      conn =  DBConn.getConnection();
	      try {
	         cstmt = conn.prepareCall(sql);    
	         
	         cstmt.setInt(1, pdeptno);    
	         cstmt.setString(2, pdname);  
	         cstmt.setString(3, ploc);   
	         
	         rowCount = cstmt.executeUpdate(); // DML문 
	         
	         if (rowCount == 1) {
	            System.out.println("부서 추가 성공!!");
	         } else {
	            System.out.println("부서 추가 실패!!");
	         }  
	         
	      } catch (SQLException e) { 
	         e.printStackTrace();
	      } catch (Exception e) { 
	         e.printStackTrace();
	      } finally {
	         try { 
	            cstmt.close();
	         } catch (SQLException e) { 
	            e.printStackTrace();
	         }
	      }
	      
	      DBConn.close();
	      System.out.println(" end ");

	

	}//main

}//class
