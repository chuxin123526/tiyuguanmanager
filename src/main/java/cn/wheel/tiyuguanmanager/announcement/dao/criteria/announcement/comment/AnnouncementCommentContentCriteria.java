package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * 通过评论内容作为评论的查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class AnnouncementCommentContentCriteria extends AbstractDaoCriteria {
	/**
	 * 创建一个评论内容查询条件
	 * 
	 * @param content
	 *            内容
	 * @param accurate
	 *            是否精确匹配
	 */
	public AnnouncementCommentContentCriteria(String content, boolean accurate) {
		super(TYPE_ANNOUNCEMENT_COMMENT_CONTENT, (accurate ? OP_EQUAL : OP_LIKE), content);
	}
}
