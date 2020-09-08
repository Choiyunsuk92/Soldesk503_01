package testSpringBoot.service.libraryBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.controller.PageAction;
import testSpringBoot.domain.LibraryBoardDTO;
import testSpringBoot.domain.StartEndPageDTO;
import testSpringBoot.mapper.LibraryBoardMapper;
@Component
@Service
public class LibraryBoardListService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	public void libraryBoardList(Model model, Integer page) 
			throws Exception{
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
			
		/* 예전 방식 
		 LibraryBoardDTO dto = new LibraryBoardDTO();
		 dto.setStartEndPageDTO(new StartEndPageDTO(startRow, endRow));
		*/
		
		StartEndPageDTO dto = 
				new StartEndPageDTO(startRow, endRow, null, null);
		
		// startRow와 endRow만큼 레코드를 가져오 
		List<LibraryBoardDTO> lists = 
				libraryBoardMapper.getLibraryBoardList(dto);
		// 자료실테이블에 있는 레코드가 몇개인지 가져오
		int count = libraryBoardMapper.getLibraryCount();
	    
		/* pagging 공통부분 */
		model.addAttribute("count", count);
		model.addAttribute("lists", lists);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "library?");
	}
}
