package du.board.service;

import java.util.List;

import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardVO;
import du.common.Pagination;

public interface BoardService {

	public List<BoardVO> selectBoardList(Pagination pagination, String title);

	public int selectBoardListCnt(String title);

	public BoardVO selectBoard(long idx);

	public BoardAttFileVO findBoardAttFile(BoardAttFileVO criteria);

	public void insertBoard(BoardVO board) throws Exception;

	public void deleteBoard(long idx);

	public void updateBoard(BoardVO board);
}
