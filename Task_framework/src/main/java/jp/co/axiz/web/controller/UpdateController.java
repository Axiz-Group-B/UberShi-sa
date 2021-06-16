package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.axiz.web.controller.form.RePassUpdateForm;
import jp.co.axiz.web.controller.form.UpdateForm;
import jp.co.axiz.web.controller.form.UpdateInputForm;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;
import jp.co.axiz.web.util.ParamUtil;

@Controller
public class UpdateController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;

	@GetMapping(value="/update")
	public String update(@ModelAttribute("update") UpdateForm updateForm, Model model) {
		return "update";
	}

	@GetMapping(value="/updateInput")
	public String updateInput(@ModelAttribute("updateInput") UpdateInputForm updateInputForm, Model model) {
		return "updateInput";
	}

	@PostMapping(value="/updateInput")
	public String updateInput(@Validated @ModelAttribute("update") UpdateForm updateForm,
			BindingResult bindingResult,
			@ModelAttribute("updateInput") UpdateInputForm updateInputForm, Model model) {
		if(bindingResult.hasErrors()) {
			return "update";
		}
		else {
			User user = userService.findById(updateForm.getLoginId());
			if(user == null) {
				model.addAttribute("updateErrMsg", "入力されたデータは存在しません");
				return "update";
			}
			else {
				session.setAttribute("updateUser", user);
				return "updateInput";
			}
		}

	}
	@PostMapping(value="/updateConfirm")
	public String updateConifrm(@Validated @ModelAttribute("updateInput") UpdateInputForm updateInputForm,
			BindingResult bindingResult, @ModelAttribute("updateConfirm") RePassUpdateForm rePassForm,
			@ModelAttribute("update") UpdateForm updateForm,
			Model model) {
		if(bindingResult.hasErrors()) {
			return "updateInput";
		}
		else {
			User preUpdateUser = userService.findById(updateForm.getLoginId());
			System.out.println("2" + preUpdateUser);
			User updatedUser = new User(preUpdateUser.getUserId(), updateInputForm.getLoginId(), updateInputForm.getUserName(),
					updateInputForm.getTelephone(), updateInputForm.getPassword(),
					updateInputForm.getRoleId());
//			System.out.println("3" + updatedUser);
//			System.out.println(ParamUtil.compareTwoUsers(preUpdateUser, updatedUser));
			if(ParamUtil.compareTwoUsers(preUpdateUser, updatedUser)) {
//				System.out.println("vaoday1");
				model.addAttribute("updateInputErrMsg", "1項目以上変更してください");
				return "updateInput";
			}
			else {
//				System.out.println("vaoday2");
				if(userService.isExistedLoginId(updatedUser)) {
					model.addAttribute("updateInputErrMsg", "IDが重複しています");
					return "updateInput";
				}
				else {
					session.setAttribute("updatedUser", updatedUser);
					return "updateConfirm";
				}
			}

		}
	}

	@PostMapping(value="/update")
	public String updateConfirm(@Validated @ModelAttribute("updateConfirm") RePassUpdateForm rePassForm,
			BindingResult bindingResult,
			@ModelAttribute("updateInput") UpdateInputForm updateInputForm,
			 Model model) {
		if(bindingResult.hasErrors()) {
			return "updateConfirm";
		}
		else {
			User updateUser = (User) session.getAttribute("updatedUser");
			System.out.println(updateUser.getUserId());
			if(rePassForm.getRePass().equals(updateUser.getPassword())) {
				userService.update(updateUser);
				return "updateResult";
			}
			else {

				model.addAttribute("updateConfirmErrMsg", "前画面で入力されたパスワードと一致しません");
				return "updateConfirm";
			}


		}

	}

}












