package com.tirmizee.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.core.entities.DemoMember;
import com.tirmizee.core.entities.DemoUser;
import com.tirmizee.core.repositories.DemoUserRepository;

@RestController
@RequestMapping(path = "/user")
public class UserApiController {
	
	@Autowired
	private DemoUserRepository userRepository;
	
	@GetMapping(path = "/test")
	public String test() {
		DemoUser users = userRepository.findOne(1); 
		for (DemoMember member : users.getMembers()) {
			System.out.println(member.getMemberCode() + " " + member.getMemberName());
		}
		return "qwert";
	}

}
