package testSpringBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileName {
	String originalfileName;
	String storeFileName;
	String fileSize;
}
