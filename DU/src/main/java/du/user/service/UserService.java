package du.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import du.user.domain.UserVO;

public interface UserService {
	
	public boolean selectPwd(String userId, String userPwd);
	
	public UserVO selectUserInfo(String userId);
	
	public Boolean loginProcess(HttpServletRequest request, UserVO user);
	
	public void insertUser(UserVO user);
	
	public void updateUser(UserVO user);
	
	public void deleteUser(HttpSession session);
}
