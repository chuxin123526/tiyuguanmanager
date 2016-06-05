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

		// �û���
		notEmpty(PROPERTY_USERNAME, userVO.getUsername(), "�û�������Ϊ��");
		lengthRange(PROPERTY_USERNAME, userVO.getUsername(), 6, 16, "�û������ȱ���Ϊ6��16λ");

		// ����
		if (userVO.getPassword() != null) {
			lengthRange(PROPERTY_PASSWORD, userVO.getPassword(), 6, 16, "���볤�ȱ���Ϊ6��16λ");
		}

		// �Ա�
		valueRange(PROPERTY_GENDER, userVO.getGender(), 0, 1, "�Ա�ֵ��Ч");

		// �˺�����
		valueRange(PROPERTY_ACCOUNT_TYPE, userVO.getAccountType(), 0, 2, "�˺�������Ч");

		int type = userVO.getAccountType();
		if (type == Constants.UserType.TYPE_TEACHER || type == Constants.UserType.TYPE_STUDENT) {
			// ֤������
			valueRange(PROPERTY_IDENTIFIER_TYPE, userVO.getIdentifierType(), 1, 3, "֤��������Ч");

			// ����û�ѡ���֤�����������֤������û������֤������кϷ���У��
			if (userVO.getIdentifierType() == Constants.IdentifierType.TYPE_CITIZEN_ID) {
				if (!InfoCheckUtils.checkCitizenId(userVO.getIdentifierNumber())) {
					errorFlag = true;
					addErrorMessage(PROPERTY_IDENTIFIER_NUMBER, "����˺��벻�Ϸ�");
				}
			}

			// �����ѧ������Ҫ�ж�У��ѧ��
			if (type == Constants.UserType.TYPE_STUDENT) {
				notEmpty(PROPERTY_STUDENT_NUMBER, userVO.getStudentNumber(), "ѧ�Ų���Ϊ��");
				lengthRange(PROPERTY_STUDENT_NUMBER, userVO.getMobilePhone(), 11, 11, "�ֻ����볤������");
			}
		}

		// ��ʵ����
		notEmpty(PROPERTY_REALNAME, userVO.getRealname(), "��ʵ��������Ϊ��");

		// �ֻ����벻��Ϊ�գ��ҳ���Ϊ 11 λ
		notEmpty(PROPERTY_MOBILE, userVO.getMobilePhone(), "�ֻ����벻��Ϊ��");
		lengthRange(PROPERTY_MOBILE, userVO.getMobilePhone(), 11, 11, "�ֻ����볤������");

		return !errorFlag;
	}
}
