package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;

/**
 * 従業員のRepositoryを表すクラスです.
 * 
 * @author yuma.watanabe
 */
@Repository
@RequestMapping("/administrator_repository")
public class AdministratorRepository {

	/**
	 *　データベース操作を表す定数
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Administrator> ADMINISTRATOR_LOW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * 管理者情報の登録.
	 * 
	 * @param administrator 管理者情報 
	 */
	@RequestMapping("/insert")
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrators(name,mail_address,password)VALUES(:name,:mailAddress,:password)";
		template.update(insertSql, param);
	}

	/**
	 * 管理者情報の１件検索
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return 管理者情報(もし存在しなければnullが返る)
	 */
	@RequestMapping("/findby")
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String findbySql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		List<Administrator> administratorList = template.query(findbySql, param, ADMINISTRATOR_LOW_MAPPER);
		if (administratorList.size()==0) {
			return null;

		} else {
			return administratorList.get(0);
		}
	}
}
