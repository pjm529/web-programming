package du.reply.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import du.reply.dao.ReplyDAO;
import du.reply.domain.ReplyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class ReplyDAOImpl extends EgovAbstractMapper implements ReplyDAO {

	@Override
	public void insertReply(ReplyVO reply) {
		insert("ReplyDAO.insertReply", reply);
	}

	@Override
	public List<ReplyVO> selectReplyList(long boardIdx) {
		return selectList("ReplyDAO.selectReplyList", boardIdx);
	}

	@Override
	public ReplyVO selectReply(long idx) {
		return selectOne("ReplyDAO.selectReply", idx);
	}

	@Override
	public void deleteReply(long idx) {
		delete("ReplyDAO.deleteReply", idx);
	}

}
