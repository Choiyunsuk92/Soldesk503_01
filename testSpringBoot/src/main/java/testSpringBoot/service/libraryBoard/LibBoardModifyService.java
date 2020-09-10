package testSpringBoot.service.libraryBoard;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import testSpringBoot.FileName;
import testSpringBoot.command.AuthInfo;
import testSpringBoot.command.LibraryBoardCommand;
import testSpringBoot.domain.LibraryBoardDTO;
import testSpringBoot.domain.StartEndPageDTO;
import testSpringBoot.mapper.LibraryBoardMapper;

@Component
@Service
@Transactional
public class LibBoardModifyService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String libBoardModify(LibraryBoardCommand libraryBoardCommand,	
			HttpSession session, Model model)  throws Exception{
		LibraryBoardDTO dto = new LibraryBoardDTO();
		dto.setBoardContent(libraryBoardCommand.getBoardContent());
		dto.setBoardNum(Integer.parseInt(libraryBoardCommand.getBoardNum()));
		dto.setBoardSubject(libraryBoardCommand.getBoardSubject());
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		dto.setUserId(userId);
		StartEndPageDTO startEndPageDTO = 
				new StartEndPageDTO(1L, 1L, dto.getUserId(), dto.getBoardNum().toString() );
		LibraryBoardDTO lib = // 자료실 정보 가져오기 
				libraryBoardMapper.getLibraryBoardList(startEndPageDTO).get(0);
		List<FileName> list = 
				(List<FileName>)session.getAttribute("fileList");
		if(list != null) {
			for(FileName fi : list) {
				lib.setOriginalFileName(lib.getOriginalFileName()
						               .replace(fi.getOriginalfileName()+"`", ""));
				lib.setStoreFileName(lib.getStoreFileName()
								        .replace(fi.getStoreFileName()+"`", ""));
				lib.setFileSize(
						lib.getFileSize()
						   .replace(fi.getFileSize()+"`", ""));
			}
		}
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal ="";
		File file = null;
		String path1 = "/static/lib_Board/upload";
		String filePath = 
				session.getServletContext().getRealPath(path1);
		// filePath =  "c:/Users/soongmoorhee/Downloads/eclipse-workspace2/Soldesk503_01  ;         
		// filePath += "/testSpringBoot/src/main/resources/templates/thymeleaf";
		// filePath += "/lib_Board/upload";
		
		// insert할 때와 로직이 같다. 
		// libraryBoardCommand에 있는 report로 파일정보를 가져오기   
		for(MultipartFile mf : libraryBoardCommand.getReport()) {
			String original = mf.getOriginalFilename();
			String originalFileExtension = 
					original.substring(original.lastIndexOf(".")); // lee.txt ==> .txt
			String store = UUID.randomUUID().toString().replace("-", "") 
					+ originalFileExtension;
			String fileSize = Long.toString(mf.getSize()); // Long ===> String
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			file = new File(filePath +"/" + store);
			try {// 파일저장 
				mf.transferTo(file);
			}catch(Exception e) {e.printStackTrace();}
		}
		dto.setOriginalFileName(
				originalTotal+lib.getOriginalFileName());
		dto.setStoreFileName(
				storeTotal+lib.getStoreFileName());
		dto.setFileSize(fileSizeTotal+lib.getFileSize());
		// 수정을 하기 위해서는  commend 에 있는 비밀번호와 디비에 저장된 비밀번호가 같아야 한다.
		if(passwordEncoder.matches(libraryBoardCommand.getBoardPass(), 
				lib.getBoardPass())) {
			// 같으면 update  를 한다 .
			libraryBoardMapper.libraryUpdate(dto);
			// 그리고 session에 있는  파일 정보를 삭제 하자.
			if(list != null) {
				for(FileName fi : list ) {
					file = new File(filePath + "/" 
									+ fi.getStoreFileName().replace("`", ""));
					if(file.exists()) file.delete();
				}
				// session 삭제 
				session.removeAttribute("fileList");
			}
			// 디테일 페이지로 이동 
			return "redirect:/libraryBoard/libBoardDetail/"+lib.getBoardNum();
		}
		// 비밀번호가 일치하지 않으면 수정 페이지로 이동 
		return "thymeleaf/lib_Board/lib_board_modify";
	}
}











