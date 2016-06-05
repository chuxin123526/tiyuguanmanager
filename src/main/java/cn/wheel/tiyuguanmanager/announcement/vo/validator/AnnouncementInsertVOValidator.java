package cn.wheel.tiyuguanmanager.announcement.vo.validator;

import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.common.vo.validator.AbstractVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

public class AnnouncementInsertVOValidator extends AbstractVOValidator {

	public static final String PROPERTY_TITLE = "title";
	public static final String PROPERTY_CONTETNT = "content";

	@Override
	public boolean validate(Object VOObject) throws VOTypeNotMatch {
		checkVOType(VOObject, AnnouncementVO.class);
		AnnouncementVO announcementVO = (AnnouncementVO) VOObject;

		// ����
		notEmpty(PROPERTY_TITLE, announcementVO.getTitle());

		// ����
		notEmpty(PROPERTY_CONTETNT, announcementVO.getContent());

		return !errorFlag;
	}

}
