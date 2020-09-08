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
		
		StartEndPageDTO startEndPageDTO = 
				new StartEndPageDTO(startRow, endRow, null, null);
		
		List<LibraryBoardDTO> lists = 
				libraryBoardMapper.getLibraryBoardList(startEndPageDTO);
		// 자료실테이블에 있는 레코드가 몇개인지 가져오
		int count = libraryBoardMapper.getLibraryCount();
		
		model.addAttribute("count", count);
		model.addAttribute("lists", lists);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "library?");
	}
}
