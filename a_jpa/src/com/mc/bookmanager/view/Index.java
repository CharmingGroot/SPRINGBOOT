package com.mc.bookmanager.view;

import java.util.Scanner;

import com.mc.bookmanager.view.book.BookMenu;
import com.mc.bookmanager.view.member.MemberMenu;
import com.mc.bookmanager.view.rent.RentMenu;

public class Index {
   
   private Scanner sc = new Scanner(System.in);
   MemberMenu memberMenu = new MemberMenu();
   BookMenu bookMenu = new BookMenu();
   RentMenu rentMenu = new RentMenu();
   
   public void startMenu() {
      while(true) {
         System.out.println("������ �޴��� �����ϼ���.");
         System.out.println("1. ȸ������");
         System.out.println("2. ��������");
         System.out.println("3. �������");
         System.out.println("4. ����");
         System.out.print("�Է� : ");
         
         switch(sc.nextInt()) {
         case 1 : memberMenu.memberMenu(); break;
         case 2 : bookMenu.bookMenu(); break;
         case 3 : rentMenu.rentMenu(); break;
         case 4 : return;
         default :  System.out.println("�߸��� ���ڸ� �Է��ϼ̽��ϴ�.");
         
         }
      }
   }
}