package du.board.service;

import java.util.List;

import du.board.domain.BoardVO;
import du.common.Pagination;

public interface BoardService {

	public List<BoardVO> selectBoardList(Pagination pagination, String title);

	public int selectBoardListCnt(String title);

	public BoardVO selectBoard(long idx);

	public void insertBoard(BoardVO board);

	public void deleteBoard(long idx);

}
