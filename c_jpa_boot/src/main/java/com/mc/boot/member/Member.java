package com.mc.boot.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.mc.boot.member.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert ������ ������ �� null�� �ʵ�� �������� ����
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Member {
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime rentableDate;

	public void updateMember(MemberDto memberDto) {
		if(memberDto.getPassword() != null) this.password = memberDto.getPassword();
		if(memberDto.getEmail() != null) this.email = memberDto.getEmail();
		if(memberDto.getTell() != null) this.tell = memberDto.getTell();
	}
	
	// ��������
	public static Member createMember(MemberDto dto) {
		return Member.builder()
				.userId(dto.getUserId())
				.password(dto.getPassword())
				.tell(dto.getTell())
				.email(dto.getEmail())
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
