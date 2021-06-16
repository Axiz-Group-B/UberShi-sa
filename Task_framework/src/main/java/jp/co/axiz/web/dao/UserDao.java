package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.User;

public interface UserDao {
	public User loginAuth(String loginId, String pass);
	public User findById(String loginId);

	public List<User> findAllUser();
	public List<User> findByCondition(String name, String tel);

	public void userRegister(User user);

	public void delete(String loginId);

	public void update(User user);

	public boolean isExistedLoginId(User user);
}
