package jp.co.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuma.watanabe 
 * 管理者の情報を操作するServiceクラスです
 */
@Transactional
@Service
@RequestMapping("/administrator_service")
public class AdministratorService {

}
