package du.board.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class BoardAttFileVO {
	private long idx;

	private long boardIdx;

	private String filePath;

	private String oldFilename;

	private String newFilename;

	private long fileSize;

	private String writerId;

	private LocalDateTime registDate;

	private MultipartFile attFile;

	public BoardAttFileVO() {
	}

	public BoardAttFileVO(long idx, long boardIdx) {
		this.idx = idx;
		this.boardIdx = boardIdx;
	}

	public BoardAttFileVO(BoardVO vo) {
		this.boardIdx = vo.getIdx();
		this.writerId = vo.getWriterId();
		this.attFile = vo.getAttFile();
	}

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOldFilename() {
		return oldFilename;
	}

	public void setOldFilename(String oldFilename) {
		this.oldFilename = oldFilename;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public LocalDateTime getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}

	public MultipartFile getAttFile() {
		return attFile;
	}

	public void setAttFile(MultipartFile attFile) {
		this.attFile = attFile;
	}

	public String getFullAttFilePath() throws FileNotFoundException {

		if (idx > 0 && boardIdx > 0) {
			return filePath + File.separator + newFilename;
		}
		throw new FileNotFoundException();
	}

}
