package testSpringBoot.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.command.ScheduleCommend;
import testSpringBoot.domain.ScheduleDTO;
import testSpringBoot.mapper.ScheduleMapper;
@Component
@Service
public class ScheduleService {
	@Autowired
	ScheduleMapper scheduleMapper;
	public void execute(ScheduleCommend scheduleCommend,Model model) throws Exception{
		ScheduleDTO dto = new ScheduleDTO();
		dto.setNDay(scheduleCommend.getNDay());
		dto.setNMonth(scheduleCommend.getNMonth());
		dto.setNYear(scheduleCommend.getNYear());
		dto.setScheduleContent(scheduleCommend.getScheduleContent());
		dto.setScheduleSubject(scheduleCommend.getScheduleSubject());
		scheduleMapper.scheduleInsert(dto);
	}
}
