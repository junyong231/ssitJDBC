package days04.board.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;

/**
 * @author junyong
 * @date : 2024. 9. 5. 오전 10:25:55
 * @subject : 
 * @content:
 * 
 */
public interface BoardDAO {
	//1) 페이징 처리 X + 게시글 목록
	ArrayList<BoardDTO> select() throws SQLException; //sql 에러 발생시 부모 클래스(매니저)에 보냄
	
	//1-2 ) 페이징 처리 O + 게시글 목록
	ArrayList<BoardDTO> select(int currentPage , int numberPerPage ) throws SQLException;
	
	// 1-3)
	// 총 레코드 수를 가져오는 쿼리
	int getTotalRecords() throws SQLException;
	//1-4)
	// 총 페이지 수를 가져오는 함수.. 도 필요하겠다
	int getTotalPages( int numberPerPage ) throws SQLException;
	
	//2) 게시글(새글)쓰기
	int insert( BoardDTO dto ) throws SQLException; // 함수 :  

	//3) 조회수 증가
	int increaseReaded(long seq) throws SQLException;
	
	//3-2) 게시글 상세보기
	BoardDTO view(long seq) throws SQLException;

	//4) 게시글 삭제
	int delete(long seq) throws SQLException;

	//5) 게시글 수정
	int update(BoardDTO dto) throws SQLException; // int = 리턴값 ,  ***dto = 파라미터, 수정할 내용을 담은 객체***  
	
	//6) 게시글 검색 (제목)
	ArrayList<BoardDTO> searchT(String searchKeyword) throws SQLException; 
	
	//6-2) 게시글 검색 (작성자)
	ArrayList<BoardDTO> searchA(String searchKeyword) throws SQLException;
//	//6-3) 게시글 검색 (제목+내용)
//	ArrayList<BoardDTO> searchTC(String searchKeyword) throws SQLException;
	//6-4) 제목+내용 페이징처리완료 버전
	public ArrayList<BoardDTO> searchTC(String searchKeyword,int currentPage, int numberPerPage) throws SQLException;

	// 검색용 페이징 처리
	int getTotalPagesTC(int numberPerPage, String searchKeyword) throws SQLException;


}//interface












