package du.user.service;

import du.user.domain.UserVO;

public interface UserService {
	
	public boolean selectPwd(String userId, String userPwd) throws Exception;
	
	public UserVO selectUserInfo(String userId);

}
