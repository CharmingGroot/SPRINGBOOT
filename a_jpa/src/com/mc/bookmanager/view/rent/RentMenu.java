package com.mc.bookmanager.view.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mc.bookmanager.rent.RentController;
import com.mc.bookmanager.rent.dto.RentDto;

public class RentMenu {
   
   private RentController rentController = new RentController();
   
   public void rentMenu() {
      Scanner sc = new Scanner(System.in);
      String userId = null;
      
      do {
         System.out.println("\n*** ���� ���� ***");
         System.out.println("1. �����ȣ�� ����� ��ȸ");
         System.out.println("2. ���� ����");
         System.out.println("3. ���� �ݳ�");
         System.out.println("4. ����� ���̵�� ����� ��ȸ");
         System.out.println("5. ������");
         System.out.print("���� : ");
         
         switch (sc.nextInt()) {
         case 1: System.out.print("��ȸ�� ���� ������ȣ�� �Է��ϼ��� : ");
         		RentDto rentDto = rentController.findRentByIdx(sc.nextLong());
         		System.out.println(rentDto);
         
               break;
         case 2: //�������� ���̵� �Է¹ް�
               System.out.print("�������� ���̵� �Է��ϼ��� : ");
               userId = sc.next();
               List<Long> bkIdxs = new ArrayList<>();
               
               //�����ڰ� ���� �ϰ��� �ϴ� ������ȣ�� �Է¹޴´�. �ѹ��� ���� ������ ������ �ִ� 5�Ǳ����̴�.
               for (int i = 0; i < 5; i++) {
                  System.out.print("������ ������ ������ȣ�� �Է��ϼ��� : ");
                  bkIdxs.add(sc.nextLong());
                  
                  if(i < 4) {
                     System.out.print("������ ������ �� �����ϳ���?(y/n) : ");
                     if(sc.next().toLowerCase().equals("n")) {
                        break;
                     }
                  }
               }
               
               
               if(rentController.registRent(userId, bkIdxs)) {
            	   System.out.println("�������� ����");
               } else {
            	   System.out.println("�������� ����");
               }
            
            break;
         case 3: //�ݳ��� ���⵵����ȣ(rbIdx)�� �Է¹޾�
               //�ش� rbIdx�� ���⵵���� �ݳ�ó��
            System.out.print("�ݳ��� ���� �����ȣ�� �Է��ϼ��� : ");
            
            
            if(rentController.returnRentBook(sc.nextLong())) {
            	System.out.println("�ݳ� ����");
            } else {
            	System.out.println("�ݳ� ����");
            }
            
            
            
            
            
            
            
            
            
            break;
            
         case 4: sc.nextLine();
               System.out.print("���⳻���� ��ȸ�� ����� ���̵� �Է��ϼ��� : ");
               userId = sc.nextLine();
               
               List<RentDto> rents = rentController.findRentByUserId(userId);
               
               rents.forEach(e-> {
            	   System.out.println(e);   
               });
               
               
               
               System.out.print("����ȸ �� ������� �����ϳ���? (y/n) : ");
               String input = sc.nextLine();
               
               if(input.equalsIgnoreCase("y")) {
                  System.out.print("����ȸ�� �����ȣ�� �Է��ϼ��� : ");
                  Long rmIdx = sc.nextLong();
                  sc.nextLine(); 
                  
               }
            break;
            
         case 5: return;
         
         default:System.out.println("�߸��� ���ڸ� �Է��ϼ̽��ϴ�.");

         }
         
      }while(true);
   }

}