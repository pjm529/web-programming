package du.reply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.reply.dao.ReplyDAO;
import du.reply.domain.ReplyVO;
import du.reply.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void insertReply(ReplyVO reply) {
		replyDAO.insertReply(reply);
	}

	@Override
	public List<ReplyVO> selectReplyList(long boardIdx) {
		return replyDAO.selectReplyList(boardIdx);
	}

	@Override
	public void deleteReply(long idx) {
		replyDAO.deleteReply(idx);
	}

}
