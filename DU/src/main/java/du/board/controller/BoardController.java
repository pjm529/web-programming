package du.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import du.board.domain.BoardVO;
import du.board.service.BoardService;
import du.common.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/boardListPage.do")
	public ModelAndView boardListPage(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range) {

		ModelAndView mav = new ModelAndView("board/boardList.jsp");
		
		int listCnt = boardService.selectBoardListCnt();
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		mav.addObject("pagination", pagination);
		
		List<BoardVO> boardList = boardService.selectBoardList(pagination);
		mav.addObject("boardList", boardList);

		return mav;
	}

}
