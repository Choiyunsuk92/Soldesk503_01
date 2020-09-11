package testSpringBoot.service.DateService;

import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.DateCommend;
@Component
@Service
public class DateService {
	public void execute(DateCommend dateCommend , Model model) {
		String nYear = dateCommend.getNYear();
		String nMonth = dateCommend.getNMonth();
		String action = dateCommend.getAction();
		Calendar cal = Calendar.getInstance();
		
		if(action == null) {
			//삼각형 버튼을 누르지 않고 맨 처음 페이지를 열었을 때
			cal.set(cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH), 1);
		}else if(action.equals("previous")){
			cal.set(Integer.parseInt(nYear), Integer.parseInt(nMonth)-2, 1);
		}else if(action.equals("next")){
			cal.set(Integer.parseInt(nYear), Integer.parseInt(nMonth), 1);
		}
		nYear = String.valueOf(cal.get(Calendar.YEAR));
		nMonth = String.valueOf(cal.get(Calendar.MONTH));
		int dayNum = cal.get(Calendar.DAY_OF_WEEK) ; // 1일에 해당하는 요일 
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당달의 마지막 날 
		System.out.println(nYear);
		System.out.println(nMonth);
		System.out.println(dayNum);
		System.out.println(maxDay);
		model.addAttribute("nYear", nYear);
		model.addAttribute("nMonth", nMonth);
		model.addAttribute("dayNum", dayNum);
		model.addAttribute("maxDay", maxDay);
	}
}
