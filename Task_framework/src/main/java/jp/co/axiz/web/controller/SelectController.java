package jp.co.axiz.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.axiz.web.controller.form.SelectForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class SelectController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;

	@GetMapping("/select")
	public String select(Model model) {
		return "select";
	}

	@GetMapping(value = "/list")
	public String selectCondition(@ModelAttribute("selectResult") SelectForm selectForm, Model model) {
		List<User> userList = userService.findByCondition(selectForm.getUserName(), selectForm.getTelephone());
		if (userList == null) {
			model.addAttribute("selectErrMsg", "入力されたデータはありませんでした");
			return "select";
		} else {
			session.setAttribute("userList", userList);
			return "selectResult";
		}
	}
}
