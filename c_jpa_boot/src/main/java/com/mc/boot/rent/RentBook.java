package com.mc.boot.rent;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.boot.book.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert ������ ������ �� null�� �ʵ�� �������� ����
@DynamicUpdate // entity���� ������ �߰ߵ��� ���� ���� �������� ����
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class RentBook {

   @Id
   @GeneratedValue
   private Long rbIdx;
   
   @Column(columnDefinition = "timestamp default now()")
   private LocalDateTime regDate;
   
   private String state;
   
   @Column(columnDefinition = "timestamp")
   private LocalDateTime returnDate;
   
   @ColumnDefault("0")
   private Integer extenstionCnt; // ���� Ƚ��
   
   @ManyToOne
   @JoinColumn(name="bkIdx")
   private Book book;
   
   @ManyToOne()
   @JoinColumn(name="rmIdx")
   private Rent rent;
 



public static RentBook createRentBook(Rent rent, Book book, String state) {
	return RentBook.builder()
			.rent(rent)
			.book(book)
			.state(state)
			.build();
}




public void returnRentBook(String state, LocalDateTime returnDate) {
	this.state = state;
	this.returnDate = returnDate;
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}