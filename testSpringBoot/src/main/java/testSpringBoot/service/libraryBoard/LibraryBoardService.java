package testSpringBoot.service.libraryBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.LibraryBoardCommand;
import testSpringBoot.domain.LibraryBoardDTO;

@Component
@Service
public class LibraryBoardService {
	public void writePro(LibraryBoardCommand libraryBoardCommand, HttpServletRequest request) {
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
		String location ="";
		String PATH = "/static/lib_Board/upload";
		String filePath = request.getServletContext().getRealPath(PATH);
		System.out.println("LibraryBoardService : " + filePath);
	}
}
