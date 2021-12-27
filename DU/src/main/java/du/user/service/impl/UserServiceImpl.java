package du.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import du.user.dao.UserDAO;
import du.user.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean isLogin(String userId, String userPwd) throws Exception {

		if (!StringUtils.hasText(userId) || !StringUtils.hasText(userPwd)) {
			throw new RuntimeException();
		}

		String pwd = userDAO.selectPwd(userId);

		if (userPwd.equals(pwd)) {
			return true;
		}
		
		return false;
	}

}
