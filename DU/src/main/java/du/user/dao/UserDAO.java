package du.user.dao;

import du.user.domain.UserVO;

public interface UserDAO {
	
	public UserVO selectUserInfo(String userId);
	
}
