package du.board.dao;

import java.util.HashMap;
import java.util.List;

import du.board.domain.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardList(HashMap<String, Object> map);
	
	public int selectBoardListCnt(String title);
	
	public BoardVO selectBoard(long idx);
	
	public void insertBoard(BoardVO board);
	
	public void deleteBoard(long idx);
	
	public void updateBoard(BoardVO board);

}
