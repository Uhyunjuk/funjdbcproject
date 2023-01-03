package com.kh.jdbc.day03.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.day03.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberService {

	private JDBCTemplate jdbcTemplate;
	MemberDAO mDao = new MemberDAO();

	public MemberService() {
//		jdbcTemplate = JDBCTemplate.getDriverLoad();
		mDao = new MemberDAO(); // 생략하지 말기 !!
	}

	/**
	 * 회원 전체 조회 Service
	 * @return
	 */
	public List<Member> selectAll() {
		List<Member> mList = null;
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad(); 
		Connection conn = JDBCTemplate.getConnection();
		mList = mDao.selectAll(conn);
		return mList;
	}

	/**
	 * 회원 아이디로 조회 Service
	 * @param memberId
	 * @return
	 */
	public Member selectOnebyId(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = mDao.selectOneById(conn, memberId);
		return member;
	}

	/**
	 * 회원 이름으로 조회 Service
	 * @param memberName
	 * @return List<Member>
	 */
	public List<Member> selectAllbyName(String memberName) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> mList = mDao.selectAllbyName(conn, memberName);
		return mList;

	}

	/**
	 * 회원 가입 Service
	 * @param member
	 * @return int
	 */
	public int insertMember(Member member) {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	/**
	 * 회원 수정 Service
	 * @param member
	 * @return
	 */
	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.updateMember(conn, member);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	/**
	 * 회원 삭제 Service
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.deleteMember(conn, memberId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
