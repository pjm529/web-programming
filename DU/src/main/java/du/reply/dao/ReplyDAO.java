package du.reply.dao;

import java.util.List;

import du.reply.domain.ReplyVO;

public interface ReplyDAO {

	public void insertReply(ReplyVO reply);
	
	public List<ReplyVO> selectReplyList(long boardIdx);
	
	public void deleteReply(long idx);
}
