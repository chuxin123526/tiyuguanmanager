package cn.wheel.tiyuguanmanager.user.service.user;

import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

public interface IUserService {
	public void register(UserVO userVO) throws FormException,
			UserExistException;

	public User login(String username, String password);
}
