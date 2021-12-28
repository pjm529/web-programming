package du.user.controller;

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

	@RequestMapping("/signUpPage.do")
	public String signUpPage() {
		return "user/signUp.jsp";
	}
	
	@RequestMapping("/signUp.do")
	public String signUp(@ModelAttribute UserVO user) {
		
		userService.insertUser(user);
		
		return "redirect:/loginPage.do";
	}
}
