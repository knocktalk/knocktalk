package com.project.knocktalk;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.project.knocktalk.dao.AccountDao;
import com.project.knocktalk.service.AccountService;
import com.project.knocktalk.vo.AccountVo;

@Controller
public class HomeController {
	
	/** 변수 선언부 **/
	@Autowired
	AccountService accountService;
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	BCryptPasswordEncoder bCryptpasswordEncoder;
	
	@Autowired
	ServletContext application;
	@Autowired
	HttpSession session;
	
	private static final String PREFIX = "/WEB-INF/views/";
	private static final int IS_ADMIN = 1;
	
	private static final String EMAIL_UNCONFIRMED = "0"; // 회원가입을 위한 이메일 인증 미확인상태
	private static final int EMAIL_CONFIRM = 1; // 회원가입을 위한 이메일 인증 확인상태
	private static final int EMAIL_MODIFY = 3; // 이메일 변경을 위한 authstatus의 상태
	
	private static String message = "";
	
	/** 메인 Controller **/
	@RequestMapping(value = {"/", "index.page"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return PREFIX + "index.jsp";
	}
	
	/** 로그인 Controller **/
	@RequestMapping(value = "login.page")
	public String account_login() {
		return PREFIX + "account/login.jsp";
	}
	@RequestMapping(value = "account_login.do")
	public String account_login(Model model, AccountVo accountVo) {
		AccountVo account = accountDao.login(accountVo.getUser_id());
		
		boolean isMatched = bCryptpasswordEncoder.matches(accountVo.getUser_password(), account.getUser_password());
		
		if (account!=null && isMatched) {
			session.setAttribute("user_id", account.getUser_id()); // 회원 아이디
			session.setAttribute("user_imagepath", account.getUser_imagepath());
			session.setAttribute("user_imagename", account.getUser_imagename());
			
			if (account.getUser_isAdmin()==IS_ADMIN) {
				System.out.println("관리자 로그인!");
				session.setAttribute("user_isAdmin", account.getUser_isAdmin()); // 관리자 확인				
			}
		} else {
			message = "아이디 혹은 비밀번호를 다시 확인해주세요.";
			model.addAttribute("loginMessage", message);
			
			return "login.page";
		}
		
		return "redirect:/";
	}
	
	/** 로그아웃 Controller **/
	@RequestMapping(value = "logout.do")
	public String account_logout() {
		session.removeAttribute("user_id");
		session.removeAttribute("user_isAdmin");
		session.invalidate();
		return "redirect:/";
	}
	
	/** 회원가입 Controller **/ /** 절차 : (약관) > (인증) > (가입) > (설정) > (완료) **/
	/// 회원가입 버튼을 누르면
	@RequestMapping(value = "register.page")
	public String clause() {
		return PREFIX + "account/clause.jsp";
	}
	/// 약관에 동의를 하면
	@RequestMapping(value = "account_clause.do")
	public String certification() {
		return PREFIX + "account/certification.jsp";
	}
	/// 이메일 인증 '버튼'을 누르면
	@RequestMapping(value = "account_certification.do")
	public String register(Model model, String user_email) throws Exception {
		accountService.create(user_email);
//		String subString = user_email.substring(user_email.indexOf("@")+1);
//		System.out.println("redirect:https://" + subString);
		model.addAttribute("user_email", user_email);
		return PREFIX + "account/standby.jsp";
	}
	/// 이메일 인증 '링크'를 누르면
	@RequestMapping(value="emailConfirm.do", method=RequestMethod.GET)
	public String emailConfirm(Model model, String authkey, String user_email) throws Exception {
		if(session.getAttribute("authkey").equals(authkey)) {
			System.out.println("hello 2 : " + session.getAttribute("authstatus"));
			
			if((String)session.getAttribute("authstatus") == AccountService.EMAIL_MODIFY) { // 이메일 정보 수정
				AccountVo vo = new AccountVo();
				vo.setUser_id((String)session.getAttribute("user_id"));
				vo.setUser_email(user_email);
				
				accountDao.update_email(vo);
				
				session.removeAttribute("authstatus");
				
				return "/";
			}
			
			session.setAttribute("authstatus", AccountService.EMAIL_CONFIRM);
			model.addAttribute("user_email", user_email);
			
			return PREFIX + "account/register.jsp"; // 회원가입 페이지로 이동
		}
		// 유저에게 발급된 키가 유효하지 않은 경우
		return "account_create.page";
	}
	/// '다음' 버튼을 누르면
	@RequestMapping(value = "account_register.do")
	public String setting(Model model, AccountVo accountVo, 
			 			  String user_phonenumber1, int user_phonenumber2, int user_phonenumber3, 
			 			  String user_addr1, String user_addr2, String user_addr3, String user_addr4) {
		String user_phonenumber = user_phonenumber1 + "-" + String.valueOf(user_phonenumber2) + "-" + String.valueOf(user_phonenumber3);
		String user_address = user_addr1 + "," + user_addr2 + "," + user_addr3 + "," + user_addr4;
		String user_password = bCryptpasswordEncoder.encode(accountVo.getUser_password());
		
		accountVo.setUser_password(user_password);
		accountVo.setUser_phonenumber(user_phonenumber);
		accountVo.setUser_address(user_address);
		
		model.addAttribute("accountVo", accountVo);
		
		return PREFIX + "account/setting.jsp";
	}
	/// '완료' 버튼 혹은 '건너뛰기' 버튼을 누르면
	@RequestMapping(value = "account_setting.do", method = RequestMethod.POST)
	public String complete(Model model, AccountVo accountVo) throws IOException {
		String Storage_Path = "/resources/images";
		
		if (accountVo.getUser_nickname()==null) {
			accountVo.setUser_nickname(accountVo.getUser_name());
		}
		if (accountVo.getImage()==null) {
			accountVo.setUser_imagename("/defaultProfileImage");
		} else {
			Storage_Path += "/" + accountVo.getUser_id();
			String Real_Path = application.getRealPath(Storage_Path);
			System.out.println(Real_Path);
			
			File dir = new File(Real_Path);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			MultipartFile file = accountVo.getImage();  // 이미지 파라미터
			int dot = file.getName().lastIndexOf(".");
			String suffix = file.getName().substring(dot+1); // .png   .jpg   .jpeg
			
			System.out.println("getName() : " + file.getName());
			System.out.println("getOriginalFileName() : " + file.getOriginalFilename());
			
			String fileName = accountVo.getUser_id() + suffix;
			File newFile = new File(Real_Path, fileName); // 유저 id로 빈 파일 생성 (newFile)  ( ==> RealPath/admin
			
			if (newFile.exists()) { // 동명의 파일이 있으면
				FileRenamePolicy fileRename = new DefaultFileRenamePolicy();
				fileName = fileRename.rename(newFile).getName();
				newFile = new File(Real_Path, fileName); // newFile의 이름 변경 ( ==> RealPath/admin1
			}
			
			accountVo.setUser_imagename("/" + fileName);  // /admin1
			newFile.createNewFile();  // 파일 만들기
			
			file.transferTo(newFile);	// newFile에 바이너리 데이터 복사
		}
		
		accountVo.setUser_imagepath(Storage_Path);
		
		if (accountDao.insert(accountVo)==AccountDao.SUCCESS) {
			message = "회원가입이 완료되었습니다!";
			
			session.removeAttribute("authkey");
			session.removeAttribute("authstatus");
			session.invalidate();
			
			model.addAttribute("message", message);
			return PREFIX + "account/complete.jsp";
		} else {
			message = "회원가입에 문제가 발생하였습니다. 다시 시도해 주세요.";
			model.addAttribute("message", message);
			return "account/register.jsp";
		}
	}
	/// 아이디 중복확인
	@RequestMapping(value = "account_overlapCheck.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String account_overlapCheck(@RequestParam(value = "user_id")String user_id) {
		boolean result = accountDao.overlapCheck(user_id);
		
		if (result) {
			message = "사용할 수 없거나 이미 사용중인 아이디입니다.";
		} else {
			message = "사용 가능한 아이디입니다.";
		}
		
		return message;
	}
	
	/** 내 정보 Controller **/
	@RequestMapping(value = "mypage.do")
	public String mypage(Model model) {
		// 로그인 user_id 받아옴
		String userId = (String) session.getAttribute("user_id");

		// 만약 로그인되지 않았다면
		if (userId == null) {
			return "login.page";
		}
		// 로그인 되었다면
		else if (userId != null) {
			AccountVo accountVo = accountDao.mypage(userId);

			// 성별 판정
			message = (accountVo.getUser_gender() % 2 == 1) ? "남성" : "여성";
			
			model.addAttribute("gender", message);
			model.addAttribute("my_info", accountVo);
		}

		// 내정보로 넘어감
		return PREFIX + "mypage/mypage.jsp";
	}
	// 내 정보 수정
	@RequestMapping(value = "modify_information.do")
	public String Mypage_modify(Model model, AccountVo accountvo) {		
		model.addAttribute("my_info", accountvo);

		return PREFIX + "mypage/modify_information.jsp";
	}
	// 비밀번호 변경
	@RequestMapping(value = "modify_password.do")
	public String modify_password(Model model, AccountVo accountVo) {
		model.addAttribute("my_info", accountVo);
		
		return PREFIX + "mypage/modify_password.jsp";
	}
	// 이메일 변경
	@RequestMapping(value = "modify_email.do")
	public String modify_email(Model model, AccountVo accountVo) {
		model.addAttribute("my_info", accountVo);
		
		return PREFIX + "mypage/modify_email.jsp";
	}
	// 내 정보 수정 완료
	@RequestMapping(value = "my_information_edit_success.do")
	public String my_information_edit_success(Model model, AccountVo accountVo, String user_phonenumber1,
			int user_phonenumber2, int user_phonenumber3) {

		// 전화번호 1,2,3 붙임
		String user_phonenumber = user_phonenumber1 + String.valueOf(user_phonenumber2) + String.valueOf(user_phonenumber3);
		accountVo.setUser_phonenumber(user_phonenumber);
		
		accountVo.setUser_id((String)session.getAttribute("user_id"));

		// sql문 타고 update됨
		accountDao.my_information_edit(accountVo);

		// 완료되었다면 다시 mypage로 리턴
		return "mypage.do";
	}
	
	/** 비밀번호 수정 완료되었을 때*/
	@RequestMapping(value="my_password_success.do")
	public String my_password_success(AccountVo accountVo) {
		accountVo.setUser_id((String)session.getAttribute("user_id"));
		accountDao.my_password_edit(accountVo);
		return "mypage.do";
	}
	
	/** 이메일 수정완료되었을때 */ 
	@RequestMapping(value="my_email_edit_success.do")
	public String my_email_edit_success(Model model, AccountVo accountVo) {
		
		accountVo.setUser_id((String)session.getAttribute("user_id"));
		// 이메일 변경했을 시 인증메일 전송
		String dbId = (String) session.getAttribute("user_id");
		if (!accountDao.selectOne_email(dbId).equals(accountVo.getUser_email())) {

			model.addAttribute("user_email", accountVo.getUser_email());
			model.addAttribute("authstatus", EMAIL_MODIFY); // 이메일 인증 상태(정보 수정을 통한 이메일 변경 시)

			return PREFIX + "email_confirm.jsp";
		}

		accountDao.my_email_edit(accountVo);
		
		return "mypage.do";
	}
	// 회원탈퇴 : signout
	@RequestMapping(value="user_signout.do") 
	public String user_signout(Model model, String user_id) {
		accountDao.user_signout(user_id);	
		return "account_logout.do";
	}
}