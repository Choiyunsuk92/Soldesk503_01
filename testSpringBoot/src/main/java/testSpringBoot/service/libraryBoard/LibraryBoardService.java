package testSpringBoot.service.libraryBoard;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.LibraryBoardCommand;
import testSpringBoot.domain.LibraryBoardDTO;
import testSpringBoot.mapper.LibraryBoardMapper;

@Component
@Service
public class LibraryBoardService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	public String writePro(LibraryBoardCommand libraryBoardCommand, 
			HttpServletRequest request) throws Exception{
		String location = "";
		LibraryBoardDTO libraryBoardDTO = new LibraryBoardDTO();
		libraryBoardDTO.setBoardContent(libraryBoardCommand.getBoardContent());
		libraryBoardDTO.setBoardName(libraryBoardCommand.getBoardName());
		libraryBoardDTO.setBoardPass(libraryBoardCommand.getBoardPass());
		libraryBoardDTO.setBoardSubject(libraryBoardCommand.getBoardSubject());
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		libraryBoardDTO.setUserId(authInfo.getId());
		libraryBoardDTO.setIpAddr(request.getRemoteAddr());
		// 파일 정보를 입력하기 위한 변수 
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal ="";
		String PATH = "/static/lib_Board/upload";
		String filePath = request.getServletContext().getRealPath(PATH);
		System.out.println("LibraryBoardService : " + filePath);
		//../src/main/webapp/static/lib_Board/upload
		for(MultipartFile mf : libraryBoardCommand.getReport()) {
			String original = mf.getOriginalFilename(); // 전송된 파일명 
			String originalFileExtension = // 전송된 파일명으로 부터 확장자만 자라옴   
					original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "")
					+ originalFileExtension; // 임의의 파일명 + 확장자 
			String fileSize = Long.toString(mf.getSize());
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			// 파일을저장하기 위해서 파일 객체 생성 
			File file = new File(filePath + "/" + store);
			try {
				mf.transferTo(file);
			}catch(Exception e) {
				location = "thymeleaf/lib_Board/lib_board_write";
				e.printStackTrace();
			}
		}
		
		libraryBoardDTO.setOriginalFileName(originalTotal);
		libraryBoardDTO.setStoreFileName(storeTotal);
		libraryBoardDTO.setFileSize(fileSizeTotal);
		libraryBoardMapper.libraryInsert(libraryBoardDTO);
		location = "redirect:/libraryBoard/library";
		return location;
	}
}
