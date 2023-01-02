package com.kh.jdbc.day03.member.run;

import java.util.List;

import com.kh.jdbc.day03.member.controller.MemberController;
import com.kh.jdbc.day03.member.model.vo.Member;
import com.kh.jdbc.day03.member.view.MemberView;

public class MemberRun {

	public static void main(String[] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = null;
		List<Member> mList = null;
		int result = 0;
		String memberId = "";
		
		
		bye:
		while (true) {
			int choice = mView.mainMenu();
			switch (choice) {
			case 0:
				break bye;
			case 1:
				mList = mCon.printAll();
				if(mList.size() > 0) {
					mView.showAll(mList);
				}else {
					mView.displayError("회원정보가 없습니다.");
				}
				break;
			case 2:
				memberId = mView.inputMemberId("검색");
				
				break;
			case 3:
				break;
			case 4:
				member = mView.inputMember();
				result = mCon.registerMember(member);
				if(result > 0) {
					mView.displaySuccess("가입 성공");
				}else {
					mView.displayError("회원가입이 완료되지 않았습니다.");
				}
				break;
			case 5:
				memberId = mView.inputMemberId("수정");
				member = mCon.printOnebyId(memberId);
				if(member != null) {
					member = mView.modifyMember(memberId);
					result = mCon.modifyMember(member);
					if(result > 0) {
						mView.displaySuccess("회원 정보 수정 성공!");
					}else {
						mView.displayError("회원 정보 수정 실패!");					}
				}else {
					mView.displayError("존재하지 않는 회원입니다.");
				}
				break;
			case 6:
				break;
			}
		}
	}

}
