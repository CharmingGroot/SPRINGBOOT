package com.mc.thymeleaf.study;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mc.thymeleaf.study.dto.MemberDto;

@Controller
public class ThymeleafController {

	@GetMapping("study/thymeleaf")
	public void sendDataToTemplate(Model model) {
		
		MemberDto member = new MemberDto();
		member.setUserId("최호근");
		member.setPassword("1234");
		member.setTell("010-1234-1234");
		member.setRegDate(LocalDateTime.now());
		model.addAttribute("userInfo", member);
		model.addAttribute("thAttrColor","blue");
		model.addAttribute("thAttrBg","lightcoral");
		model.addAttribute("score", 75);
	}
}
