package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController{
	@RequestMapping("/testHello")
	public String aaa(
			@RequestParam(value="name", defaultValue="없어요" ) String name
			,@RequestParam(value="nai", defaultValue="10" ) int nai
			,Model model) {
		model.addAttribute("test",name);
		return "hello";
	}

}
