package testSpringBoot.controller.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("libraryBoard")
public class LibraryController {
	@RequestMapping("library")
	public String libraryList() {
		return "lib_Board/lib_board_list";
	}
}
