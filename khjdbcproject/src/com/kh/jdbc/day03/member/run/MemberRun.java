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
		String memberId = "";
		String memberName = " ";
		int result = 0;

		bye: while (true) {
			int choice = mView.mainMenu();
			switch (choice) {
			case 0:
				break bye;
			case 1:
				mList = mCon.printAll();
				if (mList.size() > 0) {
					mView.showAll(mList);
				} else {
					mView.displayError("회원정보가 없습니다.");
				}
				break;
			case 2:
				memberId = mView.inputMemberId("검색 ");
				member = mCon.printOnebyId(memberId);
				if (member != null) {
					mView.showOne(member);
				} else {
					mView.displayError("일치하는 회원이 없습니다.");
				}
				break;
			case 3:
				memberName = mView.inputMemberName("검색");
				mList = mCon.printAllbyName(memberName);
				if (mList.size() > 0) {
					mView.showAll(mList);
				} else {
					mView.displayError("일치하는 회원이 없습니다.");
				}
				break;
			case 4:
				member = mView.inputMember();
				result = mCon.registerMember(member);
				if (result > 0) {
					mView.displaySuccess("가입 성공");
				} else {
					mView.displayError("회원가입이 완료되지 않았습니다.");
				}
				break;
			case 5:
				memberId = mView.inputMemberId("수정");
				member = mCon.printOnebyId(memberId);
				if (member != null) {
					member = mView.modifyMember(memberId);
					result = mCon.modifyMember(member);
					if (result > 0) {
						mView.displaySuccess("회원 정보 수정 성공!");
					} else {
						mView.displayError("회원 정보 수정 실패!");
					}
				} else {
					mView.displayError("존재하지 않는 회원입니다.");
				}
				break;
			case 6:
				memberId = mView.inputMemberId("삭제");
				result = mCon.removeMember(memberId);
				if (result > 0) {
					mView.displaySuccess("회원 탈퇴 성공");
				} else {
					mView.displayError("회원 탈퇴가 완료되지 않았습니다.");
				}
				break;
			}
		}
	}

}
