package com.kh.jdbc.day03.member.controller;

import java.util.List;

import com.kh.jdbc.day03.member.model.service.MemberService;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberController {
	
	private MemberService mService;
	
	public MemberController() {
		mService = new MemberService(); // 까먹지말기 -> Null PointException 발생 
	}
	
	/**
	 * 회원 전체 조회 Controller
	 * @return
	 */
	public List<Member> printAll() {
		List<Member> mList = mService.selectAll();
		return mList;
	}
	
	/**
	 * 회원 아이디로 조회 Controller
	 * @param memberId
	 * @return
	 */
	public Member printOnebyId(String memberId) {
		Member member = mService.selectOnebyId(memberId);
		return member;
	}
	
	/**
	 * 회원 이름으로 조회 Controller
	 * @param memberName
	 * @return
	 */
	public List<Member> printAllbyName(String memberName) {
		List<Member> mList = mService.selectAllbyName(memberName);
		return mList;
	}

	/**
	 * 회원 가입 Controller
	 * @param member
	 * @return
	 */
	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	
	/**
	 * 회원 수정 Controller
	 * @param member
	 * @return
	 */
	public int modifyMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
	
	/**
	 * 회원 삭제 Controller
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId) {
		int result = mService.deleteMember(memberId);
		return result;
	}
}
