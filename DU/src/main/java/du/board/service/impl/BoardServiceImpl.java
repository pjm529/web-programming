package du.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.board.dao.BoardDAO;
import du.board.domain.BoardVO;
import du.board.service.BoardService;
import du.common.Pagination;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardVO> selectBoardList(Pagination pagination, String title) {

		HashMap<String, Object> map = new HashMap<>();

		map.put("startList", pagination.getStartList());
		map.put("listSize", pagination.getListSize());
		map.put("title", title);

		return boardDAO.selectBoardList(map);
	}

	@Override
	public int selectBoardListCnt(String title) {
		return boardDAO.selectBoardListCnt(title);
	}

	@Override
	public void insertBoard(BoardVO board) {
		boardDAO.insertBoard(board);
	}

}
