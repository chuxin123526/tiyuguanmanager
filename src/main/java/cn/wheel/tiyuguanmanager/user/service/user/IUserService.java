package cn.wheel.tiyuguanmanager.user.service.user;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserForbiddenException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryVO;
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

	/**
	 * 在用户管理页面中添加一个用户
	 * 
	 * @param vo
	 *            添加用户表单
	 */
	public void insertUser(UserVO vo) throws FormException, UserExistException, RoleNotFoundException;

	/**
	 * 根据表单中的数据查询用户数据
	 * 
	 * @param queryVO
	 *            含有查询的
	 * @return 含有相关信息的查询结果对象
	 */
	public UserQueryResult queryUser(UserQueryVO queryVO);

	/**
	 * 根据用户编号停用账户
	 * 
	 * @param userId
	 *            用户编号
	 * @throws UserNotExistException
	 *             如果指定用户编号的用户不存在，则抛出这个异常
	 */
	public void forbidUserAccount(long userId) throws UserNotExistException;

	/**
	 * 根据用户编号启用账号
	 * 
	 * @param userId
	 *            用户编号
	 * @throws UserNotExistException
	 *             如果指定用户编号的用户不存在，则抛出这个异常
	 */
	public void enableUserAccount(long userId) throws UserNotExistException;

	/**
	 * 根据用户编号查找用户 po 对象
	 * 
	 * @param userId
	 *            用户编号
	 * @return 如果数据库中存在相应的用户编号，则返回相应的用户对象，否则返回 null
	 */
	public User findUserById(long userId);

	/**
	 * 变更用户信息
	 * 
	 * @param updateVO
	 *            含有新信息的表单对象
	 * 
	 * @throws UserNotExistException
	 *             如果表单中的用户编号无效，则会抛出这个异常
	 * @throws UserExistException
	 *             如果新的用户名跟系统中现有的用户同名，则会抛出这个异常
	 * @throws FormException
	 *             如果表单校验失败，则会抛出这个异常
	 * @throws RoleNotFoundException
	 *             如果表单中指定的角色编号无效，则会抛出这个异常
	 */
	public void updateUser(UserVO updateVO) throws UserNotExistException, UserExistException, FormException, RoleNotFoundException;

	/**
	 * 用户信息认证或者撤销用户认证
	 * 
	 * @param userId
	 *            要进行用户信息认证的用户编号
	 * @param pass
	 *            是否认证通过
	 * @throws UserNotExistException
	 *             如果数据库中不存在指定用户编号的用户，则抛出这个异常
	 */
	public void checkUser(long userId, boolean pass) throws UserNotExistException;
}
