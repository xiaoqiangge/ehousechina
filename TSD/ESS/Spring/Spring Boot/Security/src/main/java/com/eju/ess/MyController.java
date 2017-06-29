package com.eju.ess;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String postTemplates() {
		return "11111111111111";
	}
}
