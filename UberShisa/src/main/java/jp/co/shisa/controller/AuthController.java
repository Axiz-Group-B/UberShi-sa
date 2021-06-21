package jp.co.shisa.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.form.LoginForm;

@Controller
@EnableAutoConfiguration
public class AuthController {

	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		return "index.html";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("login")LoginForm form,BindingResult bindingResult,Model model) {
			return "#";





	}


}
