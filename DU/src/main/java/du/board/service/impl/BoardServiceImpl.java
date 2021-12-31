package du.board.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;

import du.board.dao.BoardDAO;
import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardVO;
import du.board.service.BoardService;
import du.common.Pagination;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private EgovPropertyService propertyService;

	@Override
	public List<BoardVO> selectBoardList(Pagination pagination, String title) {

		HashMap<String, Object> map = new HashMap<>();

		map.put("startList", pagination.getStartList());
		map.put("listSize", pagination.getListSize());
		map.put("title", title);

		return boardDAO.selectBoardList(map);
	}

	@Override
	public int selectBoardListCnt(String title) {
		return boardDAO.selectBoardListCnt(title);
	}

	@Override
	public BoardVO selectBoard(long idx) {
		return boardDAO.selectBoard(idx);
	}

	@Override
	public BoardAttFileVO findBoardAttFile(BoardAttFileVO criteria) {
		return boardDAO.selectBoardAttFile(criteria);
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		boardDAO.insertBoard(board);

		insertBoardAttFile(board);
	}

	@Override
	public void deleteBoard(BoardVO board) throws Exception {
		if (board.hasAttFile()) {
			deleteBoardAttFile(board.getCriteria());
		}
		boardDAO.deleteBoard(board.getIdx());
	}

	@Override
	public void deleteBoardAttFile(BoardAttFileVO criteria) throws Exception {
		BoardAttFileVO attFileVO = boardDAO.selectBoardAttFile(criteria);

		String fullAttFilePath = attFileVO.getFullAttFilePath();

		File file = new File(fullAttFilePath);

		if (file.exists() && !file.isDirectory()) {
			file.delete();
		}

		boardDAO.deleteBoardAttFile(criteria);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
	}

	private void insertBoardAttFile(BoardVO boardVO) throws Exception {
		if (!boardVO.isExistAttFile()) {
			return;// 첨부파일 값이 파라미터에 존재하는지 체크
		}

		BoardAttFileVO attFileVO = new BoardAttFileVO(boardVO);
		try {
			uploadBoardAttFileVO(attFileVO);
		} catch (Exception e) {
			new RuntimeException();
		}
		// 파일 업로드가 성공적이면 관련 데이터를 DB에 저장
		boardDAO.insertBoardAttFile(attFileVO);
	}

	private void uploadBoardAttFileVO(BoardAttFileVO attFileVO) throws Exception {

		// 1. filePath
		String fileStorePath = propertyService.getString("fileStorePath"); // context-properties.xml 에 저장한 fileStorePath
		String dailyPath = LocalDate.now().toString(); // 오늘 날짜
		String filePath = fileStorePath + dailyPath;

		File directory = new File(filePath);

		// 폴더가 존재하지 않으면 폴더 생성
		if (!directory.exists()) {
			directory.mkdir();
		}
		attFileVO.setFilePath(filePath);

		// 2. oldFilename
		MultipartFile multipartFile = attFileVO.getAttFile();
		String originalFilename = multipartFile.getOriginalFilename();
		attFileVO.setOldFilename(originalFilename);

		// 3. newFilename add fileSize
		int pos = originalFilename.lastIndexOf("."); // 확장자 추출
		String ext = originalFilename.substring(pos);
		String newFilenameBody = Generators.timeBasedGenerator().generate().toString(); // UUID 생성
		String newFilename = newFilenameBody + ext;
		attFileVO.setNewFilename(newFilename);
		attFileVO.setFileSize(multipartFile.getSize());

		// 4. real file copy
		File newFile = new File(filePath + File.separator + newFilename);
		multipartFile.transferTo(newFile);
	}

}
