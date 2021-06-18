package jp.co.shisa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		return "index";
	}
}
