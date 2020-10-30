package sp4.sp4_test06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sp4.sp4_test06.DTO.MemberDTO;
import sp4.sp4_test06.DTO.MemberDao;
@Service
public class MemberInfoPrinter {
	// setter를 통한 의존객체 주입
	@Autowired // @Resource(name ="memberDao")
	private MemberDao memberDao;
	private MemberPrinter printer;
	// 의존객체 주입 : Dependency Injection(DI)
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 의존객체 주입 : Dependency Injection(DI)
	@Autowired
	@Qualifier("sysout")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	public void printMemberInfo(String eamil) {
		MemberDTO dto = memberDao.selectByEmail(eamil);
		if(dto == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(dto);
	}
}
