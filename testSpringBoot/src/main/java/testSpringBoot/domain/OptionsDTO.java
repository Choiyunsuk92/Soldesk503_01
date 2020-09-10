package testSpringBoot.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsDTO {
	String userId;
	Integer questionNum;
	Integer optionNum;
	String optionName;
}
