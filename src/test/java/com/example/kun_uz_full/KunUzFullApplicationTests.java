package com.example.kun_uz_full;

import com.example.kun_uz_full.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KunUzFullApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(MD5Util.MD5Encode("12345"));
	}

}
