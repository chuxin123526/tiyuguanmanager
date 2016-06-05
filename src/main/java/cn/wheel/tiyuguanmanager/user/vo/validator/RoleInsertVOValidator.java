package cn.wheel.tiyuguanmanager.user.vo.validator;

import cn.wheel.tiyuguanmanager.common.vo.validator.AbstractVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

public class RoleInsertVOValidator extends AbstractVOValidator {

	public static final String PROPERTY_NAME = "name";

	@Override
	public boolean validate(Object VOObject) throws VOTypeNotMatch {
		checkVOType(VOObject, RoleVO.class);
		RoleVO roleVo = (RoleVO) VOObject;

		// 校验角色名称
		notEmpty(PROPERTY_NAME, roleVo.getName(), "角色名称不能为空");

		return !errorFlag;
	}

}
