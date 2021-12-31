package du.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardVO;
import du.board.service.BoardService;
import du.common.DownloadView;
import du.common.Pagination;
import du.user.domain.UserVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/boardListPage.do")
	public ModelAndView boardListPage(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "3") int listSize,
			@RequestParam(required = false, defaultValue = "") String title, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			ModelAndView mav = new ModelAndView("login.jsp");
			return mav;
		}

		ModelAndView mav = new ModelAndView("board/boardList.jsp");

		int listCnt = boardService.selectBoardListCnt(title);
		Pagination pagination = new Pagination();
		pagination.setListSize(listSize);
		pagination.pageInfo(page, range, listCnt);
		mav.addObject("pagination", pagination);

		List<BoardVO> boardList = boardService.selectBoardList(pagination, title);
		mav.addObject("boardList", boardList);

		mav.addObject("title", title);

		return mav;

	}

	@RequestMapping("/boardWritePage.do")
	public String boardWritePage(HttpSession session) {

		if (session.getAttribute("USER") == null) {
			return "redirect:/loginPage.do";
		}

		return "board/boardWrite.jsp";
	}

	@RequestMapping("/boardWrite.do")
	public String boardWrite(@ModelAttribute BoardVO board, HttpSession session) throws Exception {

		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/loginPage.do";
		}

		board.setWriterId(user.getUserId());
		boardService.insertBoard(board);

		return "redirect:/boardListPage.do";
	}

	@RequestMapping("/boardInfoPage/{idx}.do")
	public ModelAndView boardInfoPage(@PathVariable("idx") long idx, HttpSession session) {

		if (session.getAttribute("USER") == null) {
			ModelAndView mav = new ModelAndView("login.jsp");
			return mav;
		}

		ModelAndView mav = new ModelAndView("board/boardInfo.jsp");

		BoardVO board = boardService.selectBoard(idx);

		mav.addObject("board", board);

		return mav;

	}

	@RequestMapping("/boardDelete.do")
	public String boardDelete(BoardVO board, HttpSession session, HttpServletResponse response) throws Exception {

		BoardVO board2 = boardService.selectBoard(board.getIdx());
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/loginPage.do";
		} else if (board2.getWriterId().equals(user.getUserId())) {

			boardService.deleteBoard(board);

			return "redirect:/boardListPage.do";

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

	@RequestMapping("/boardModifyPage.do")
	public ModelAndView boardModifyPage(long idx, HttpSession session, HttpServletResponse response) {

		BoardVO board = boardService.selectBoard(idx);
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {

			ModelAndView mav = new ModelAndView("login.jsp");
			return mav;

		} else if (board.getWriterId().equals(user.getUserId())) {

			ModelAndView mav = new ModelAndView("board/boardModify.jsp");
			mav.addObject("board", board);

			return mav;
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

	@RequestMapping("/boardModify.do")
	public String boardModify(@ModelAttribute BoardVO board, HttpSession session, HttpServletResponse response) {

		UserVO user = (UserVO) session.getAttribute("USER");
		BoardVO board2 = boardService.selectBoard(board.getIdx());

		if (user == null) {
			return "redirect:/loginPage.do";
		} else if (board2.getWriterId().equals(user.getUserId())) {

			boardService.updateBoard(board);
			return "redirect:/boardInfoPage/" + Long.toBinaryString(board.getIdx()) + ".do";

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

	@PostMapping("/download/boardAttFile.do")
	public View downloadBoardAttFile(BoardAttFileVO criteria, Model model) throws Exception {
		BoardAttFileVO attFileVO = boardService.findBoardAttFile(criteria);
		File file = new File(attFileVO.getFullAttFilePath());

		model.addAttribute("downloadFile", file);
		model.addAttribute("downloadFilename", attFileVO.getOldFilename());

		return new DownloadView();
	}
}
