package testSpringBoot.service.libraryBoard;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.AuthInfo;
import testSpringBoot.domain.LibraryBoardDTO;
import testSpringBoot.domain.StartEndPageDTO;
import testSpringBoot.mapper.LibraryBoardMapper;

@Component
@Service
public class LibBoardDeleteService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(String boardNum,String boardPass,
			HttpSession session,Model model) throws Exception{
		// 작성한 사용자인지를 확인 하기 위해서 로그인 정보 가져오기 
		String userId = ((AuthInfo)session.getAttribute("authInfo")).getId();
		// 자료실 번호와 사용자 아이를 이용해서 자료실 내용을 디비로 부터 가져옴 
		StartEndPageDTO startEndPageDTO = 
				new StartEndPageDTO(1L,1L,userId,boardNum);
		LibraryBoardDTO dto = libraryBoardMapper.getLibraryBoardList(startEndPageDTO).get(0);
		// 디비로 부터 가져온 자료실 정보에서 사용자가 입력한 비밀번호와 디비로 부터 가져온 비밀번호가 일치한지 확인 
		// 	비밀번호가 암호화가 되어 있으므로 PasswordEncoder를 이용해서 비교 해야 한다. 
		if(passwordEncoder.matches(boardPass, dto.getBoardPass())) {
			// 자료실 삭제하고 리스트페이지로 이동 
			libraryBoardMapper.libraryDelete(boardNum);
			// 디비를 삭제 한 후  파일을 삭제 하자. 
			// 파일 정보는 dto로 이미 디비로 부터 받아 왔다.
			String [] files =  dto.getStoreFileName().split("`");
			String path1 =  "/static/lib_Board/upload";
			String filePath = session.getServletContext().getRealPath(path1);
			File file = null; //  파일을 생성하기 위해 File객체를 생성해야 한다.
			for(String fileName : files) {
				file = new File(filePath + "/" + fileName);
				if(file.exists()) file.delete();
			}
			return "redirect:/libraryBoard/library";
		}
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
		return "thymeleaf/lib_Board/lib_board_delete";
	}
}
