package command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FileInfo {
	String originalFileName;
	String storeFileName;
	String fileSize;
}
