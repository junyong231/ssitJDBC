package days04;

import java.sql.Connection;

import com.util.DBConn;

import days04.board.controller.BoardController;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
import days04.board.service.BoardService;

/**
 * @author junyong
 * @date : 2024. 9. 5. 오전 9:00:24
 * @subject : [ 콘솔에 게시판 만들기 ] 
 * @content: 
 * 
 * 1. 패키지 선언
 * 		ㄴ days04.board
 * 		ㄴ days04.board.controller	- 
 * 		ㄴ days04.board.service			- 확장성 위해 남김
 * 		ㄴ days04.board.persistence - DB 연동 처리만 하는.. / DAO 
 * 		ㄴ days04.board.domain 		- DTO(데이터 전송) , VO(밸류 오브젝트)  둘 다 값 전달 역할
 * 
 */
public class Ex01 {

	public static void main(String[] args) {
		
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService(dao);
		BoardController controller = new   BoardController(service);
		
		controller.boardStart();
		
		
		
		

	}//main

}//class

/*
CREATE SEQUENCE seq_tblcstVSBoard
NOCACHE;

CREATE TABLE tbl_cstVSBoard (
  seq NUMBER  NOT NULL PRIMARY KEY, --글번호
  writer VARCHAR2 (20) NOT NULL ,                                    --작성자
  pwd VARCHAR2 (20) NOT NULL ,                                     -- 게시글 비번
  email VARCHAR2 (100) ,                                        -- 글쓴이 메일
  title VARCHAR2 (200) NOT NULL ,                                     -- 제목 
  writedate DATE DEFAULT sysdate,     -- 작성일
  readed NUMBER DEFAULT 0,                                  --조회수
  tag NUMBER(1) NOT NULL ,                                               --글의 형식  (html..) 0 = 텍스트, 1 = html
  content CLOB                                                    --내용
);
-- 더미데이터 생성 !!
BEGIN

    FOR i IN 1..150 LOOP
        INSERT INTO tbl_cstVSBoard (seq, writer, pwd, email, title, tag, content)
        VALUES ( seq_tblcstVSBoard.NEXTVAL, '홍길동' || MOD(i,10), '1234', '홍길동' || MOD(i,10) || '@sist.co.kr', '더미...' || i, 0 , '더미...' || i );
    END LOOP;
    COMMIT;

END;
--
BEGIN
    UPDATE tbl_cstVSBoard
    SET writer = '박준용'
    WHERE MOD(seq, 15) = 4;
    COMMIT;
END;
--
BEGIN
    UPDATE tbl_cstVSBoard
    SET title = '게시판 구현'
    WHERE MOD(seq, 15) IN ( 3, 5 , 8 );
    COMMIT;
END;
--


*/