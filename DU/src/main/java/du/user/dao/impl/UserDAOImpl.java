package du.user.dao.impl;

import org.springframework.stereotype.Repository;

import du.user.dao.UserDAO;
import du.user.domain.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class UserDAOImpl extends EgovAbstractMapper implements UserDAO {

	@Override
	public String selectPwd(String userId) {
		return selectOne("userDAO.selectPwd", userId);
	}
	
	@Override
	public UserVO selectUserInfo(String userId) {
		return selectOne("userDAO.selectUserInfo", userId);
	}

	@Override
	public void insertUser(UserVO user) {
		insert("userDAO.insertUser", user);
	}

	@Override
	public void updateUser(UserVO user) {
		update("userDAO.updateUser", user);
	}

}
