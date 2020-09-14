package testSpringBoot.controller.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import testSpringBoot.command.ScheduleCommend;
import testSpringBoot.service.schedule.DateService;
import testSpringBoot.service.schedule.ScheduleService;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	@Autowired
	DateService dateService ;
	@Autowired
	ScheduleService scheduleService;
	@RequestMapping("schedule")
	public String form(ScheduleCommend dateCommend, Model model) throws Exception{
		dateService.execute(dateCommend, model);
		return "thymeleaf/schedule/schedule";
	}
	@RequestMapping("reservation")
	public String reservation(@ModelAttribute("dateCommend")ScheduleCommend dateCommend, Model model) {
		return "thymeleaf/schedule/reservation";
	}
	@RequestMapping("reservationOk")
	public String reservationOk(ScheduleCommend dateCommend,Model model) throws Exception{
		scheduleService.execute(dateCommend, model);
		return "redirect:/schedule/schedule";
	}
}
