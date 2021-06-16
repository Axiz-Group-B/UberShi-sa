package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.RoleDao;
import jp.co.axiz.web.entity.Role;
import jp.co.axiz.web.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> selectAllRole() {
		// TODO 自動生成されたメソッド・スタブ
		return roleDao.selectAllRole();
	}

}
