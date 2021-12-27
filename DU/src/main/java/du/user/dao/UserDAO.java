package du.user.dao;

import du.user.domain.UserVO;

public interface UserDAO {

	public String selectPwd(String userId);

	public UserVO selectUserInfo(String userId);

}
