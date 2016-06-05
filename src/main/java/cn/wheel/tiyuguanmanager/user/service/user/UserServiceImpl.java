package cn.wheel.tiyuguanmanager.user.service.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.dao.criteria.RoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserAccountTypeCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserForbiddenExcludeCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserIdCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserPasswordCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserRoleIdCriteria;
import cn.wheel.tiyuguanmanager.user.dao.role.IRoleDao;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserForbiddenException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.Contract;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MessageDigestUtils;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
import cn.wheel.tiyuguanmanager.user.util.StringUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryShowback;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryVO;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserInsertVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserRegisterVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.UserUpdateVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Resource
	private IRoleDao roleDao;

	private Role findRegisteredRole() {
		List<Role> list = roleDao.find(new DaoCriteria[] { new RoleNameCriteria("ע���û�") });
		Role retValue = null;
		if (list.size() == 0) {
			Role role = new Role();
			role.setName("ע���û�");
			roleDao.insert(role);

			retValue = role;
		} else {
			retValue = list.get(0);
		}

		return retValue;
	}

	private Role findVerifiedRole() {
		List<Role> list = roleDao.find(new DaoCriteria[] { new RoleNameCriteria("��֤�û�") });
		Role retValue = null;
		if (list.size() == 0) {
			Role role = new Role();
			role.setName("��֤�û�");
			roleDao.insert(role);

			retValue = role;
		} else {
			retValue = list.get(0);
		}

		return retValue;
	}

	@Transactional
	@Override
	public void register(UserVO userVO) throws FormException, UserExistException {
		// 1. ��У��
		UserRegisterVOValidator validator = new UserRegisterVOValidator();
		boolean validated = true;

		try {
			validated = validator.validate(userVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 2. ��ѯ�û��Ƿ��Ѿ�����
		List<User> list = userDao.find(new DaoCriteria[] { new UserNameCriteria(userVO.getUsername(), true) });
		if (list.size() > 0) {
			throw new UserExistException();
		}

		// 3. ����ע���û���
		Role role = findRegisteredRole();

		// 4. ��װ PO ����
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

		// 5. ��ӵ����ݿ⵱��
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
		// 1. У�������
		UserInsertVOValidator validator = new UserInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(userVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 2. �ж��Ƿ���ͬ���û�����
		List<User> userList = userDao.find(new DaoCriteria[] { new UserNameCriteria(userVO.getUsername(), true) });
		if (userList.size() > 0) {
			throw new UserExistException();
		}

		// 3. �жϽ�ɫ�Ƿ����
		Role role = roleDao.findById(userVO.getRoleId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		// 4. ��װ PO
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

		// 5. �����־ò�д�����ݿ�
		userDao.insert(user);
	}

	@Transactional
	@Override
	public UserQueryResult queryUser(UserQueryVO queryVO) {
		boolean findAll = false;
		UserQueryResult result = new UserQueryResult();
		UserQueryShowback showback = new UserQueryShowback();

		// 1. ��ȡ�����ݣ���װ��ѯ����
		List<DaoCriteria> daoCriterias = new ArrayList<>();
		int[] criteria = queryVO.getCriteria();
		if (criteria == null || criteria.length == 0) {
			findAll = true;

			showback.setNameIncluded(false);
			showback.setRoleIncluded(false);
			showback.setAccountTypeIncluded(false);
		} else {
			for (int i : criteria) {
				if (i == 0) {
					// �û�����ѯ
					String usernameStr = queryVO.getUsername();
					if (usernameStr != null && !"".equals(usernameStr.trim())) {
						DaoCriteria usernameCriteria = new UserNameCriteria(queryVO.getUsername(), false);
						daoCriterias.add(usernameCriteria);

						showback.setUsername(usernameStr);
						showback.setNameIncluded(true);
					}
				} else if (i == 1) {
					// ��ɫ��ѯ
					DaoCriteria roleIdCriteria = new UserRoleIdCriteria(queryVO.getRoleId());
					daoCriterias.add(roleIdCriteria);

					showback.setRoleId(queryVO.getRoleId());
					showback.setRoleIncluded(true);
				} else if (i == 2) {
					boolean student = false, teacher = false, employee = false;

					// �˺�����
					for (int type : queryVO.getAccountType()) {
						switch (type) {
						case 0:
							showback.setAccountTypeIncluded(true);
							showback.setTypeStudentIncluded(true);
							student = true;
							break;
						case 1:
							showback.setAccountTypeIncluded(true);
							showback.setTypeEmployeeIncluded(true);
							employee = true;
							break;
						case 2:
							showback.setAccountTypeIncluded(true);
							showback.setTypeTeacherIncluded(true);
							teacher = true;
							break;
						}
					}

					DaoCriteria typeCriteria = new UserAccountTypeCriteria(student, teacher, employee);
					daoCriterias.add(typeCriteria);
				}
			}
		}

		if (queryVO.getForbidden() == 0) {
			DaoCriteria forbidden = new UserForbiddenExcludeCriteria(true);
			daoCriterias.add(forbidden);

			findAll = false;
			showback.setForbiddenIncluded(true);
		} else {
			showback.setForbiddenIncluded(false);
		}

		DaoCriteria[] daoCriteriasArray = new DaoCriteria[daoCriterias.size()];
		for (int i = 0; i < daoCriterias.size(); i++) {
			daoCriteriasArray[i] = daoCriterias.get(i);
		}

		showback.setPage(queryVO.getPage());
		result.setShowback(showback);

		// 2. ��ѯ����
		long totalCount = 0;
		if (!findAll) {
			totalCount = userDao.count(daoCriteriasArray);
		} else {
			totalCount = userDao.count();
		}

		result.setTotalCount(totalCount);

		// 3. �����ҳ����
		int maxPage = PagingUtils.getMaxPage((int) totalCount, Constants.ITEM_PER_PAGE);
		result.setMaxPage(maxPage);

		// 4. ��ѯ
		List<User> users = null;
		if (!findAll) {
			users = userDao.find(daoCriteriasArray, PagingUtils.calcFirstOffset(queryVO.getPage(), Constants.ITEM_PER_PAGE), Constants.ITEM_PER_PAGE);
		} else {
			users = userDao.find(null, PagingUtils.calcFirstOffset(queryVO.getPage(), Constants.ITEM_PER_PAGE), Constants.ITEM_PER_PAGE);
		}
		result.setCurrentPage(queryVO.getPage());
		result.setCurrentPageItem(users.size());
		result.setResult(users);

		return result;
	}

	@Transactional
	@Override
	public void forbidUserAccount(long userId) throws UserNotExistException {
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		user.setStatus(Constants.UserStatus.DISABLED);
		userDao.update(user);
	}

	@Transactional
	@Override
	public void enableUserAccount(long userId) throws UserNotExistException {
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		user.setStatus(Constants.UserStatus.NORMAL);
		userDao.update(user);
	}

	@Transactional
	@Override
	public User findUserById(long userId) {
		return userDao.findById(userId);
	}

	@Transactional
	@Override
	public void updateUser(UserVO updateVO) throws UserNotExistException, UserExistException, FormException, RoleNotFoundException {
		// 1. У���
		UserUpdateVOValidator validator = new UserUpdateVOValidator();
		boolean validated = false;
		try {
			validated = validator.validate(updateVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. �����ݿ���ȡ���û�����
		User user = userDao.findById(updateVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. �жϱ���ָ�����û�����Ƿ���Ч
		Role role = roleDao.findById(updateVO.getRoleId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		// 4. �ж��µ��û����Ƿ��������û�ͬ��
		List<User> existUser = userDao.find(new DaoCriteria[] { new UserNameCriteria(updateVO.getUsername(), true),
				new UserIdCriteria(false, updateVO.getUserId()) });
		if (existUser.size() > 0) {
			throw new UserExistException();
		}

		// 5. ����������䵽������
		user.setUsername(updateVO.getUsername());

		if (updateVO.getPassword() != null) {
			user.setPassword(MessageDigestUtils.md5_32(updateVO.getPassword()));
		}

		user.setGender(updateVO.getGender());
		user.setRole(role);
		user.setType(updateVO.getAccountType());
		user.setIdentifierType(updateVO.getIdentifierType());
		user.setIdentifierNumber(updateVO.getIdentifierNumber());
		user.setRealname(updateVO.getRealname());
		user.setStudentNumber(updateVO.getStudentNumber());

		user.getContracts().clear();
		Contract contract = new Contract();
		contract.setType(Constants.ContratType.TYPE_MOBILE);
		contract.setContent(updateVO.getMobilePhone());
		user.getContracts().add(contract);

		userDao.update(user);
	}

	@Transactional
	@Override
	public void checkUser(long userId, boolean pass) throws UserNotExistException {
		// 1. �ж��û�����Ƿ���Ч
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		// 2. ������֤���ǳ�����֤���û��Ľ�ɫ��Ϣ���б��
		if (pass) {
			user.setRole(this.findVerifiedRole());
		} else {
			user.setRole(this.findRegisteredRole());
		}

		// 3. �ѱ��д�뵽���ݿ�
		userDao.update(user);
	}
}
