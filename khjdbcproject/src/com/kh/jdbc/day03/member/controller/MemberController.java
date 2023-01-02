package com.kh.jdbc.day03.member.controller;

import java.util.List;

import com.kh.jdbc.day03.member.model.service.MemberService;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberController {
	
	private MemberService mService;
	
	public MemberController() {
		mService = new MemberService(); // 까먹지말기 -> Null PointException 발생 
	}
	
	public List<Member> printAll() {
		List<Member> mList = mService.selectAll();
		return mList;
	}
	
	public Member printOnebyId(String memberId) {
		Member member = mService.selectOnebyId(memberId);
		return member;
	}

	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	
	public int modifyMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
}
