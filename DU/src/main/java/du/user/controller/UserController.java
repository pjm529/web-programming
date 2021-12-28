package du.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import du.user.domain.UserVO;
import du.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/userInfoConfirmPage.do")
	public String userInfoConfirmPage() {
		return "user/userInfoConfirm.jsp";
	}

	@RequestMapping("/userInfoConfirm.do")
	public String userInfoConfirm(@ModelAttribute UserVO user) {

		if (userService.selectPwd(user.getUserId(), user.getPwd())) {
			return "user/userInfo.jsp";
		} else {
			return "main.jsp";
		}

	}

	@RequestMapping("/signUpPage.do")
	public String signUpPage() {
		return "user/signUp.jsp";
	}

	@RequestMapping("/signUp.do")
	public String signUp(@ModelAttribute UserVO user) {

		userService.insertUser(user);

		return "redirect:/loginPage.do";
	}

	@RequestMapping("/userModify.do")
	public String userModify(@ModelAttribute UserVO user) {
		userService.updateUser(user);
		return "redirect:/logout.do";
	}

	@RequestMapping("/userDelete.do")
	public String userDelete(HttpSession session) {
		userService.deleteUser(session);

		return "redirect:/loginPage.do";
	}

}
