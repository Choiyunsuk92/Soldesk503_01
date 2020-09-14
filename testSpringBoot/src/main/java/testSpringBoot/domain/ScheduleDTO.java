package testSpringBoot.domain;

import lombok.Data;

@Data
public class ScheduleDTO {
	String nYear;
	String nMonth;
	String nDay;
	String scheduleSubject;
	String scheduleContent;
}
