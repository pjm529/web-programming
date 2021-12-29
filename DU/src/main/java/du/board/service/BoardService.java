package du.board.service;

import java.util.List;

import du.board.domain.BoardVO;

public interface BoardService {
	
	public List<BoardVO> selectBoardList();

}
