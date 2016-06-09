package cn.wheel.tiyuguanmanager.user.vo.validator;

import cn.wheel.tiyuguanmanager.common.vo.validator.AbstractVOValidator;
import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.util.InfoCheckUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

/**
 * 用于对注册表单进行校验的校验类
 * 
 * @author DENG YURONG
 * 
 */
public class UserRegisterVOValidator extends AbstractVOValidator {

	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_GENDER = "gender";
	public static final String PROPERTY_IDENTIFIER_TYPE = "id.type";
	public static final String PROPERTY_IDENTIFIER_NUMBER = "id.number";
	public static final String PROPERTY_STUDENT_NUMBER = "stu.number";
	public static final String PROPERTY_REALNAME = "realname";

	@Override
	public boolean validate(Object VOObject) throws VOTypeNotMatch {
		checkVOType(VOObject, UserVO.class);
		UserVO userVO = (UserVO) VOObject;

		// 用户名
		notEmpty(PROPERTY_USERNAME, userVO.getUsername(), "用户名不能为空");
		lengthRange(PROPERTY_USERNAME, userVO.getUsername(), 6, 16, "用户名长度必须为6到16位");

		// 密码
		notEmpty(PROPERTY_PASSWORD, userVO.getPassword(), "密码不能为空");
		lengthRange(PROPERTY_PASSWORD, userVO.getPassword(), 6, 16, "密码长度必须为6到16位");

		// 性别
		valueRange(PROPERTY_GENDER, userVO.getGender(), 0, 1, "性别值无效");

		// 证件类型
		valueRange(PROPERTY_IDENTIFIER_TYPE, userVO.getIdentifierType(), 1, 3, "证件类型无效");

		// 如果用户选择的证件类型是身份证，则对用户的身份证号码进行合法性校验
		if (userVO.getIdentifierType() == UserConstants.IdentifierType.TYPE_CITIZEN_ID) {
			if (!InfoCheckUtils.checkCitizenId(userVO.getIdentifierNumber())) {
				errorFlag = true;
				addErrorMessage(PROPERTY_IDENTIFIER_NUMBER, "身份账号码不合法");
			}
		}

		// 学号
		lengthRange(PROPERTY_STUDENT_NUMBER, userVO.getStudentNumber(), 12, 12, "学号长度必须为12位");

		// 真实姓名
		notEmpty(PROPERTY_REALNAME, userVO.getRealname(), "真实姓名不能为空");

		return !errorFlag;
	}
}
