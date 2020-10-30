package Main.service;

import Main.DTO.MemberDTO;
import Main.DTO.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao = new MemberDao();
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
