package testSpringBoot.controller.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import testSpringBoot.command.DateCommend;
import testSpringBoot.service.DateService.DateService;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	@Autowired
	DateService dateService ;
	@RequestMapping("schedule")
	public String form(DateCommend dateCommend, Model model) {
		dateService.execute(dateCommend, model);
		return "thymeleaf/schedule/schedule";
	}
	@RequestMapping("reservation")
	public String reservation(DateCommend dateCommend, Model model) {
		dateService.execute(dateCommend, model);
		return "thymeleaf/schedule/schedule";
	}
	
}
