package com.gn.board.service;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.commit;
import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;
public class BoardService {
	
	public Board selectBoardOne(int boardNo){
		Connection conn = getConnection();
		Board result = new BoardDao().selectBoardOne(conn, boardNo);
		close(conn);
		return result;
		
	}
	
	public int selectBoardCount(Board option) {
		Connection conn = getConnection();
		int result = new BoardDao().selectBoardCount(conn, option);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(Board option){
		Connection conn = getConnection();
		List<Board> resultList = new ArrayList<Board>();
		resultList = new BoardDao().selectBoardList(conn,option);
		return resultList;
	}
	
	public int createBoard(Board b, Attach a) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1.Auto 꺼주고
			conn.setAutoCommit(false);
			// 2.insert 해주고
			int boardNo = new BoardDao().insertBoard(b,conn);
			a.setBoardNo(boardNo);
			int attachNo = new BoardDao().insertAttach(a,conn);
			// 3. rollback 
			if(boardNo != 0 && attachNo != 0){
				result = 1;
				commit(conn);
			}else{
				rollback(conn);
			}
			
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
			
		}
		close(conn);
		return result;
	}
}
