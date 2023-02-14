package com.mc.bookmanager.view.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mc.bookmanager.rent.RentController;

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
         System.out.println("4. ���� ���� ����� ��ȸ");
         System.out.println("5. ������");
         System.out.print("���� : ");
         
         switch (sc.nextInt()) {
         case 1: //�ݳ��� ���⵵����ȣ(rbIdx)�� �Է¹޾�
               //�ش� rbIdx�� ���⵵���� �ݳ�ó��
               System.out.print("��ȸ�� ���� ������ȣ�� �Է��ϼ��� : ");

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
            
            break;
         case 3: //�ݳ��� ���⵵����ȣ(rbIdx)�� �Է¹޾�
               //�ش� rbIdx�� ���⵵���� �ݳ�ó��
            System.out.print("�ݳ��� ���� �����ȣ�� �Է��ϼ��� : ");
            
            break;
            
         case 4: //������� ��ȸ�� ������� ���̵� �Է¹޾�
               //rentController �� searchRentList �޼��� ȣ��
               //��ȯ ���� rentList�� ���
               //����� ����� ����� ����
               //����ڿ��� ����� �� ��ȸ���θ� ����
                //����ڰ� ����� �� ��ȸ�� �ϰڴٰ� �ϸ�
                //�� ��ȸ�� ����� ��ȣ�� �Է¹ް�
                //�ش� ������� ���⵵�� ����� ���
            
               //part.2 : ���� ������ ����ǿ���, ���°� '����'�� ���⵵���� ��ȸ�غ���.
               sc.nextLine();
               System.out.print("���⳻���� ��ȸ�� ����� ���̵� �Է��ϼ��� : ");
               userId = sc.nextLine();
               
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