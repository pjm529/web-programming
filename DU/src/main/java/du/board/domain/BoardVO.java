package du.board.domain;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

	private long idx;

	private String title;

	private String content;

	private String writerId;

	private String writerName;

	private LocalDateTime registDate;

	private LocalDateTime modifyDate;

	private MultipartFile attFile;

	private long attIdx;

	private String attFilename;

	private String handleType;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public MultipartFile getAttFile() {
		return attFile;
	}

	public void setAttFile(MultipartFile attFile) {
		this.attFile = attFile;
	}

	public long getAttIdx() {
		return attIdx;
	}

	public void setAttIdx(long attIdx) {
		this.attIdx = attIdx;
	}

	public String getAttFilename() {
		return attFilename;
	}

	public void setAttFilename(String attFilename) {
		this.attFilename = attFilename;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public boolean isExistAttFile() {
		return attFile != null && attFile.getSize() > 0;
	}

	public boolean hasAttFile() {
		return attIdx > 0;

	}

	public BoardAttFileVO getCriteria() {
		return new BoardAttFileVO(attIdx, idx);
	}
}
