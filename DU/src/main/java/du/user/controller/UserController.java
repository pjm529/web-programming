package du.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import du.dept.domain.DeptVO;
import du.dept.service.DeptService;
import du.user.domain.UserVO;
import du.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DeptService deptService;

	@RequestMapping("/userInfoConfirmPage.do")
	public String userInfoConfirmPage(HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}
		
		return "user/userInfoConfirm.jsp";
	}

	@RequestMapping("/userInfoConfirm.do")
	public ModelAndView userInfoConfirm(@ModelAttribute UserVO user, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			ModelAndView mav = new ModelAndView("login.jsp");
			return mav;
		}

		if (userService.selectPwd(user.getUserId(), user.getPwd())) {
			ModelAndView mav = new ModelAndView("user/userInfo.jsp");
			List<DeptVO> dept = deptService.selectDeptList();
			mav.addObject("dept", dept);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("main.jsp");
			return mav;
		}

	}

	@RequestMapping("/signUpPage.do")
	public ModelAndView signUpPage() {

		ModelAndView mav = new ModelAndView("user/signUp.jsp");
		List<DeptVO> dept = deptService.selectDeptList();
		mav.addObject("dept", dept);
		return mav;
	}

	@RequestMapping("/signUp.do")
	public String signUp(@ModelAttribute UserVO user) {

		userService.insertUser(user);

		return "redirect:/loginPage.do";
	}

	@RequestMapping("/userModify.do")
	public String userModify(@ModelAttribute UserVO user, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}

		userService.updateUser(user);
		return "redirect:/logout.do";
	}

	@RequestMapping("/userDelete.do")
	public String userDelete(HttpSession session) {

		if (session.getAttribute("USER") != null) {
			userService.deleteUser(session);
		}

		return "redirect:/loginPage.do";
	}

}
