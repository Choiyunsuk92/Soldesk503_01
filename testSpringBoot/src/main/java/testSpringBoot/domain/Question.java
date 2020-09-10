package testSpringBoot.domain;

import java.util.List;

import lombok.Data;

@Data
public class Question {
	String title;
	private List<String> options;
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
