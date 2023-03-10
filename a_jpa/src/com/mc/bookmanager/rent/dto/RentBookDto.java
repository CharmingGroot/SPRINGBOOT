package com.mc.bookmanager.rent.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mc.bookmanager.book.dto.BookDto;
import com.mc.bookmanager.member.dto.MemberDto;
import com.mc.bookmanager.rent.RentBook;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentBookDto {
	
	private Long rbIdx;
	private LocalDateTime regDate;
	private String state;
	private LocalDateTime returnDate;
	private int extenstionCnt;
	private BookDto book;
	private Long rmIdx;
	
	public RentBookDto(RentBook rentBook) {
		super();
		
		this.rbIdx = rentBook.getRbIdx();
		
		this.regDate = rentBook.getRegDate();
		
		this.state = rentBook.getState();

		this.returnDate = rentBook.getReturnDate();

		this.extenstionCnt = rentBook.getExtenstionCnt(); // ???? Ƚ??

		this.book = new BookDto(rentBook.getBook());
		this.rmIdx = rentBook.getRent().getRmIdx();
	}


	
	
	public static List<RentBookDto> toDtoList(List<RentBook> entityList){
		return entityList.stream().map(e-> new RentBookDto(e)).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
