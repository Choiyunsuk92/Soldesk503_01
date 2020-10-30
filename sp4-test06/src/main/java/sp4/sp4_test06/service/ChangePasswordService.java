package sp4.sp4_test06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sp4.sp4_test06.DTO.MemberDTO;
import sp4.sp4_test06.DTO.MemberDao;
@Service  // service클래스 파일
public class ChangePasswordService {
	@Autowired //setter를 통한 의존객체 자동주입
	// compile된 class 파일에는 setter가 존재한다. 
	private MemberDao memberDao;

	public void changePassword(String email, String oldPw,
			String newPw) {
		MemberDTO dto = memberDao.selectByEmail(email);
		if(dto == null) {
			System.out.println("이메일이 존재하지 않습니다.");
			return;
		}
		dto.changePassword(oldPw, newPw);
		memberDao.update(dto);
	}
}
