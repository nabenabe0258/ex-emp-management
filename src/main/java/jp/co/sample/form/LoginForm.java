package jp.co.sample.form;

/**
 * LoginFormを表すクラスです.
 * 
 * @author yuma.watanabe
 *
 */
public class LoginForm {

	/**
	 * ログイン時入力するメールアドレス
	 */
	private String mailAddress;
	/**
	 *　ログイン時入力するパスワード
	 */
	private String password;

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}

}
