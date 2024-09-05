package days04.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author junyong
 * @date : 2024. 9. 5. 오전 10:17:41
 * @subject :
 * @content:
 * 
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BoardDTO {


		  private long seq; 
		  private String writer ;                                    
		  private String pwd;                                  
		  private String email;                         
		  private String title;                             
		  private Date writedate ;
		  private int readed;                               
		  private int tag;                           
		  private String content;                                                    



}//class
