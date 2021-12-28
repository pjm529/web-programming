package du.dept.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import du.dept.dao.DeptDAO;
import du.dept.domain.DeptVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class DeptDAOImpl extends EgovAbstractMapper implements DeptDAO {

	@Override
	public List<DeptVO> selectDeptList() {
		return selectList("DeptDAO.selectDeptList");
	}

	@Override
	public void insertDept(DeptVO dept) {
		insert("DeptDAO.insertDept", dept);
	}

}
