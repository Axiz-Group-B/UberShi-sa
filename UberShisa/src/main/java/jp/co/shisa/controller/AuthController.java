package jp.co.shisa.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class AuthController {
	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		return "index.html";
	}
}
