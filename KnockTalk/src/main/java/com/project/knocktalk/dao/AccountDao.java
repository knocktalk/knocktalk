package com.project.knocktalk.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.knocktalk.vo.AccountVo;

@Repository("accountDao")
public class AccountDao {
	
	public static final int SUCCESS = 1;
	
	@Autowired
	SqlSession sqlSession;
	
	public boolean overlapCheck(String user_id) {
		return sqlSession.selectOne("account.selectOne_id", user_id) != null;
	}
	public AccountVo login(String user_id) {
		return sqlSession.selectOne("account.select_login", user_id);
	}
	public int insert(AccountVo accountVo) {
		return sqlSession.insert("account.insert", accountVo);
	}
	// 마이페이지
	public AccountVo mypage(String user_id) {
		return sqlSession.selectOne("mypage.selectOne_mypage", user_id);
	}
	
	// 마이페이지 수정 : 별명 , 휴대폰 번호 수정
	 public int my_information_edit(AccountVo accountVo) { 
		 return sqlSession.update("mypage.my_information_edit", accountVo); 
	}
	 
	 //마이페이지 수정 : 비밀번호
	 public int my_password_edit(AccountVo accountVo) {
		 return sqlSession.update("mypage.my_password_edit", accountVo);
	 }
	 
	 //마이페이지 수정 : 이메일
	 public int my_email_edit(AccountVo accountVo) {
		 return sqlSession.update("mypage.my_email_edit", accountVo);
	 }
	 
	// DB에 저장된 이메일 조회(마이페이지 수정 시 이메일 변경을 했는지 확인하기 위함)
	 public String selectOne_email(String user_id) {
		 return sqlSession.selectOne("mypage.selectOne_email", user_id);
	 }
	 // 이메일 수정
	 public int update_email(AccountVo vo) {
		 return sqlSession.update("mypage.update_email", vo);
	 }
	 // 회원탈퇴
	 public int user_signout(String user_id) {
		 return sqlSession.delete("mypage.user_signout", user_id);
	 }
}
