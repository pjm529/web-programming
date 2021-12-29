package du.board.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import du.board.dao.BoardDAO;
import du.board.domain.BoardVO;
import du.common.Pagination;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class BoardDAOImpl extends EgovAbstractMapper implements BoardDAO {

	@Override
	public List<BoardVO> selectBoardList(Pagination pagination) {
		return selectList("BoardDAO.selectBoardList", pagination);
	}

	@Override
	public int selectBoardListCnt() {
		return selectOne("BoardDAO.selectBoardListCnt");
	}

}
