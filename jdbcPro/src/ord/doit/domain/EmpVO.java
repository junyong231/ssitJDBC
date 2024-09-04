package ord.doit.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author User
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpVO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private LocalDateTime hiredate; 
	private double sal;
	private double comm;
	private int deptno;
}   //class







