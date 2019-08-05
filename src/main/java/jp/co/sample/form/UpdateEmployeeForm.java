package jp.co.sample.form;

/**
 * 従業員情報を受け取るFormクラス.
 *  
 * @author yuma.watanabe
 */
public class UpdateEmployeeForm {
	/**
	 * 従業員のid
	 */
	private String id;
	/**
	 * 従業員の扶養人数
	 */
	private String dependentsCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

}
