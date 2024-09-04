package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 * @author junyong
 * @date : 2024. 9. 4. 오후 3:38:14
 * @subject : [ OracleCallableStatement ]
 * @content:
 * // [저장 프로시저]   - 입력받은 ID를 사용 여부 체크하는 프로시저
      //       ㄴ 회원가입
      //             아이디 : [   hong     ] <ID중복체크버튼> : 한 프로시저에 넣는게 아니고 아이디 중복체크할 때마다 누르니까 똑같이 프로시저도 나눠야함
      //             비밀번호      
      //             이메일
      //             주소
      //             연락처
      //             등등
 */
public class Ex05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//회원 테이블이랄게 따로 없으니 emp테이블의 empno를 ID라고 가정한다
		System.out.println("> 중복 체크할 ID  입력(empno) ? ");
		int id = sc.nextInt(); //7369 smith

		//up_idcheck 프로시저  cstmt 이용해서 처리 코딩
		String sql = " { call up_idcheck(pid=>? ,pcheck=>? ) }";

		Connection conn = null;
		CallableStatement cstmt = null;
		int check = -1; //값은 의미는 없고 초기화

		conn = DBConn.getConnection();

		try {
			cstmt = conn.prepareCall(sql);
			// IN ? OUT ?
			cstmt.setInt(1, id);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			cstmt.executeQuery(); //SELECT니까
			check = cstmt.getInt(2);
//			System.out.println(check);
			if (check == 0) {
				System.out.println("사용 가능한 ID입니다");
			} else {
				System.out.println("이미 사용 중인 ID입니다.");
			}
			
			
			//rs 필요없다

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//f


		DBConn.close();



	}//main

}//class
