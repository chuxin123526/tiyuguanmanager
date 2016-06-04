package cn.wheel.tiyuguanmanager.user.service.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserForbiddenException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.Contract;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MessageDigestUtils;
import cn.wheel.tiyuguanmanager.user.util.StringUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserInsertVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserRegisterVOValidator;
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
			List<Role> list = roleDao.find(new DaoCriteria[] { new RoleNameCriteria("注册用户") });
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
	public void register(UserVO userVO) throws FormException, UserExistException {
		// 1. 表单校验
		UserRegisterVOValidator validator = new UserRegisterVOValidator();
		boolean validated = true;

		try {
			validated = validator.validate(userVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 2. 查询用户是否已经存在
		List<User> list = userDao.find(new DaoCriteria[] { new UserNameCriteria(userVO.getUsername(), true) });
		if (list.size() > 0) {
			throw new UserExistException();
		}

		// 3. 查找注册用户组
		Role role = findRegisteredRole();

		// 4. 组装 PO 对象
		User user = new User();
		user.setGender(userVO.getGender());
		user.setIdentifierNumber(userVO.getIdentifierNumber());
		user.setIdentifierType(userVO.getIdentifierType());
		user.setPassword(MessageDigestUtils.md5_32(userVO.getPassword()));
		user.setRealname(userVO.getRealname());
		user.setRole(role);
		user.setStudentNumber(userVO.getStudentNumber());
		user.setUsername(userVO.getUsername());
		user.setStatus(Constants.UserStatus.NORMAL);
		user.setType(Constants.UserType.TYPE_STUDENT);

		String mobilePhone = userVO.getMobilePhone();
		if (!StringUtils.isEmpty(mobilePhone)) {
			Contract mobile = new Contract();
			mobile.setContent(mobilePhone);
			mobile.setType(Constants.ContratType.TYPE_MOBILE);

			Set<Contract> contacts = new HashSet<>();
			contacts.add(mobile);
			user.setContracts(contacts);
		}

		// 5. 添加到数据库当中
		userDao.insert(user);
	}

	@Transactional
	@Override
	public User login(String username, String password) throws UserForbiddenException {
		if (username == null || password == null) {
			return null;
		}

		List<User> list = userDao
				.find(new DaoCriteria[] { new UserNameCriteria(username, true), new UserPasswordCriteria(MessageDigestUtils.md5_32(password)) });

		if (list.size() > 0) {
			User user = list.get(0);
			user.getRole();

			if (user.getStatus() == Constants.UserStatus.DISABLED) {
				throw new UserForbiddenException();
			}

			return user;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public void updateUserRole(User user, String newRole) throws RoleNotFoundException {
		List<Role> list = this.roleDao.find(new DaoCriteria[] { new RoleNameCriteria(newRole) });
		if (list.size() == 0) {
			throw new RoleNotFoundException();
		}

		Role role = list.get(0);
		user.setRole(role);

		this.userDao.update(user);
	}

	@Transactional
	@Override
	public List<User> findUserByUsername(String username, boolean accurate) {
		return this.userDao.find(new DaoCriteria[] { new UserNameCriteria(username, accurate) });
	}

	@Transactional
	@Override
	public void updateUserRole(long userId, String newRole) throws UserNotExistException, RoleNotFoundException {
		User user = this.userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		List<Role> roleList = this.roleDao.find(new DaoCriteria[] { new RoleNameCriteria(newRole) });
		if (roleList.size() == 0) {
			throw new RoleNotFoundException();
		}

		user.setRole(roleList.get(0));
		this.userDao.update(user);
	}

	@Transactional
	@Override
	public void updateUserRole(long userId, long roleId) throws UserNotExistException, RoleNotFoundException {
		User user = this.userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		Role role = this.roleDao.findById(roleId);
		if (role == null) {
			throw new RoleNotFoundException();
		}

		user.setRole(role);
		this.userDao.update(user);
	}

	@Transactional
	@Override
	public void insertUser(UserVO userVO) throws FormException, UserExistException, RoleNotFoundException {
		// 1. 校验表单数据
		UserInsertVOValidator validator = new UserInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(userVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 2. 判断是否有同名用户存在
		List<User> userList = userDao.find(new DaoCriteria[] { new UserNameCriteria(userVO.getUsername(), true) });
		if (userList.size() > 0) {
			throw new UserExistException();
		}

		// 3. 判断角色是否存在
		Role role = roleDao.findById(userVO.getRoleId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		// 4. 组装 PO
		User user = new User();
		user.setGender(userVO.getGender());
		user.setIdentifierNumber(userVO.getIdentifierNumber());
		user.setIdentifierType(userVO.getIdentifierType());
		user.setPassword(MessageDigestUtils.md5_32(userVO.getPassword()));
		user.setRealname(userVO.getRealname());
		user.setRole(role);
		user.setStudentNumber(userVO.getStudentNumber());
		user.setUsername(userVO.getUsername());
		user.setStatus(Constants.UserStatus.NORMAL);
		user.setType(userVO.getAccountType());

		// 5. 交给持久层写入数据库
		userDao.insert(user);
	}
}
