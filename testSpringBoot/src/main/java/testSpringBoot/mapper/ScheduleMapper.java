package testSpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import testSpringBoot.domain.ScheduleDTO;
@Repository
public interface ScheduleMapper {
	public  void scheduleInsert(ScheduleDTO dto) throws Exception;
	public List<ScheduleDTO> scheduleSelectList(ScheduleDTO dto) throws Exception;
}
