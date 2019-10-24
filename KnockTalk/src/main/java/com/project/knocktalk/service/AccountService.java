package com.project.knocktalk.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.knocktalk.dao.AccountDao;
import com.project.knocktalk.service.email.MailUtils;
import com.project.knocktalk.service.email.TempKey;

@Service
public class AccountService {
	
	public static final String EMAIL_UNCONFIRMED = "0"; // 회원가입을 위한 이메일 인증 미확인상태
	public static final String EMAIL_CONFIRM = "1"; // 회원가입을 위한 이메일 인증 확인상태
	public static final String EMAIL_MODIFY = "3"; // 이메일 변경을 위한 authstatus의 상태
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	AccountDao accountDao;
	@Autowired
	HttpSession session;
	
	@Transactional
	public void create(final String email) throws Exception {
		System.out.println(email);
		if (session.getAttribute("authkey")!=null && session.getAttribute("authstatus")!=null) {
			session.removeAttribute("authkey");
			session.removeAttribute("authstatus");
			session.invalidate();
		} else {
			// 임의의 authkey 생성
			final String authkey = new TempKey().getKey(50, false);

			session.setAttribute("authkey", authkey);	
			session.setAttribute("authstatus", EMAIL_UNCONFIRMED); // 이메일 인증 전 상태 0 or 3
			System.out.println("hello 1 : " + session.getAttribute("authstatus"));

			// mail 작성 관련
			new Thread(new Runnable() {
				@Override
				public void run() {
					MailUtils sendMail;
					try {
						sendMail = new MailUtils(mailSender);
					
						sendMail.setSubject("회원가입 이메일 인증");
						sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
								.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
								.append("<a href='http://localhost:2080/knocktalk/emailConfirm.do?user_email=")
								.append(email)
								.append("&authkey=")
								.append(authkey)
								.append("' target='_blenk'>이메일 인증 확인</a>")
								.toString());
						sendMail.setFrom("webproject1400@gmail.com", "관리자명");
						sendMail.setTo(email);
						sendMail.send();
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			}).start();
			
		}
	}
}
