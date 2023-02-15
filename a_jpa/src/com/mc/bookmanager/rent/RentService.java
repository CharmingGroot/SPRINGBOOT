package com.mc.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mc.bookmanager.book.Book;
import com.mc.bookmanager.jpa.EntityTemplate;
import com.mc.bookmanager.member.Member;
import com.mc.bookmanager.rent.dto.RentDto;

public class RentService {

	private RentRepository rentRepository = new RentRepository();
	public RentDto findRentByIdx(long rmIdx) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		RentDto dto = null;
		
		try {
			Rent rent = em.find(Rent.class, rmIdx);
			dto = new RentDto(rent);
		} finally {
			em.close();
		}
		
		return dto;
	}
	public boolean createRent(String userId, List<Long> bkIdxs) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			
			// �����
			// ù��° ���⵵���� �� n��
			
			List<Book> books = new ArrayList<Book>();
			
			for (Long bkIdx : bkIdxs) {
				books.add(em.find(Book.class, bkIdx));
			}
			
			String title = books.size() > 1 ? books.get(0).getTitle() + "��" + books.size() + "��"
					: books.get(0).getTitle();
			
			
			Member member = em.find(Member.class, userId);
			
			Rent rent = Rent.createRent(title, member, books.size());
			List<RentBook> rentBooks = new ArrayList<RentBook>();
			
			for (Book book : books) {
				rentBooks.add(RentBook.createRentBook(rent,book,"����"));
			}
			
			rent.addRentBooks(rentBooks);
			
			em.persist(rent);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}
	public boolean returnRentBook(long rbIdx) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.returnRentBook("�ݳ�", LocalDateTime.now());
					
			// ��� ���⵵���� �ݳ�ó���Ǹ�
			// �������̺��� ������¸� �ݳ����� �Բ� ����
			if(rentBook.getRent().getRentBooks().stream().allMatch(e-> e.getState().equals("�ݳ�"))) {
				rentBook.getRent().returnComplete();
			}
			
			
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		
		
		return false;
	}
	public List<RentDto> findRentByUserId(String userId) {

		EntityManager em = EntityTemplate.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		List<RentDto> rentDtos = null;
		
		try {
			List<Rent> rents = rentRepository.findRentByUserId(em, userId);
			
			System.out.println("-------------------------------------------");
			
			rentDtos = RentDto.toDtoList(rents);
			
			
		} finally {
			em.close();
		}
				
		
		
		return rentDtos;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
