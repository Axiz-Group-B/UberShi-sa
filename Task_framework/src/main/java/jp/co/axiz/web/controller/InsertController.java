package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.controller.form.InsertConfirmForm;
import jp.co.axiz.web.controller.form.InsertForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.RoleService;
import jp.co.axiz.web.service.UserService;

@Controller
public class InsertController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(@ModelAttribute("insert") InsertForm insertForm, Model model) {
		insertForm.setRoleId(2);
		return "insert";
	}

	@RequestMapping(value = "/insertConfirm", method = RequestMethod.POST)
	public String insertConfirm(@Validated @ModelAttribute("insert") InsertForm insertForm,
			BindingResult bindingResult, @ModelAttribute("insertConfirm") InsertConfirmForm insertConfirmForm, Model model) {
		if (bindingResult.hasErrors()) {
			return "insert";
		} else {
			if (userService.findById(insertForm.getLoginId()) != null) {
				model.addAttribute("insertErrMsg", "IDが重複しています");
				return "insert";
			} else {
				User registerUser = new User(insertForm.getLoginId(), insertForm.getUserName(),
						 insertForm.getTelephone(), insertForm.getPassword(),
						insertForm.getRoleId());
				session.setAttribute("registerUser", registerUser);
				return "insertConfirm";
			}
		}
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertResult(@Validated @ModelAttribute("insertConfirm") InsertConfirmForm insertConfirmForm,
			BindingResult bindingResult, Model model) {
		User registerUser = (User) session.getAttribute("registerUser");
//		System.out.println(registerUser.getPassword());

//		System.out.println(prePass);
		if(bindingResult.hasErrors()) {

			return "insertConfirm";
		}
		else {
			String prePass = (String) registerUser.getPassword();
			if(!prePass.equals(insertConfirmForm.getRePass())) {
				model.addAttribute("insertConfirmErrMsg", "前画面で入力されたパスワードが一致しません");
				return "insertConfirm";
			}
			else {
				userService.userRegister(registerUser);
				return "insertResult";
			}
		}

	}
}
