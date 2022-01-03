package du.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardVO;
import du.common.Pagination;

public interface BoardService {

	public List<BoardVO> selectBoardList(Pagination pagination, String title);

	public int selectBoardListCnt(String title);

	public BoardVO selectBoard(long idx);

	public BoardAttFileVO findBoardAttFile(BoardAttFileVO criteria);

	public void insertBoard(BoardVO board) throws Exception;

	public void deleteBoard(BoardVO board) throws Exception;

	public void deleteBoardAttFile(BoardAttFileVO criteria) throws Exception;

	public void updateBoard(BoardVO board, HttpSession session) throws Exception;
}
