package du.board.dao;

import java.util.List;

import du.board.domain.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardList();

}
