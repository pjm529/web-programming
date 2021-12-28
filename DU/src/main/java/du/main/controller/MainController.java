package du.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import du.user.domain.UserVO;
import du.user.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String mainPageByPost(@ModelAttribute UserVO user) throws Exception {

		if (userService.selectPwd(user.getUserId(), user.getPwd())) {
			return "main.jsp";
		} else {

			return "login.jsp";
		}

	}

	@RequestMapping(value = "/loginPage.do", method = RequestMethod.GET)
	public String loginPage() {
		return "login.jsp";
	}
}
