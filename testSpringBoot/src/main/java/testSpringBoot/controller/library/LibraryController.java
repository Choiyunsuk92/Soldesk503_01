package testSpringBoot.controller.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import testSpringBoot.command.LibraryBoardCommand;
import testSpringBoot.controller.FileDownLoad;
import testSpringBoot.domain.FileName;
import testSpringBoot.service.libraryBoard.FileDelService;
import testSpringBoot.service.libraryBoard.LibraryBoardDetailService;
import testSpringBoot.service.libraryBoard.LibraryBoardListService;
import testSpringBoot.service.libraryBoard.LibraryBoardService;

@Controller
@RequestMapping("libraryBoard")
public class LibraryController {
	@Autowired
	LibraryBoardService libraryBoardService;
	@Autowired
	LibraryBoardListService libraryBoardListService;
	@Autowired
	LibraryBoardDetailService libraryBoardDetailService;
	
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
	@RequestMapping("libBoardDetail/{id}")
	public String libBoardDetail(@PathVariable(value = "id") String boardNum,
			Model model, HttpSession session)  throws Exception{
		libraryBoardDetailService.libBoardDetail(boardNum, session, model);
		return "thymeleaf/lib_Board/lib_board_view";
	}
	@Autowired
	FileDownLoad fileDownLoad;
	@RequestMapping("fileDown")
	public void filDownLoad(@RequestParam(value="file")String fileName,
			HttpServletResponse response, HttpServletRequest request) throws Exception{
		String path = "/static/lib_Board/upload";
		fileDownLoad.fileDownLoad(path,fileName,request,response);
	}
	@RequestMapping("libBoardModify")
	public String libBoardModify(Model model, HttpSession session,
			@RequestParam(value = "boardNum" ) String boardNum) throws Exception{
		libraryBoardDetailService.libBoardDetail(boardNum, session, model);
		return "thymeleaf/lib_Board/lib_board_modify";
	}
	@Autowired
	FileDelService fileDelService; 
	@RequestMapping("fileDel")
	public String fileDel(FileName fileName,HttpSession session,Model model) {
		fileDelService.fileSessionAdd(fileName, session, model);
		return "thymeleaf/lib_Board/delPage";
	}
	
}
