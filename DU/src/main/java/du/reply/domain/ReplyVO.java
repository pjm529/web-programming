package du.reply.domain;

import java.time.LocalDateTime;

public class ReplyVO {

	private long idx;
	
	private long boardIdx;
	
	private String content;
	
	private String writerId;
	
	private String writerName;
	
	private LocalDateTime registDate;
	
	private LocalDateTime modifyDate;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(long boardIdx) {
		this.boardIdx = boardIdx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public LocalDateTime getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
