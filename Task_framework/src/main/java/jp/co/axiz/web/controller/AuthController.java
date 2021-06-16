package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.AuthForm;
import jp.co.axiz.web.entity.Role;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.RoleService;
import jp.co.axiz.web.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("login") AuthForm auForm, Model model) {
		return "login";
	}

	@PostMapping(value = "/logout")
	public String logout(Model model){
		session.invalidate();
		return "logout";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String authLogin(@Validated @ModelAttribute("login") AuthForm auForm, BindingResult bindingResult,
			Model model) {
		User user = userService.loginAuth(auForm.getLoginId(), auForm.getPassword());
		if (bindingResult.hasErrors()) {
			return "login";
		} else if (user != null) {
			List<Role> roleList = roleService.selectAllRole();
			session.setAttribute("roleList", roleList);
			session.setAttribute("user", user);
			return "menu";
		} else {
			model.addAttribute("loginErrMsg", "IDまたはパスワードが間違っています");
			return "login";
		}

	}
}
