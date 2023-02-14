package com.mc.bookmanager.view.book;

import java.util.List;
import java.util.Scanner;

import com.mc.bookmanager.book.Book;
import com.mc.bookmanager.book.BookController;
import com.mc.bookmanager.book.dto.BookDto;

public class BookMenu {
	
	private Scanner sc = new Scanner(System.in);
	private BookController bookController = new BookController();
	
	public void bookMenu() {
		
		do {
			System.out.println("\n*** ���� ���� ***");
			System.out.println("1. ���� ��ü ��ȸ");
			System.out.println("2. ���� ���");
			System.out.println("3. ���� �Ұ� ����");
			System.out.println("4. ���� ����");
			System.out.println("5. ���� �˻�");
			System.out.println("6. ����");
			System.out.print("��ȣ ���� : ");
			
			switch(sc.nextInt()) {
			case 1 :
				//bookController�� findAllBook()�� ȣ���ϰ�
				//������� ���
				List<BookDto> books = bookController.findAllBook();

				books.forEach(e -> {
					System.out.println(e);
				});
				
				break;
			case 2 : 
				//registBook �޼��带 ȣ���� ����ڷκ��� �߰��� ���� ������ �Է¹ް�
				//bookController�� registBook �޼��带 ȣ���� ���������� �߰�
				//������ ���������� �߰��Ǹ� "���� �߰� ����"
				//���� �߰��� �����ϸ� "���� �߰� ����" ���
				
				if(bookController.registBook(registBook())) {
					System.out.println("�����߰�����");
				}else {
					System.out.println("�����߰�����");
				}
				
				break;
				
			case 3:
				//������ ������ ������ȣ�� ���� �Ұ�(info�÷�)�� ����ڷ� ���� �Է¹޾�
				//bookController �� updateBook�� ȣ���� ���� �Ұ��� �����ϰ�
				//�����ϸ� "���� ���� ����", �����ϸ� "���� ���� ����"�� ����Ͻÿ�.
				sc.nextLine();
				System.out.print("������ ������ȣ�� �Է��ϼ��� : ");
				long bkIdx = sc.nextLong();
				sc.nextLine();
				System.out.print("������ ���� �Ұ��� �Է��ϼ��� : ");
				String info = sc.nextLine();
				
				if(bookController.updateBookInfo(bkIdx, info)) {
					System.out.println("���� ���� ����!");
				}else {
					System.out.println("���� ���� ����");
				}
				
				break;
				
			case 4:
				//������ ������ ������ȣ�� ����ڷ� ���� �Է¹޾�
				//bookController �� removeBook �޼��带 ȣ���ϰ�
				//���� ������ �����ϸ� "���� ���� ����", �����ϸ� "���� ���� ����" ���
				System.out.print("������ ���� ��ȣ : ");
				bkIdx = sc.nextLong();
				
				if(bookController.removeBook(bkIdx)) {
					System.out.println("���� ���� ����");
				}else {
					System.out.println("���� ���� ����");
				}
			
				break;
				
			//searchBookMenu �޼��带 ȣ���� ���� �˻� �޴�â ���
			case 5: searchBookMenu(); break;		
			case 6: return;
			default : System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�.");
			}
		}while(true);
	}
	
	public void searchBookMenu() {
		List<Book> bookList = null;
		do {
			System.out.println("\n*** ���� �˻� �޴� ***");
			System.out.println("1. ���� Ű���� �˻�");
			System.out.println("2. �α� top3 �˻�");
			System.out.println("3. ���� �޴��� �̵�");
			System.out.print("�Է� : ");
			
			switch(sc.nextInt()) {
			case 1 :
				//bookController�� searchBookByTitle �޼��忡 ����ڰ� �Է���
				//�������� �����ϰ� ����� ����Ͻÿ�.	
				System.out.print("�˻��� Ű���带 �Է��ϼ��� : ");
				String keyword = sc.next();
				List<BookDto> books = bookController.findBookByTitle(keyword);
				books.forEach(e -> {
					System.out.println(e);
				});
				
				break;
			case 2 :
				System.out.println("���� �Ǽ��� ���� ���� 3���� ����Դϴ�.");
				
				 bookController.findBookTopN(3).forEach(e -> {
					System.out.println(e);
				 });
				
				break;
			case 3: return;
			default : System.out.println("�߸� �Է��Ͽ����ϴ�. �ٽ� �Է��ϼ���.");
			}
			
		}while(true);
	}
	
	public BookDto registBook() {
		
		BookDto book = new BookDto();
		
		System.out.println("���������� �Է��ϼ���---------------------");
		System.out.print("���� ���� : ");
		book.setTitle(sc.next());
		
		System.out.print("�۰� : " );
		book.setAuthor(sc.next());
		
		System.out.print("ISBN : ");
		book.setIsbn(sc.next());
		
		System.out.print("ī�װ��ڵ� : ");
		book.setCategory(sc.next());
		
		return book;
	}
	
	
	
	

}