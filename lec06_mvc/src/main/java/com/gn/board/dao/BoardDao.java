package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;
public class BoardDao {
	public Board selectBoardOne(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board result = null;
		try {
			String sql="SELECT * FROM 'board' b JOIN `member` m ON b.board_writer = m.member_no WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				result = new Board();
				result.setBoardTitle(rs.getString("board_title"));
				result.setBoardContent(rs.getString("board_content"));
				result.setBoardWriter(rs.getInt("board_writer"));
				result.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				result.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
				result.setMemberName(rs.getString("member_name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectBoardCount(Connection conn, Board option) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "SELECT COUNT(*) FROM board ";
			if(option.getBoardTitle() != null) {
				sql += "WHERE board_title LIKE CONCAT('%','"+option.getBoardTitle()+"','%')";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Board> selectBoardList(Connection conn, Board option){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> result = new ArrayList<>();
		try {
			 String sql = "SELECT * "
					+ "FROM board b "
					+ "JOIN member m "
					+ "ON b.board_writer = m.member_no ";
		if(option.getBoardTitle() != null) {
				sql += "WHERE board_title Like CONCAT('%','"+option.getBoardTitle()+"','%') ";
		}
		///추가///
		sql += "LIMIT "+option.getLimitPageNo()+", "+option.getNumPerPage();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Board b = new Board();
			b.setBoardNo(rs.getInt("board_no"));
			b.setBoardTitle(rs.getString("board_title"));
			b.setBoardContent(rs.getString("board_content"));
			//b.setBoardWriter(rs.getInt("board_writer"));
			b.setMemberName(rs.getString("member_name"));
			b.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
			b.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
			result.add(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null)rs.close();
				if(conn != null)rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
		//게시글 번호(board_no)
		//게시글 제목(board_title)
		//게시글 내용(board_content)
		//게시글 작성자(board_writer)
		//게시글 작성자의 아이디 또는 닉네임 정보.
		//게시글 등록일(reg_date)
		//게시글 수정일(mod_date) 
		// INNERJOIN
	}
	
	public int insertBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardNo = 0;
		try {
			String sql = "INSERT INTO `board`(board_title ,board_content ,board_writer) " 
						+"VALUES(?,?,?)";
			// (1) 매개변수 추가
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			
			boardNo = pstmt.executeUpdate();
			//(2) 생성된 키 반환
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardNo;
	}
	
	public int insertAttach(Attach a, Connection conn) {
		// 1. board_no
		// 2. ori_name
		// 3. new_name
		// 4. attach_path insert
		// 5. 반환받아오는 것 까지.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int attachNo = 0;
		try {
			String sql = "INSERT INTO `attach`(board_no ,ori_name ,new_name ,attach_path) "
						+"VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getBoardNo());
			pstmt.setString(2, a.getOriName());
			pstmt.setString(3, a.getNewName());
			pstmt.setString(4, a.getAttachPath());
			
			attachNo = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				attachNo = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return attachNo;
	}
}
