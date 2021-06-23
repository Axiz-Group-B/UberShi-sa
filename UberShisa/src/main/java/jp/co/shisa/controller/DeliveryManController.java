package jp.co.shisa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.service.DeliveryManService;

@Controller
@EnableAutoConfiguration
public class DeliveryManController {
	@Autowired
	private DeliveryManService deliveryManService;

	//signup.htmlでアカウントを持っている方を押した時と新規ユーザー登録したときにindex画面に遷移
	@RequestMapping("/newInsert")
	public String index(@ModelAttribute("insert") LoginForm form) {
		return "signup";
	}
	//バリデーションどこだっけ

	//signup.htmlでパスワードと確認パスワードが間違ってた時とポップアップで戻る押した時にsignup.htmlに戻る
	@RequestMapping("/signup")
	public String insertDeliveryMan(@ModelAttribute("insert") SignupForm form, Model model) {
		if (form.getPass().equals(form.getRePass())) {
			deliveryManService.insertUserInfo(form);
			deliveryManService.insertDeliveryMan(form);
			return "index";

		} else {
			//passとrepassが同じじゃなかったらエラーメッセージ
			model.addAttribute("errorPassMsg", "パスワードと確認パスワードが一致しません");
			return "signup";

		}
		//ポップアップで戻る選択した時
	}


}
