package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.User;

public interface UserService {
	public User loginAuth(String loginId, String pass);
	public User findById(String loginId);
	public List<User> findByCondition(String name, String tel);
	public void userRegister(User user);
	public void delete(String loginId);
	public void update(User user);
	public boolean isExistedLoginId(User user);
}

