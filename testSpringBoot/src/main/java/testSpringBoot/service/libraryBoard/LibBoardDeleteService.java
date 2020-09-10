package testSpringBoot.service.libraryBoard;

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
		StartEndPageDTO startEndPageDTO = 
				new StartEndPageDTO(1L,1L,userId,boardNum);
		LibraryBoardDTO dto = libraryBoardMapper.getLibraryBoardList(startEndPageDTO).get(0);
		return null;
	}
}
