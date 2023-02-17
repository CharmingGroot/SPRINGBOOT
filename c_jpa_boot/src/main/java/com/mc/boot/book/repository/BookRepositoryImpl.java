package com.mc.boot.book.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.mc.boot.book.Book;
import com.mc.boot.book.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;

//Repository명 + Impl 형태로 인터페이스 이름 작성 필수.
// Spring EntityManager는 thread-safe 하다.
public class BookRepositoryImpl implements BookQueryDSLCustom{

	private final JPAQueryFactory queryFactory;
	private final QBook book = QBook.book;
	
	// entity매니저를 받아서 queryFactory 초기화해주어야함.
	public BookRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public List<Book> findBookWithQueryDSL(String category, int bookAmt, String title) {
		List<Book> books= queryFactory.select(book)
				.from(book)
				.where(book.category.eq(category)
						.and(book.bookAmt.goe(bookAmt))
						.and(book.title.startsWith("디")))
				.fetch();
		return null;
	}

}
