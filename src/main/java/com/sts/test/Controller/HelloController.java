package com.sts.test.Controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sts.test.Service.TT0001Service;

@Controller
public class HelloController {
	@Autowired
	TT0001Service service;
	Hoge hoge = new Hoge();

	@GetMapping("/")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {

		if( service.getAllTT0001().size() > 0)
			model.addAttribute("tt0001_list", service.getAllTT0001());
		
		model.addAttribute("message", "Hello Thymeleaf!!");
		model.addAttribute("model_value", "MODEL VALUE !");

		HashMap<String, String> map = new HashMap<>();
        map.put("hoge", "HOGE");
        map.put("fuga", "FUGA");
        map.put("piyo", "PIYO");

        model.addAttribute("map", map);
        model.addAttribute("list", Arrays.asList("hoge", "fuga", "piyo"));
		model.addAttribute("hoge", hoge);
		return "hello";		
	}

	public static class Hoge {
		public int publicField = 1;
		public String name = "hogeee";
        public String value = "HOGEEE";
		public int publicMethod() {
			return 2;
		}
		public int getPublicValue() {
			return 3;
		}
	}
	
	@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/msg");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}