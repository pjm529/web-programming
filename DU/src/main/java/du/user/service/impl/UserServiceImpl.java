package du.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import du.user.dao.UserDAO;
import du.user.domain.UserVO;
import du.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean selectPwd(String userId, String userPwd) throws Exception {

		if (!StringUtils.hasText(userId) || !StringUtils.hasText(userPwd)) {
			throw new RuntimeException();
		}

		String pwd = userDAO.selectPwd(userId);

		if (userPwd.equals(pwd)) {
			return true;
		}
		
		return false;
	}

	@Override
	public UserVO selectUserInfo(String userId) {
		return userDAO.selectUserInfo(userId);
	}

}
