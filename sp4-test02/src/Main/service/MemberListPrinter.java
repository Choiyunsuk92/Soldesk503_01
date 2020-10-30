package Main.service;

import java.util.Collection;

import Main.DTO.CachedMemberDao;
import Main.DTO.MemberDTO;

public class MemberListPrinter {
	private CachedMemberDao memberDao = new CachedMemberDao();
	private MemberPrinter printer = new MemberPrinter();
	public void printAll() {
		Collection<MemberDTO> lists = memberDao.selectAll();
		System.out.println("총 회원 수는 : " + lists.size());
		for(MemberDTO dto :lists ) {
			printer.print(dto);
		}
	}
}
