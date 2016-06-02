package cn.wheel.tiyuguanmanager.user.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.RoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserPasswordCriteria;
import cn.wheel.tiyuguanmanager.user.dao.role.IRoleDao;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MessageDigestUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Resource
	private IRoleDao roleDao;

	private Role registeredRole;

	private Role findRegisteredRole() {
		if (registeredRole == null) {
			List<Role> list = roleDao
					.findByCriteria(new DaoCriteria[] { new RoleNameCriteria(
							"注册用户") });
			if (list.size() == 0) {
				Role role = new Role();
				role.setName("注册用户");
				roleDao.insert(role);

				registeredRole = role;
			} else {
				registeredRole = list.get(0);
			}
		}

		return registeredRole;
	}

	@Transactional
	@Override
	public void register(UserVO userVO) throws FormException,
			UserExistException {
		// 1. 表单校验
		UserVOValidator validator = new UserVOValidator();
		boolean validated = true;

		try {
			validated = validator.validate(userVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 2. 查询用户是否已经存在
		List<User> list = userDao
				.findByCriteria(new DaoCriteria[] { new UserNameCriteria(userVO
						.getUsername()) });
		if (list.size() > 0) {
			throw new UserExistException();
		}

		// 3. 查找注册用户组
		Role role = findRegisteredRole();

		// 4. 组装 PO 对象
		User user = new User();
		user.setContracts(userVO.getContracts());
		user.setGender(userVO.getGender());
		user.setIdentifierNumber(userVO.getIdentifierNumber());
		user.setIdentifierType(userVO.getIdentifierType());
		user.setPassword(MessageDigestUtils.md5_32(userVO.getPassword()));
		user.setRealname(userVO.getRealname());
		user.setRole(role);
		user.setStudentNumber(userVO.getStudentNumber());
		user.setUsername(userVO.getUsername());
		user.setStatus(Constants.UserStatus.NORMAL);

		// 5. 添加到数据库当中
		userDao.insert(user);
	}

	@Transactional
	@Override
	public User login(String username, String password) {
		if (username == null || password == null) {
			return null;
		}

		List<User> list = userDao
				.findByCriteria(new DaoCriteria[] {
						new UserNameCriteria(username),
						new UserPasswordCriteria(MessageDigestUtils
								.md5_32(password)) });

		if (list.size() > 0) {
			User user = list.get(0);
			user.getRole();
			return user;
		} else {
			return null;
		}
	}
}
