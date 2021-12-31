package du.board.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import du.board.dao.BoardDAO;
import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class BoardDAOImpl extends EgovAbstractMapper implements BoardDAO {

	@Override
	public List<BoardVO> selectBoardList(HashMap<String, Object> map) {
		return selectList("BoardDAO.selectBoardList", map);
	}

	@Override
	public int selectBoardListCnt(String title) {
		return selectOne("BoardDAO.selectBoardListCnt", title);
	}

	@Override
	public BoardVO selectBoard(long idx) {
		return selectOne("BoardDAO.selectBoard", idx);
	}

	@Override
	public BoardAttFileVO selectBoardAttFile(BoardAttFileVO criteria) {
		return selectOne("BoardDAO.selectBoardAttFile", criteria);
	}

	@Override
	public void insertBoard(BoardVO board) {
		insert("BoardDAO.insertBoard", board);
	}

	@Override
	public void insertBoardAttFile(BoardAttFileVO attFileVO) {
		insert("BoardDAO.insertBoardAttFile", attFileVO);
	}

	@Override
	public void deleteBoard(long idx) {
		delete("BoardDAO.deleteBoard", idx);
	}

	@Override
	public void updateBoard(BoardVO board) {
		update("BoardDAO.updateBoard", board);
	}

}
