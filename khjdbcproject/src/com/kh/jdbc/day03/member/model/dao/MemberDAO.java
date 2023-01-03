package com.kh.jdbc.day03.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberDAO {
	private Properties prop;
	
public MemberDAO() {
	prop = new Properties();
	FileReader reader;
	try {
		reader = new FileReader("resources/query.properties");
		prop.load(reader);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	/**
	 * 회원 전체 조회 DAO
	 * @param conn
	 * @return
	 */
	public List<Member> selectAll(Connection conn) {
		List<Member> mList = null;
		String sql = prop.getProperty("selectAll");
		try {
			// 연결을 JDBCTemplate로 옮김!
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			mList = new ArrayList<Member>();
			// 후처리
			while (rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getNString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));

				mList.add(member);
			}
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

	/**
	 * 회원 아이디로 조회 DAO
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOneById");
		Member member = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getNString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	/**
	 * 회원 이름으로 조회 DAO
	 * @param conn
	 * @param memberName
	 * @return
	 */
	public List<Member> selectAllbyName(Connection conn, String memberName) {
		String sql = prop.getProperty("selectAllbyName");
		List<Member> mList = null;
		Member member = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+memberName+"%");
			ResultSet rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			while(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				
				mList.add(member);
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	/**
	 * 회원가입 DAO
	 * @param conn
	 * @param member
	 * @return
	 */
	public int insertMember(Connection conn, Member member) {
		// Class.forName()
		// Connection conn = DriverManager~~~~~@%$#$%@#^
		int result = 0;
		String sql = prop.getProperty("insertMember");
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(9, member.getMemberHobby()); // 쿼리문 실행준비
			result = pstmt.executeUpdate(); // 쿼리문 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 회원 수정 DAO
	 * @param conn
	 * @param member
	 * @return
	 */
	public int updateMember(Connection conn, Member member) {
		// UPDATE MEMBER_TBL SET
		// MEMBER_PWD =?, MEMBER_EMAIL=?,MEMBER_PHONE=?, MEMBER_ADDRESS=?,
		// MEMBER_HOBBY=? WHERE MEMER_ID =?
		String sql = prop.getProperty("updateMember");
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberAddress());
			pstmt.setString(5, member.getMemberHobby());
			pstmt.setString(6, member.getMemberId());
			result = pstmt.executeUpdate(); // preparedstatement에는 안에쓰지않는다.

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 회원 삭제 DAO
	 * @param conn
	 * @param memberId
	 * @return
	 */
	public int deleteMember(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteMember");
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId); // 쿼리문실행준비완료
			result = pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
