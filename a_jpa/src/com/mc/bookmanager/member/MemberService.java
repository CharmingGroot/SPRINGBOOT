package com.mc.bookmanager.member;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mc.bookmanager.jpa.EntityTemplate;
import com.mc.bookmanager.member.dto.MemberDto;

public class MemberService {
	
	private MemberRepository memberRepository = new MemberRepository();

	public MemberDto findMemberById(String userId) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		Member member = null;
		
		try {
			member = em.find(Member.class, userId);
		} finally {
			//EntityManager ����
			//EntityManager�� �����ϸ� Entity���� ���̻� EntityManager�� ��������� �ƴϰԵȴ�. == �ؿ��ӻ��·� ����ȴ�.
			// close : EntityManger ����
			// clear : EntityManager �ʱ�ȭ, EntityManager�� �����ϰ� �ִ� ��� Entity���� �ؿ��� ���·� ����
			// detach : Ư�� Entity�� �ؿ��� ���·� ������ �� ���
			em.close();
		}
		
		return new MemberDto(member);
	}

	public List<MemberDto> findAllMember() {
		
		List<Member> members = null;
		EntityManager em = EntityTemplate.getEntityManager();
		
		try {
			members = memberRepository.findAllMember(em);
		} finally {
			em.close();
		}
		
		return MemberDto.toDtoList(members);
	}

	public boolean createMember(MemberDto dto) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		// Ʈ����� ����
		tx.begin();
		
		try {
			// memberdto�� entitiy�� �����������.
			em.persist(Member.createMember(dto));
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}

	public boolean updateMember(MemberDto memberDto) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		System.out.println(memberDto);
		try {
			// ���ӻ��� entity
			Member entity = em.find(Member.class, memberDto.getUserId());
			
			//entityManager�� �����ϴ� Entity�� �Ӽ��� ����Ǿ�������
			//entityManager�� Ʈ������� commit�Ǵ� ������, ����� ������ ����ȭ -> dirty check
			entity.updateMember(memberDto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		return false;
	}

	public List<MemberDto> findMemberByRegDate(LocalDateTime begin, LocalDateTime end) {
		EntityManager em = EntityTemplate.getEntityManager();
		List<Member> members = null;
		
		try {
			members = memberRepository.findAllMember(em);
			
		} finally {
			em.close();
		}
		
		return MemberDto.toDtoList(members);
	}

	public boolean removeMemberByUserId(String userId) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		
		
		
		try {
			Member member = em.find(Member.class, userId);
			em.remove(member);
			
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
