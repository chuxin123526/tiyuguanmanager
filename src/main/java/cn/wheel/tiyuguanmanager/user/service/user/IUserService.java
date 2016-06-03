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
	 * ע��
	 * 
	 * @param userVO
	 *            ע���
	 * @throws FormException
	 *             �����У��ʧ�ܣ����׳�����쳣
	 * @throws UserExistException
	 *             �����ͬ�û������û��Ѿ����ڣ����׳�����쳣
	 */
	public void register(UserVO userVO) throws FormException, UserExistException;

	/**
	 * ��¼
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return ����û�����������ȷ���򷵻ض�Ӧ�� User ���󣬷��򷵻� null
	 * @throws UserForbiddenException
	 *             �����ǰ��¼���˺Ŵ��ڽ���״̬������׳�����쳣
	 */
	public User login(String username, String password) throws UserForbiddenException;

	/**
	 * ����û��Ľ�ɫ
	 * 
	 * @param user
	 *            Ҫ���б�����û�ʵ��
	 * @param newRole
	 *            �½�ɫ������
	 * @throws RoleNotFoundException
	 *             ���û���ҵ�ָ���Ľ�ɫ���׳�����쳣
	 */
	public void updateUserRole(User user, String newRole) throws RoleNotFoundException;

	/**
	 * ����û��Ľ�ɫ
	 * 
	 * @param userId
	 *            Ҫ���н�ɫ������û����
	 * @param newRole
	 *            �µĽ�ɫ����
	 * @throws UserNotExistException
	 *             ����Ҳ���ָ�����û������׳�����쳣
	 * @throws RoleNotFoundException
	 *             ����Ҳ���ָ���Ľ�ɫ�����׳�����쳣
	 */
	public void updateUserRole(long userId, String newRole) throws UserNotExistException, RoleNotFoundException;

	/**
	 * ����û��Ľ�ɫ
	 * 
	 * @param userId
	 *            Ҫ���б�����û��ı��
	 * @param roleId
	 *            �µĽ�ɫ���
	 * @throws UserNotExistException
	 *             ����Ҳ���ָ�����û������׳�����쳣
	 * @throws RoleNotFoundException
	 *             ����Ҳ���ָ���Ľ�ɫ�����׳�����쳣
	 */
	public void updateUserRole(long userId, long roleId) throws UserNotExistException, RoleNotFoundException;

	/**
	 * �����û��������û���Ϣ
	 * 
	 * @param username
	 *            �û���
	 * @param accurate
	 *            �Ƿ�ȷƥ��
	 * @return ������������������û������б�
	 */
	public List<User> findUserByUsername(String username, boolean accurate);

}
