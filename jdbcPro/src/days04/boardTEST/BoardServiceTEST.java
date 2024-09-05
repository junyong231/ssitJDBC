package days04.boardTEST;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import days04.board.domain.BoardDTO;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
import days04.board.service.BoardService;

class BoardServiceTEST {

	BoardDAO dao = null;
	Connection conn = null;
	BoardService service = null;

	//생성자
	public BoardServiceTEST() {
		this.conn = DBConn.getConnection();
		this.dao = new BoardDAOImpl(this.conn);
		this.service = new BoardService(this.dao);
	}

	@Test
	void testSelect() {
		//fail("Not yet implemented");
 
			ArrayList<BoardDTO> list = this.service.selectService(1, 10);
			list.forEach(dto-> { System.out.println(dto) ;}  ) ;
	 

	}
	@Test //새글쓰기
	void testInsert() {
		//fail("Not yet implemented");
 
		BoardDTO dto = new BoardDTO().builder()
									.writer("홍낄동2")
									.pwd("1234")
									.email("asd2@naver.com")
									.title("단위테스트")
									.tag(0)
									.content("zzzz")
									.build();
		
		int rowCount;
		try {
			rowCount = this.dao.insert(dto);
			if (rowCount ==1) {
				System.out.println("글쓰기 성공!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	 

	}
}
