package com.incident.tool.utiliy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incident.tool.security.utility.EncDec;



@RestController
@RequestMapping("/incident")
public class EncryptJsonString {
	@Autowired
	EncDec enc;
	
	@PostMapping("/encrypt")
	public String encryptJson(@RequestBody String json) {
		return enc.encrypt(json);
	}
	
	  
	@GetMapping("/test")
	public String getTest(@RequestBody String a) { 
		return a; 
		}
	 

}
