package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * ͨ������������Ϊ���۵Ĳ�ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class AnnouncementCommentContentCriteria extends AbstractDaoCriteria {
	/**
	 * ����һ���������ݲ�ѯ����
	 * 
	 * @param content
	 *            ����
	 * @param accurate
	 *            �Ƿ�ȷƥ��
	 */
	public AnnouncementCommentContentCriteria(String content, boolean accurate) {
		super(TYPE_ANNOUNCEMENT_COMMENT_CONTENT, (accurate ? OP_EQUAL : OP_LIKE), content);
	}
}
