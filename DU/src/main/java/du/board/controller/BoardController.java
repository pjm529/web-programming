package du.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import du.board.domain.BoardVO;
import du.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/boardListPage.do")
	public ModelAndView boardListPage() {
		
		ModelAndView mav = new ModelAndView("board/boardList.jsp");
		List<BoardVO> boardList = boardService.selectBoardList();
		mav.addObject("boardList", boardList);
		
		return mav;
	}

}
