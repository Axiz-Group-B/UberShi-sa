package jp.co.axiz.web.controller;

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

import jp.co.axiz.web.controller.form.DeleteForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class DeleteController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("delete") DeleteForm delForm, Model model) {

		return "delete";
	}
	@PostMapping(value = "/deleteConfirm")
	public String deleteConfirm(@Validated @ModelAttribute("delete") DeleteForm delForm,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "delete";
		}
		else {
			User loginUser = (User) session.getAttribute("user");
			User deleteUser = userService.findById(delForm.getLoginId());

			if(deleteUser == null) {
				model.addAttribute("delErrMsg", "入力されたデータは存在しません");
				return "delete";
			}
			else {
				if(loginUser.getLoginId().equals(delForm.getLoginId())) {
					model.addAttribute("delErrMsg", "ログインユーザーは削除できません");
					return "delete";
				}else {
					model.addAttribute("deleteUser", deleteUser);
					return "deleteConfirm";
				}
			}
		}
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteResult(@ModelAttribute("delete") DeleteForm delForm, Model model) {
		userService.delete(delForm.getLoginId());
		return "deleteResult";
	}
}










