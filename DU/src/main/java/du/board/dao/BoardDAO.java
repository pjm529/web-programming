package du.board.dao;

import java.util.List;

import du.board.domain.BoardVO;
import du.common.Pagination;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardList(Pagination pagination);
	
	public int selectBoardListCnt();

}
