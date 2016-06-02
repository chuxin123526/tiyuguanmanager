package cn.wheel.tiyuguanmanager.user.vo.validator;

import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

public interface VOValidator {
	public String getErrorMessage(String property);

	public boolean validate(Object VOObject) throws VOTypeNotMatch;
}
