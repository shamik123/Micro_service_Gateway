package com.example.Gateway;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {

	public static void main(String[] args) {
	String recordFile = "D:\\Temp\\record.txt";
	String line ="Test";
	
	PasswordEncoder pw = new BCryptPasswordEncoder(4);
	String password = pw.encode("admin1234");
	System.out.println("Password is" + password);
	Boolean status = pw.matches("admin1234","$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha");
	System.out.println("Match is" + status);
	
}
}