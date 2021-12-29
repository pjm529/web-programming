package du.board.service.impl;

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
	public List<BoardVO> selectBoardList(Pagination pagination) {
		return boardDAO.selectBoardList(pagination);
	}

	@Override
	public int selectBoardListCnt() {
		return boardDAO.selectBoardListCnt();
	}

}
