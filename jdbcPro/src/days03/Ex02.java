package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

/**
 * @author junyong
 * @date : 2024. 9. 4. 오전 10:48:19
 * @subject : [jdbc] 트랜잭션 처리
 * @content: 논리적인 작업 단위 ( 모두완료/ 모두취소 )
 * 						예) 계좌 이체
 * 						A UPDATE
 * 						B UPDATE
 * 						는  ( 모두완료/ 모두취소 ) 여야함.		완료시 COMMIT/ 실패시 ROLLBACK
 * 
 * 오라클에선 저장프로시저에서 
 * BEGIN
 * 		1) UPDATE		INSERT	
 * 		2) UPDATE		INSERT	
 * EXCEPTION
 * 		WHEN ㅁㅁㅁ THEN 
 * 				ROLLBACK;
 * END;
 * 
 * 자바에서는   
 * try
 * 
 * 		1) UPDATE		INSERT	50 (O)
 * 		2) UPDATE		INSERT	50 (X 유니크 위배)  => 결론적으로 실패 롤백.
 * 
 * 		COMMIT;
 * catch
 * 		ROLLBACK;
 *	
 * 트랜잭션 처리는 오라클, 자바 어디에서 해도 상관없다
 * 
 */
public class Ex02 {

	public static void main(String[] args) {
		
		String sql = "INSERT INTO dept VALUES( ?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		//1,2

		try {
			conn = DBConn.getConnection();
			conn.setAutoCommit(false); // 오토커밋 해제.
			pstmt = conn.prepareStatement(sql);
			// 첫번째 인서트 (O)
			pstmt.setInt(1, 50);
			pstmt.setString(2, "QC");
			pstmt.setString(3, "SEOUL");
			
			rowCount = pstmt.executeUpdate();
			if ( rowCount == 1) {
				System.out.println("첫번째 부서추가 성공 !!");
			}
			//ORA-00001: unique constraint (SCOTT.PK_DEPT) violated 이후 제약조건 위배 걸리네
			//그리고 롤백되므로 첫번째 성공했던 50번 부서도 없음
			// 두번째 인서트 (X)
			pstmt.setInt(1, 50);
			pstmt.setString(2, "QC2");
			pstmt.setString(3, "SEOUL");
			
			rowCount = pstmt.executeUpdate();
			if ( rowCount == 1) {
				System.out.println("두번째 부서추가 성공 !!");
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); //얘도 트라이캐치 해줘야하네;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//4
		finally {
		try {
			pstmt.close();
			DBConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		}
	}//main

}//class
