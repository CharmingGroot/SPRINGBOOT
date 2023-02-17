package com.mc.boot.book;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.boot.book.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert ������ ������ �� null�� �ʵ�� �������� ����
@DynamicUpdate // entity���� ������ �߰ߵ��� ���� ���� �������� ����
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Book {
	
	//�⺻Ű
	@Id
	@GeneratedValue  // JPA�� ��å�� ���� �ĺ��� �ڵ� ����
	private Long bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	
	@ColumnDefault("0")
	private Integer bookAmt;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ColumnDefault("0")
	private Integer rentCnt;

	public static Book createBook(BookDto dto) {
		return Book.builder().title(dto.getTitle()).author(dto.getAuthor()).isbn(dto.getIsbn()).category(dto.getCategory()).build();
	}

	public void updateInfo(String info) {
		this.info = info;
	}

	public void updateBookAmt(int bookAmt) {
		this.bookAmt = bookAmt;
		
	}
	
}