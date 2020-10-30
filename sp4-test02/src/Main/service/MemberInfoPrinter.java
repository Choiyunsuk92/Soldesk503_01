package Main.service;//sp4-test03

import Main.DTO.CachedMemberDao;
import Main.DTO.MemberDTO;

public class MemberInfoPrinter {
	private CachedMemberDao memberDao = new CachedMemberDao();
	private MemberPrinter printer = new MemberPrinter();
	public void printMemberInfo(String eamil) {
		MemberDTO dto = memberDao.selectByEmail(eamil);
		if(dto == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(dto);
	}
}
