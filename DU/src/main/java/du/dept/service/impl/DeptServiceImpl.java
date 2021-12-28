package du.dept.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.dept.dao.DeptDAO;
import du.dept.domain.DeptVO;
import du.dept.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDAO deptDAO;

	@Override
	public List<DeptVO> selectDeptList() {
		return deptDAO.selectDeptList();
	}

}
