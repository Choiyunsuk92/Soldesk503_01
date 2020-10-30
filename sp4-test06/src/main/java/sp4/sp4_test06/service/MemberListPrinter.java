package sp4.sp4_test06.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sp4.sp4_test06.DTO.MemberDTO;
import sp4.sp4_test06.DTO.MemberDao;
@Service // service class임을 알려줌
public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	// 의존객체 주입 : Dependency Injection(DI)
	@Autowired // 생성자를 통한 의존객체 주입.
	public MemberListPrinter(MemberDao memberDao, 
			@Qualifier("sysout") MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<MemberDTO> lists = memberDao.selectAll();
		System.out.println("총 회원 수는 : " + lists.size());
		for(MemberDTO dto :lists ) {
			printer.print(dto);
		}
	}
}
