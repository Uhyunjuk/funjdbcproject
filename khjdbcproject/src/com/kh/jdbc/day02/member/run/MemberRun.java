package com.kh.jdbc.day02.member.run;

import java.util.List;

import com.kh.jdbc.day02.member.comtroller.MemberController;
import com.kh.jdbc.day02.member.model.vo.Member;
import com.kh.jdbc.day02.member.view.MemberView;

public class MemberRun {
	public static void main(String[] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = null;
		List<Member> mList = null;
		String memberId = "";
		String memberName = "";
		int result = 0;

		goodbye: while (true) {
			int choice = mView.mainMenu();
			switch (choice) {
			case 0:
				break goodbye;
			case 1:
				// 전체조회
				mList = mCon.printAll();
				if (!mList.isEmpty()) {
					mView.showAll(mList);
				} else {
					mView.displayError("데이터가 존재하지 않습니다.");
				}
				break;
			case 2:
				// 아이디로 조회
				memberId = mView.inputMemberId("검색 ");
				member = mCon.printOneBtId(memberId);
				if (member != null) {
					mView.showOne(member);
				} else {
					mView.displayError("일치하는 데이터가 없습니다.");
				}
				break;
			case 3:
				// 이름으로 조회
				memberName = mView.inputMemberName("검색");
				mList = mCon.printAllbyName(memberName);
				if (!mList.isEmpty()) {
					mView.showAll(mList);
				} else {
					mView.displayError("일치하는 데이터가 없습니다.");
				}
				break;
			case 4:
				// 회원가입
				member = mView.inputMember();
				result = mCon.registerMember(member);
				if (result > 0) {
					mView.displaySuccess("회원가입 완료 ^^*");
				} else {
					mView.displaySuccess("회원가입 실패");
				}
				break;
			case 5:
				// 회원 정보수정
				memberId = mView.inputMemberId("수정");
				member = mCon.printOneBtId(memberId);
				if (member != null) {
					member = mView.modifyMember(member);
					mCon.modifyMember(member);
					mView.displaySuccess("회원정보 수정 완료 >< !");
				} else {
					mView.displayError("일치하는 회원이 없습니다.");
				}
				break;
			case 6:
				// 회원탈퇴
				memberId = mView.inputMemberId("삭제");
				result = mCon.removeMember(memberId);
				if(result > 0) {
					mView.displaySuccess("탈퇴 완료 !");
				}else {
					mView.displayError("탈퇴 실패");
				}
				break;
			case 7: 
				// 로그인 기능
				member = mView.inputLoginInfo();
				result = mCon.checkInfo(member);
				if(result > 0) {
					mView.displaySuccess("로그인 성공 ↖^^↗");
				}else {
					mView.displayError("일치하는 정보가 존재하지 않습니다.");
				}
				break;
			default:
				mView.printMessage("잘못입력하셨습니다. 1 ~ 6 사이의 수를 입력해주세요.");
				break;
			}
		}
	}
}
