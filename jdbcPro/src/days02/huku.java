package days02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author junyong
 * @date : 2024. 9. 3. 오후 5:26:33
 * @subject :
 * @content:
 * 
 */
public class huku {

	public static void main(String[] args) {
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SCOTT";
		String password = "TIGER";
		Connection conn = null; //커널
		Statement stmt = null; //드론
		ResultSet rs = null;
		
		//받아올 것들 자료형
		int deptno; 
		
		
		
		String sql = " SELECT deptno "
						+ "FROM emp ";
		
		//1. jdbc 드라이버 로딩
		try {
			Class.forName(className);
		//2. Connection 객체	
			conn =  DriverManager.getConnection(url, user, password);
		//3. CRUD - 일꾼
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while ( rs.next()) {
				deptno = rs.getInt("deptno");
				System.out.printf("%d\n", deptno );
			}


			//4.
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
