package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.util.DBConn;

import ord.doit.domain.EmpDeptSalGradeVO;
import ord.doit.domain.EmpVO;
import ord.doit.domain.SalGradeVO;


/**
 * @author junyong
 * @date : 2024. 9. 3. 오후 12:36:41
 * @subject : 
 * @content:
 * 
 */
public class Ex04 {

	public static void main(String[] args) {
		
		String sql = " SELECT grade,  losal, hisal ,COUNT( grade ) cnt " 
							+" FROM emp e JOIN dept d ON e.deptno = d.deptno "
                			+" JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal "
							+" GROUP BY grade,losal,hisal "
							+" ORDER BY grade "; 

		String sql2 = "SELECT d.deptno, dname, empno, ename, sal+NVL(comm,0) pay  "
	            + "FROM dept d RIGHT JOIN emp e ON d.deptno = e.deptno "
	            + "            JOIN salgrade s ON sal BETWEEN losal AND hisal "
	            + "WHERE grade = ";

		
		Connection conn = null; //연결
		Statement stmt = null , stmt2 = null; //배달기사
		ResultSet rs = null, rs2 = null; //결과 받을놈

		//컬럼 자료형
		int deptno;
		String dname;
		int empno;
		String ename;
		double pay;
		
		int grade;
		int losal, hisal;
		int cnt;

		ArrayList<SalGradeVO> list = new ArrayList<>();
		SalGradeVO vo = null;

		try {

			conn = DBConn.getConnection();

			stmt = conn.createStatement(); //일꾼 객체 생성
			rs = stmt.executeQuery(sql); 

			while ( rs.next() ) {
				grade = rs.getInt("grade");
				losal = rs.getInt("losal");
				hisal = rs.getInt("hisal");
				cnt = rs.getInt("cnt");
				
				vo = new SalGradeVO().builder()
		                   .grade(grade)
		                   .losal(losal)
		                   .hisal(hisal)
		                   .cnt(cnt)
		                   .build();

				
				//list.add(vo);
				System.out.printf("%d등급 (  %d ~ %d  ) - %d명\n", vo.getGrade(), vo.getLosal() , vo.getHisal(), vo.getCnt() );
				
				String sql3 = sql2 + vo.getGrade(); //이거 없이
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sql3); //여기다가 sql2 + grade (혹은 vo.getGrade() 해도 됨)
				
				if ( rs2.next()) {
					do {
		                  // d.deptno, dname, empno, ename, sal
		                  deptno = rs2.getInt("deptno");
		                  dname = rs2.getString("dname");
		                  empno = rs2.getInt("empno");
		                  ename = rs2.getString("ename");
		                  pay = rs2.getDouble("pay");
		                  
		                  System.out.printf("\t%d\t%s\t%d\t%s\t%.2f \n",
		                        deptno, dname, empno, ename, pay);
		               } while (rs2.next());
		               
		               
		            }else {
		               System.out.println("\t 사원 존재 X"); 

				};//if
				
				
				//vo = new SalGradeVO().builder().grade(grade).losal(losal).hisal(hisal).cnt(cnt).build();
				

			}//while

//			list.forEach(svo -> 
//			System.out.printf("%d등급 (  %d ~ %d  ) - %d명\n", svo.getGrade(), svo.getLosal() , svo.getHisal(), svo.getCnt() )
//			);


		}

		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close(); //닫는 순서 중요
				stmt.close(); //일꾼도 닫아줘야함
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//main

}//class
/*
[실행결과]
1등급   (     700~1200 ) - 2명                        key
      20   RESEARCH   7369   SMITH   800               value
      30   SALES         7900   JAMES   950
2등급   (   1201~1400 ) - 2명
   30   SALES   7654   MARTIN   2650
   30   SALES   7521   WARD      1750   
3등급   (   1401~2000 ) - 2명
   30   SALES   7499   ALLEN      1900
   30   SALES   7844   TURNER   1500
4등급   (   2001~3000 ) - 4명
    10   ACCOUNTING   7782   CLARK   2450
   20   RESEARCH   7902   FORD   3000
   20   RESEARCH   7566   JONES   2975
   30   SALES   7698   BLAKE   2850
5등급   (   3001~9999 ) - 1명   
   10   ACCOUNTING   7839   KING   5000
*/      