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

@Repository
@RequestMapping("/administrator")
public class AdministratorRepository {

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

	@RequestMapping("/insert")
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrator(name,mail_address,password)VALUES(:name,:mailAddress,:password)";
		template.update(insertSql, param);
	}

	@RequestMapping("/findby")
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String findbySql = "SELECT id,name,mail_address,password FROM administrator WHERE mail_address=:mailAddress password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		List<Administrator> administratorList = template.query(findbySql, param, ADMINISTRATOR_LOW_MAPPER);
		if (administratorList.get(0) == null) {
			return null;

		} else {
			return administratorList.get(0);
		}
	}
}
