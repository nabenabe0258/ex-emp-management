package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者の情報を操作するServiceクラスです.
 * 
 * @author yuma.watanabe 
 */
@Transactional
@Service
public class AdministratorService {

	/**
	 * AdministratorRepositoryオブジェクトの参照を注入.
	 */
	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param administrator　管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理をする.
	 * 
	 * @param mailAddress 管理者のメールアドレス
	 * @param password　管理者のパスワード
	 * @return　管理者情報
	 */
	public Administrator login(String mailAddress,String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress,password);
	}

}
