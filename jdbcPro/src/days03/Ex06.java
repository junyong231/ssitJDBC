package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

/**
 * @author junyong
 * @date : 2024. 9. 4. 오후 4:14:22
 * @subject : [ login ] /인가 ( 접속 자체 )
 * @content: 아이디/ 비밀번호 입력
 * 						[로그인] [회원가입]
 * 
 * 	emp 테이블을 회원테이블이라고 생각// empno (id) / ename(pw) 
 * 
 * 
 */
public class Ex06 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//회원 테이블이랄게 따로 없으니 emp테이블의 empno를 ID라고 가정한다
		System.out.println("> 로그인할 ID,PWD  입력(empno) ? ");
		int id = sc.nextInt(); //7369 smith
		String pwd = sc.next(); //비번 - ename
		

		String sql = " { call up_login(? , ? , ? ) }"; //id, pw, 출력용 매개변수

		Connection conn = null;
		CallableStatement cstmt = null;
		int check = -1; //값은 의미는 없고 초기화

		conn = DBConn.getConnection();

		try {
			cstmt = conn.prepareCall(sql);
			// IN ? OUT ?
			cstmt.setInt(1, id);
			cstmt.setString(2, pwd);
			
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			cstmt.executeQuery(); //SELECT니까
			//rs 필요없다
			check = cstmt.getInt(3);
			//			System.out.println(check);
			if (check == 0) {
				System.out.println("로그인 성공 !!!");
			} else if ( check == 1) {
				System.out.println("ID는 존재하지만 비밀번호 잘못됨");
			} else if (check == -1) {
				System.out.println("존재하지 않는 아이디입니다.");
			}

			
	

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//f


		



	}//main
}//class

