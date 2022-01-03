package du.reply.service;

import java.util.List;

import du.reply.domain.ReplyVO;

public interface ReplyService {
	
	public void insertReply(ReplyVO reply);

	public List<ReplyVO> selectReplyList(long boardIdx);
	
	public ReplyVO selectReply(long idx);
	
	public void deleteReply(long idx);
	
	public void updateReply(ReplyVO reply);
}
