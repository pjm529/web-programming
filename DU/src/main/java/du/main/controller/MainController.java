package du.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String mainPageByPost(HttpServletRequest request, @ModelAttribute UserVO user) throws Exception {

		if (userService.loginProcess(request, user)) {
			return "redirect:/mainPage.do";
		} else {

			return "redirect:/loginPage.do";
		}

	}

	@RequestMapping("/loginPage.do")
	public String loginPage() {
		return "login.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		session.removeAttribute("USER");
		
		return "redirect:/loginPage.do";
	}
	
	@RequestMapping("/mainPage.do")
	public String mainPage(HttpSession session) {
		
		if(session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}
		
		return "main.jsp";
	}
}
