package du.dept.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import du.dept.domain.DeptVO;
import du.dept.service.DeptService;

@Controller
public class DeptController {

	@Autowired
	private DeptService deptService;

	@RequestMapping("/deptPage.do")
	public ModelAndView deptPage(HttpSession session) {

		if (session.getAttribute("USER") == null) {
			ModelAndView mav = new ModelAndView("login.jsp");
			return mav;
		}

		ModelAndView mav = new ModelAndView("dept/deptList.jsp");

		List<DeptVO> dept = deptService.selectDeptList();
		mav.addObject("dept", dept);
		return mav;
	}

	@RequestMapping("/deptInsertPage.do")
	public String deptInserPage(HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}

		return "dept/deptInsert.jsp";
	}

	@RequestMapping("/deptInsert.do")
	public String deptInsert(DeptVO dept, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}

		deptService.insertDept(dept);
		return "redirect:/deptPage.do";
	}

	@RequestMapping("/deptDelete.do")
	public String deptDelete(String deptCd, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}

		deptService.deleteDept(deptCd);
		return "redirect:/deptPage.do";
	}
}
