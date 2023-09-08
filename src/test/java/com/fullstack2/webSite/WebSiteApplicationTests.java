package com.fullstack2.webSite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstack2.webSite.repository.QnABoardRepository;

@SpringBootTest
class WebSiteApplicationTests {

	@Autowired
	private QnABoardRepository qnaBoardRepository;
	
	@Test
	void contextLoads() {
		String password = qnaBoardRepository.findPasswordByBno(22L);
		System.out.println(password);
	}

}
