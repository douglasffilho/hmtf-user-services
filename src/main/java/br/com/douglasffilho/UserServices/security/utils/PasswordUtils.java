package br.com.douglasffilho.UserServices.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	public static String generateBCrypt(String password) {
		if (password == null) {
			return password;
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.encode(password);
		}
	}

	public static boolean validatePassword(String password, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, encodedPassword);
	}

}
