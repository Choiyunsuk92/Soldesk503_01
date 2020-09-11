package testSpringBoot.command;

import java.util.List;

import lombok.Data;

@Data
public class AnsweredData {
	private List<String> responses;
	private Respondent res;
	
}
