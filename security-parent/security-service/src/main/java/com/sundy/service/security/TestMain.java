package com.sundy.service.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class TestMain {

	public static void main(String[] args) {
		//进行加密
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String textPlain="sundy";
        String password=encoder.encode(textPlain.trim());
        
        System.out.println(password);
	}

}
