package com.mc.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mc.bookmanager.book.Book;
import com.mc.bookmanager.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



// EntityGraph : Entity ��ȸ�ÿ� �Բ� ��ȸ�� ���� ��ƼƼ�� ����
@NamedEntityGraph(
	name = "Rent.rentBooks",
	attributeNodes = {
			@NamedAttributeNode(value = "rentBooks", subgraph = "RentBook.book")
	}
	,subgraphs = @NamedSubgraph(name = "RentBook.book", attributeNodes = {
			@NamedAttributeNode("book")
	})
)
@Entity
@DynamicInsert 
@DynamicUpdate 
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Rent {

	@Id
	@GeneratedValue
	private Long rmIdx;

	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ColumnDefault("false")
	private Boolean isReturn;
	private String title;
	private Integer rentBookCnt;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	// JPA���� toMany������ FetchType�� default�� LAZY
	//			toOne ������ FetchType�� default�� EAGER
	
	// 			EAGER : ��ƼƼ�� ��ȸ�� �� ���� ��ƼƼ�� �Բ� ��ȸ��.
	// 			LAZY : ��ƼƼ�� ��ȸ�� ���� ������ƼƼ�� ��ȸ���� �ʰ�, �ش� ������ƼƼ(�Ӽ�)�� ó������ ȣ��Ǵ� ������ ������ƼƼ�� ��ȸ
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rent", fetch = FetchType.LAZY)
	private List<RentBook> rentBooks = new ArrayList<>();



	
	public void addRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;
	}




	public static Rent createRent(String title, Member member, int rentBookCnt) {
		return Rent.builder()
				.title(title)
				.member(member)
				.rentBookCnt(rentBookCnt)
				.build();
	}




	public void returnComplete() {
		this.isReturn = true;
	}
	
	
}



















