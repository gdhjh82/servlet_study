package com.gn.member.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.member.vo.Member;
import static com.gn.common.sql.JDBCTemplate.close;
public class MemberDao {
	
	// createMember 메소드
	// 매개변수로 Connection , Member 받아서
	// DB에 INSERT(member_id, member_pw, member_name)
	// ResultSet(x) , executeUpdate(o)
	// 결과를 int로 반환
	public int createMember(Member m, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO member (member_id,member_pw,member_name) "
						+ "VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2,m.getMemberPw());
			pstmt.setString(3,m.getMemberName());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
	}
	public Member loginMember(String id, String pw, Connection conn) {
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m = new Member();
				m.setMemberNO(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return m;
	}
	public int updateMember(String pw, String name, String no, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "UPDATE member SET member_pw = ?,  member_name = ? WHERE member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			pstmt.setString(2,name);
			pstmt.setString(3,no);
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		
		}
		return result;
	}
	
	public Member getMemberByNo(String no, Connection conn) {
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM member WHERE member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m = new Member();
				m.setMemberNO(rs.getInt("member_no"));
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		return m;
	}
}
