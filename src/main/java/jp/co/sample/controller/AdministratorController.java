package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラ.
 * 
 * @author yuma.watanabe
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService service;
	@Autowired
    private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面に遷移.
	 * 
	 * @return login画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * 登録フォームを返すメソッド.
	 * 
	 * @return 登録フォーム
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 *　管理者情報の挿入
	 * 
	 * @param form 管理者フォーム
	 * @return　ログイン画面(リダイレクト)
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		service.insert(administrator);
		return "redirect:/";
	}
	/**
	 * ログインの可否
	 * 
	 * @param form 管理者情報
	 * @param model　
	 * @return　ログイン失敗画面または管理者情報一覧
	 */
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
	Administrator administrator = service.login(form.getMailAddress(),form.getPassword());
		if(administrator==null) {
			model.addAttribute("result","メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}else {
			session.setAttribute("AdministratorName",administrator);
			return "forward:/employee/list";
		}
	}

}
