package cn.wheel.tiyuguanmanager.user.vo.validator;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.util.InfoCheckUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

public class UserUpdateVOValidator extends AbstractVOValidator {

	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_GENDER = "gender";
	public static final String PROPERTY_IDENTIFIER_TYPE = "id.type";
	public static final String PROPERTY_IDENTIFIER_NUMBER = "id.number";
	public static final String PROPERTY_REALNAME = "realname";
	public static final String PROPERTY_ROLE = "role";
	public static final String PROPERTY_ACCOUNT_TYPE = "account.type";
	public static final String PROPERTY_MOBILE = "mobile";
	public static final String PROPERTY_STUDENT_NUMBER = "stu.number";

	@Override
	public boolean validate(Object VOObject) throws VOTypeNotMatch {
		checkVOType(VOObject, UserVO.class);
		UserVO userVO = (UserVO) VOObject;

		// 用户名
		notEmpty(PROPERTY_USERNAME, userVO.getUsername(), "用户名不能为空");
		lengthRange(PROPERTY_USERNAME, userVO.getUsername(), 6, 16, "用户名长度必须为6到16位");

		// 密码
		if (userVO.getPassword() != null) {
			lengthRange(PROPERTY_PASSWORD, userVO.getPassword(), 6, 16, "密码长度必须为6到16位");
		}

		// 性别
		valueRange(PROPERTY_GENDER, userVO.getGender(), 0, 1, "性别值无效");

		// 账号类型
		valueRange(PROPERTY_ACCOUNT_TYPE, userVO.getAccountType(), 0, 2, "账号类型无效");

		int type = userVO.getAccountType();
		if (type == Constants.UserType.TYPE_TEACHER || type == Constants.UserType.TYPE_STUDENT) {
			// 证件类型
			valueRange(PROPERTY_IDENTIFIER_TYPE, userVO.getIdentifierType(), 1, 3, "证件类型无效");

			// 如果用户选择的证件类型是身份证，则对用户的身份证号码进行合法性校验
			if (userVO.getIdentifierType() == Constants.IdentifierType.TYPE_CITIZEN_ID) {
				if (!InfoCheckUtils.checkCitizenId(userVO.getIdentifierNumber())) {
					errorFlag = true;
					addErrorMessage(PROPERTY_IDENTIFIER_NUMBER, "身份账号码不合法");
				}
			}

			// 如果是学生还需要判断校验学号
			if (type == Constants.UserType.TYPE_STUDENT) {
				notEmpty(PROPERTY_STUDENT_NUMBER, userVO.getStudentNumber(), "学号不能为空");
				lengthRange(PROPERTY_STUDENT_NUMBER, userVO.getMobilePhone(), 11, 11, "手机号码长度有误");
			}
		}

		// 真实姓名
		notEmpty(PROPERTY_REALNAME, userVO.getRealname(), "真实姓名不能为空");

		// 手机号码不能为空，且长度为 11 位
		notEmpty(PROPERTY_MOBILE, userVO.getMobilePhone(), "手机号码不能为空");
		lengthRange(PROPERTY_MOBILE, userVO.getMobilePhone(), 11, 11, "手机号码长度有误");

		return !errorFlag;
	}
}
