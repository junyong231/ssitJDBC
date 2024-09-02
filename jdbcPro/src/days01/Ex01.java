package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex01 {

	public static void main(String[] args) {
		System.out.println("hello worlds");
		System.out.println("살려주세요");
		
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SCOTT"; //대소문자 주의
		String password= "TIGER";
		Connection conn = null; //다리
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println( conn );
			if (conn != null) {
					conn.close();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}//f

	}//main 

}//class

// JDBCClass 폴더 생성 -> 이클립스 실행 설정 (폰트 jdk..) -> JAVA 프로젝트 생성 -> jdbcPro -> 



