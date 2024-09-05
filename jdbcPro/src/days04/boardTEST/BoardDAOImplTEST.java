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

class BoardDAOImplTEST {

	BoardDAO dao = null;
	Connection conn = null;
	
	//생성자
	public BoardDAOImplTEST() {
		this.conn = DBConn.getConnection();
		this.dao = new BoardDAOImpl(this.conn);
	}
	
	@Test
	void testSelect() {
		//fail("Not yet implemented");
		try {
			//ArrayList<BoardDTO> list = this.dao.select();
			ArrayList<BoardDTO> list = this.dao.select(1, 10);
			list.forEach(dto-> { System.out.println(dto) ;}  ) ;
			list = this.dao.select();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
