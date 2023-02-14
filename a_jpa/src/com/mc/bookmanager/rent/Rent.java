package com.mc.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.mc.bookmanager.member.Member;

import lombok.Data;

@Data
@Entity
public class Rent {

	@Id
	@GeneratedValue
	private Long rmIdx;
	
	// create ������ �÷����� ������ �� �ۼ��ϴ� ������ columnDefinition�� ����
	// create Rent(regDate timestamp default now() . . .)
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ColumnDefault("false")
	private Boolean isReturn;
	private String title;
	private Integer rentBookCnt;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	// CascadeType
	// PERSIST	: PERSIST�� ������ �� ���� ��ƼƼ�� �Բ� ���� => Rent�� ���̺� ����� �� RentBook�� �Բ� ����
	// REMOVE	: ��ƼƼ�� ������ �� ���� ��ƼƼ�� �Բ� ����
	// MERGE	: �ؿ��ӻ����� ��ƼƼ�� MERGE�ؼ� ���ӻ��·� ���� �� ���� ��ƼƼ�� �Բ� ���ӻ��·� ����
	// DETACH	: ���ӻ����� ��ƼƼ�� �ؿ��ӻ��·� ���� �� ������ƼƼ�� �Բ� ����
	// ALL		: PERSIST + REMOVE + MERGE + DETECH
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rent")
	private List<RentBook> rentBooks = new ArrayList<>();
}


// ���� ManyToOne �̳� OneToMany 
// 1:n, n:1 ���迡���� �׻� n���� ���������� �����̴�.
// ���������� ������ �ƴ����� ��ü �б⸸ �����ϴ�.
// 


















