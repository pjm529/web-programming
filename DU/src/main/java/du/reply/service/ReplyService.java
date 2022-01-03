package du.reply.service;

import java.util.List;

import du.reply.domain.ReplyVO;

public interface ReplyService {
	
	public void insertReply(ReplyVO reply);

	public List<ReplyVO> selectReplyList(long boardIdx);
}
