package du.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import du.reply.domain.ReplyVO;
import du.reply.service.ReplyService;
import du.user.domain.UserVO;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@RequestMapping("/replyWrite.do")
	public String replyWrtier(HttpSession session, @ModelAttribute ReplyVO reply) {
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/loginPage.do";
		}
		reply.setWriterId(user.getUserId());

		replyService.insertReply(reply);

		return String.format("redirect:/boardInfoPage/%d.do", reply.getBoardIdx());

	}

	@RequestMapping("/replyDelete.do")
	public String replyDelete(HttpSession session, HttpServletResponse response, @RequestParam long idx,
			@RequestParam String boardIdx) {

		ReplyVO reply = replyService.selectReply(idx);
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/loginPage.do";
		} else if (reply.getWriterId().equals(user.getUserId())) {

			replyService.deleteReply(idx);

			return "redirect:/boardInfoPage/" + boardIdx + ".do";

		} else {
			response.setContentType("text/html; charset=UTF-8");

			try {

				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한이 없습니다.'); history.back();</script>");
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

	}

	@RequestMapping("/replyModify.do")
	public String replyModify(HttpSession session, HttpServletResponse response, @ModelAttribute ReplyVO reply) {

		ReplyVO reply2 = replyService.selectReply(reply.getIdx());
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/loginPage.do";
		} else if (reply2.getWriterId().equals(user.getUserId())) {

			replyService.updateReply(reply);

			return String.format("redirect:/boardInfoPage/%d.do", reply.getBoardIdx());

		} else {
			response.setContentType("text/html; charset=UTF-8");

			try {

				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한이 없습니다.'); history.back();</script>");
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

	}
}
