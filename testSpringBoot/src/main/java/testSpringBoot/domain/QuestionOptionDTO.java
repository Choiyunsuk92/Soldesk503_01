package testSpringBoot.domain;

import java.util.List;

import lombok.Data;

@Data
public class QuestionOptionDTO {
	QuestionDTO question;
	List<OptionsDTO> options;
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
