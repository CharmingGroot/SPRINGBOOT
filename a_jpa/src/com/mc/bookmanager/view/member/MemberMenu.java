package com.mc.bookmanager.view.member;

import java.util.List;
import java.util.Scanner;

import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.member.MemberController;
import com.mc.bookmanager.member.dto.MemberDto;

public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	public void memberMenu() {
		
		do {
			System.out.println("\n***  ȸ�� ���� ���α׷� ***");
			System.out.println("1. ȸ�� ��ü ��ȸ");
			System.out.println("2. �� ȸ�� ���");
			System.out.println("3. ȸ�� ����");
			System.out.println("4. ȸ�� Ż��");
			System.out.println("5. ȸ�� �˻�");
			System.out.println("6. ���� ������");
			System.out.print("��ȣ �Է� : ");
			
			switch(sc.nextInt()) {
			
				case 1:
					List<MemberDto> members = memberController.findAllMember();
					members.forEach(e -> {
						System.out.println(e);
					});
					break;
					
				case 2: if(memberController.signUp(join())){
							System.out.println("ȯ���մϴ�! ȸ�����Կ� �����Ͽ����ϴ�.");
						}else {
							System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
						}
					break;
					
				case 3: 
					sc.nextLine();
					MemberDto member = new MemberDto();
					System.out.print("ȸ�������� ������ ���̵� : ");
					member.setUserId(sc.nextLine());
					
					System.out.print("������ ��й�ȣ : ");
					String password = sc.nextLine();
					if(!password.equals("")) {
						member.setPassword(password);
					}
					
					System.out.print("������ ��ȭ��ȣ : ");
					String tell = sc.nextLine();
					if(!tell.equals("")) {
						member.setTell(tell);
					}
					
					System.out.print("������ �̸��� : ");
					String email = sc.nextLine();
					if(!email.equals("")) {
						member.setEmail(email);
					}
				
					if(memberController.updateMember(member)) {
						System.out.println("ȸ������ ������ ���������� �Ϸ�Ǿ����ϴ�.");
					}else {
						System.out.println("ȸ������ ������ �����߽��ϴ�.");
					}
					break;
					
				case 4: 
					System.out.print("Ż�� ��ų ȸ���� ���̵� �Է� : ");
					String userId = sc.next();
					if(memberController.removeMember(userId)) {
						System.out.println("ȸ��Ż��ó���� �ȷ�Ǿ����ϴ�.");
						
					}else {
						System.out.println("ȸ��Ż��ó���� �����߽��ϴ�.");
					}
					
					break;
					
				case 5: searchMenu(); break;
				
				
				case 6: return;
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
			}
		}while(true);
	}
	
	public void searchMenu() {
		
		do {
			System.out.println("\n*** ȸ�� �˻� �޴� ***");
			//cache�����ϱ�
			System.out.println("1. ���̵�� �˻�");
			System.out.println("2. ���Գ�¥�� �˻�");
			System.out.println("3. ���� �޴��� �̵�");
			System.out.print("��ȣ ���� : ");
			
			switch(sc.nextInt()) {
				case 1 : System.out.print("�˻��� ���̵� : ");
						 String userId = sc.next();
						 MemberDto member = memberController.findMemberById(userId);
						 System.out.println(member);
						 break;
						 
				case 2 : System.out.print("�˻��� ���� ���� ��¥[yyyy-mm-dd] :");
						 String begin = sc.next();
						 System.out.print("�˻��� ���� ����¥[yyyy-mm-dd] : ");
						 String end = sc.next();
						 
						 List<MemberDto> members = memberController.findMemberByRegDate(begin,end);
						 members.forEach(e->{
							 System.out.println(e);
						 });
						 
						 break;
						 
				case 3 : return;
				default : System.out.println("�߸� �ԷµǾ����ϴ�. �ٽ� �Է��ϼ���.");
			}
		}while(true);
	}
		
	//����ڷκ��� ȸ������ ������ �޾Ƽ� member��ü�� ��ȯ
	public MemberDto join() {
		MemberDto member = new MemberDto();
		System.out.println("ȸ�� ������ �Է��ϼ���.-------------");
		
		System.out.print("���̵� : ");
		member.setUserId(sc.next());
		
		System.out.print("��ȣ : ");
		member.setPassword(sc.next());
		
		System.out.print("�̸��� : ");
		member.setEmail(sc.next());
		
		System.out.print("��ȭ ��ȣ : ");
		member.setTell(sc.next());
		return member;
	}

	
	
	
	
	
	
	
}
