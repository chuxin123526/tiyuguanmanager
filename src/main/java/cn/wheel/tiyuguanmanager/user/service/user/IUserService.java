package cn.wheel.tiyuguanmanager.user.service.user;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserForbiddenException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

public interface IUserService {
	/**
	 * 注册
	 * 
	 * @param userVO
	 *            注册表单
	 * @throws FormException
	 *             如果表单校验失败，则抛出这个异常
	 * @throws UserExistException
	 *             如果相同用户名的用户已经存在，则抛出这个异常
	 */
	public void register(UserVO userVO) throws FormException, UserExistException;

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 如果用户名和密码正确，则返回对应的 User 对象，否则返回 null
	 * @throws UserForbiddenException
	 *             如果当前登录的账号处于禁用状态，则会抛出这个异常
	 */
	public User login(String username, String password) throws UserForbiddenException;

	/**
	 * 变更用户的角色
	 * 
	 * @param user
	 *            要进行变更的用户实体
	 * @param newRole
	 *            新角色的名称
	 * @throws RoleNotFoundException
	 *             如果没有找到指定的角色则抛出这个异常
	 */
	public void updateUserRole(User user, String newRole) throws RoleNotFoundException;

	/**
	 * 变更用户的角色
	 * 
	 * @param userId
	 *            要进行角色变更的用户编号
	 * @param newRole
	 *            新的角色名称
	 * @throws UserNotExistException
	 *             如果找不到指定的用户，则抛出这个异常
	 * @throws RoleNotFoundException
	 *             如果找不到指定的角色，则抛出这个异常
	 */
	public void updateUserRole(long userId, String newRole) throws UserNotExistException, RoleNotFoundException;

	/**
	 * 变更用户的角色
	 * 
	 * @param userId
	 *            要进行变更的用户的编号
	 * @param roleId
	 *            新的角色编号
	 * @throws UserNotExistException
	 *             如果找不到指定的用户，则抛出这个异常
	 * @throws RoleNotFoundException
	 *             如果找不到指定的角色，则抛出这个异常
	 */
	public void updateUserRole(long userId, long roleId) throws UserNotExistException, RoleNotFoundException;

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param username
	 *            用户名
	 * @param accurate
	 *            是否精确匹配
	 * @return 返回满足查找条件的用户对象列表
	 */
	public List<User> findUserByUsername(String username, boolean accurate);

}
