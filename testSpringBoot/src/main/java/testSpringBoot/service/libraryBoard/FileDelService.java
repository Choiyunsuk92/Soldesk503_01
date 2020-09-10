package testSpringBoot.service.libraryBoard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import testSpringBoot.FileName;
@Component
@Service
public class FileDelService {
	public void fileSessionAdd(FileName fileName, HttpSession session,Model model) {
		List<FileName> list = (List<FileName>)session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileName>();
		}
		// delPage에 moidel로 0 이나 1값을 전달하기 윈한 변수 
		int num = 0;
		// session이 존재하지 않으면 true, session이 존재하면 false
		Boolean newFile = true;
		for(int i = 0; i < list.size(); i++) {
			FileName fn = list.get(i);
			if(fn.getStoreFileName().equals(fileName.getStoreFileName())) {
				// 삭제 취소 버튼을 누르면 list에 있는 값 삭제 
				list.remove(i);
				newFile = false;
				num = 0;
			}
		}
		if(newFile) {
			// list나 session에 없으면 리스트에 추가 
			list.add(fileName);
			num = 1;
		}
		// delPage에 num가 가지고 있는 값을   val로 전달 
		model.addAttribute("val", num);
		session.setAttribute("fileList", list);
	}
}

	