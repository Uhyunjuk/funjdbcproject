package com.kh.jdbc.day02.member.comtroller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {

	/**
	 * 회원 목록 전체 조회
	 * 
	 * @return List<Member>
	 */
	public List<Member> printAll() { // SELECT * FROM MEMBER_TBL
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAll();
		return mList;
	}

	/**
	 * 회원 아이디로 조회
	 * @param studentId
	 * @return Member
	 */
	public Member printOneById(String memberId) {
		//ResultSet이 1개면 Member
		//ResultSet이 1개 이상이면 List<Memeber>
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	
	
	/**
	 * 회원 이름으로 조회
	 * @param memberName
	 * @return List<Member>
	 */
	public List<Member> printAllbyName(String memberName) {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAllbyName(memberName);
		return mList;
	}
	
	/**
	 * 멤버 회원가입
	 * @param member
	 * @return int
	 */
	public int registerMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
	
	/**
	 * 회원 수정
	 * @param member
	 * @return int
	 */
	public int modifyMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(member);
		return result;
	}
	
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @return int
	 */
	public int removeMember(String memberId) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}
	
	/**
	 * 회원 로그인하기
	 * @param member
	 * @return
	 */
	public int checkInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}
}
