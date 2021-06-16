package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User loginAuth(String loginId, String pass) {
		// TODO 自動生成されたメソッド・スタブ
		return userDao.loginAuth(loginId, pass);
	}

	@Override
	public User findById(String loginId) {
		// TODO 自動生成されたメソッド・スタブ
		return userDao.findById(loginId);
	}

	@Override
	public List<User> findByCondition(String name, String tel) {
		// TODO 自動生成されたメソッド・スタブ
		return userDao.findByCondition(name, tel);
	}

	@Override
	public void userRegister(User user) {
		// TODO 自動生成されたメソッド・スタブ
		userDao.userRegister(user);
	}

	@Override
	public void delete(String loginId) {
		// TODO 自動生成されたメソッド・スタブ
		userDao.delete(loginId);
	}

	@Override
	public void update(User user) {
		// TODO 自動生成されたメソッド・スタブ
		userDao.update(user);
	}

	@Override
	public boolean isExistedLoginId(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return userDao.isExistedLoginId(user);
	}

}
