package cn.wheel.tiyuguanmanager.user.vo.validator;

import java.util.HashMap;
import java.util.Map;

import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

public abstract class AbstractVOValidator implements VOValidator {

	private Map<String, String> errMsgs;
	protected boolean errorFlag = false;

	public AbstractVOValidator() {
		errMsgs = new HashMap<>();
	}

	public void addErrorMessage(String property, String errorMessage) {
		this.errMsgs.put(property, errorMessage);
	}

	public Map<String, String> getErrorMessages() {
		return this.errMsgs;
	}

	public boolean notEmpty(String property, String str) {
		return this.notEmpty(property, str, "�����Բ���Ϊ��");
	}

	public boolean notEmpty(String property, String str, String errMsg) {
		boolean notEmpty = (str != null && !"".equals(str.trim()));
		if (!notEmpty) {
			errorFlag = true;
			errMsgs.put(property, errMsg);
		}

		return notEmpty;
	}

	public boolean minLength(String property, String str, int minLength) {
		return this.minLength(property, str, minLength, "�����Գ��ȱ��벻���� "
				+ minLength);
	}

	public boolean minLength(String property, String str, int minLength,
			String errMsg) {
		boolean min = (str.length() >= minLength);
		if (!min) {
			errorFlag = true;
			errMsgs.put(property, errMsg);
		}

		return min;
	}

	public boolean maxLength(String property, String str, int maxLength) {
		return this.maxLength(property, str, maxLength, "�����Եĳ��Ȳ������� "
				+ maxLength);
	}

	public boolean maxLength(String property, String str, int maxLength,
			String errMsg) {
		boolean max = (str.length() <= maxLength);
		if (!max) {
			errorFlag = true;
			errMsgs.put(property, errMsg);
		}

		return max;
	}

	public boolean lengthRange(String property, String str, int minLength,
			int maxLength) {
		return this.lengthRange(property, str, minLength, maxLength,
				"�����Գ��ȱ����� " + minLength + " ��" + maxLength + " ֮��");
	}

	public boolean lengthRange(String property, String str, int minLength,
			int maxLength, String errMsg) {
		int strLength = str.length();
		boolean match = (minLength <= strLength && strLength <= maxLength);
		if (!match) {
			errorFlag = true;
			errMsgs.put(property, errMsg);
		}

		return match;
	}

	public boolean checkVOType(Object voObj, Class<? extends Object> type)
			throws VOTypeNotMatch {
		if (voObj.getClass() != type) {
			errorFlag = true;
			errMsgs.put("type", "VO ���Ͳ�ƥ��");
			throw new VOTypeNotMatch();
		}

		return true;
	}

	public boolean valueRange(String property, int value, int minValue,
			int maxValue, String errMsg) {
		boolean match = (minValue <= value && value <= maxValue);
		if (!match) {
			errorFlag = true;
			errMsgs.put(property, errMsg);
		}

		return match;
	}

	public boolean valueRange(String property, int value, int minValue,
			int maxValue) {
		return this.valueRange(property, value, minValue, maxValue, "������ֵ�ķ�ΧΪ"
				+ minValue + "��" + maxValue);
	}

	@Override
	public String getErrorMessage(String property) {
		return this.errMsgs.get(property);
	}

	@Override
	public abstract boolean validate(Object VOObject) throws VOTypeNotMatch;
}
