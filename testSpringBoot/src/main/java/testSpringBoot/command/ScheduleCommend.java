package testSpringBoot.command;

import lombok.Data;

@Data
public class ScheduleCommend {
	String nYear;
	String nMonth;
	String nDay;
	String action;
	String scheduleSubject;
	String scheduleContent;
}
