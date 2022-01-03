package du.reply.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
