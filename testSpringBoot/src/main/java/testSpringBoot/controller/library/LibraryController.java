package testSpringBoot.controller.library;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.LibraryBoardCommand;
import testSpringBoot.service.libraryBoard.LibraryBoardListService;
import testSpringBoot.service.libraryBoard.LibraryBoardService;

@Controller
@RequestMapping("libraryBoard")
public class LibraryController {
	@Autowired
	LibraryBoardService libraryBoardService;
	@Autowired
	LibraryBoardListService libraryBoardListService;
	
	// command객체가 필요한 곳에 model로 전달 
	@ModelAttribute
	LibraryBoardCommand setLibraryBoardCommand() {
		return new LibraryBoardCommand();
	}
	// model.addAttribute("libraryBoardCommand", new LibraryBoardCommand())
	// @ModelAttribute("libraryBoardCommand" )LibraryBoardCommand libraryBoardCommand
	@RequestMapping("library")
	public String libraryList(
			@RequestParam(value = "page" , defaultValue = "1") Integer page,
			Model model) throws Exception{
		libraryBoardListService.libraryBoardList(model, page);
		return "lib_Board/lib_board_list";
	}
	
	@RequestMapping(value="libBoardForm" , method = RequestMethod.GET)
	public String libraryWrite(/*LibraryBoardCommand libraryBoardCommand*/) {
		return "thymeleaf/lib_Board/lib_board_write";
	}
	@RequestMapping(value="libBoardForm" , method = RequestMethod.POST)
	public String libraryWritePro(@Validated LibraryBoardCommand libraryBoardCommand,
			BindingResult result, HttpServletRequest request) throws Exception{
		if (result.hasErrors()) {
			System.out.println("bsaicf");
 			 return "thymeleaf/lib_Board/lib_board_write";
	    }
		String location = libraryBoardService.writePro(libraryBoardCommand,request);
		//write페이지가 실행된 후에는 리스트 페이지로 가야 한다.
		return location;
	}
}
