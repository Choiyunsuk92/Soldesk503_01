package Main.service;

import Main.DTO.MemberDao;

public class Assembler {
	private MemberDao memberDao;
	private MemberPrinter printer;
	private MemberRegisterService memberRegisterService;
	private MemberListPrinter memberListPrinter;
	private ChangePasswordService changePasswordService;
	private MemberInfoPrinter memberInfoPrinter;
	public Assembler() {
		this.memberDao = new MemberDao();
		this.printer = new MemberPrinter();
		this.memberRegisterService = 
				new MemberRegisterService(memberDao);
		this.memberListPrinter = 
				new MemberListPrinter(memberDao,printer );
		this.changePasswordService =
				new ChangePasswordService();
		this.memberInfoPrinter = 
				new MemberInfoPrinter();
	}
	
	public MemberListPrinter getMemberListPrinter() {
		return memberListPrinter;
	}
	public MemberRegisterService getMemberRegisterService() {
		return memberRegisterService;
	}
	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberPrinter getPrinter() {
		return printer;
	}
	
	
}
