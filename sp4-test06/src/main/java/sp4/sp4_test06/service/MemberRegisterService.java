package sp4.sp4_test06.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sp4.sp4_test06.DTO.MemberDTO;
import sp4.sp4_test06.DTO.MemberDao;
import sp4.sp4_test06.DTO.RegisterRequest;
@Service
public class MemberRegisterService {
	// 의존 객체
	@Autowired
	private MemberDao memberDao;
	public void regist(RegisterRequest req) {
		MemberDTO dto = memberDao.selectByEmail(req.getEmail());
		if (dto == null) {
			dto = new MemberDTO();
			dto.setEmail(req.getEmail());
			dto.setName(req.getName());
			dto.setPassword(req.getPassword());
			dto.setRegisterDate(new Date());
			memberDao.insert(dto);
			System.out.println("사용자 등록이 완료 되었습니다.");
		} else {
			System.out.println("중복 사용자입니다.");
		}
	}
}
