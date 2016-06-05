package cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.common.dao.criteria.CriteriaProcessor;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.SQLUtils;

public class AnnouncementCommentCriteriaUtils implements CriteriaProcessor {
	@Override
	public void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias) {
		if (daoCriterias == null || daoCriterias.length == 0) {
			return;
		}

		for (DaoCriteria daoCriteria : daoCriterias) {
			int type = daoCriteria.getType();
			int op = daoCriteria.getOp();

			switch (type) {
			case DaoCriteria.TYPE_ANNOUNCEMENT_COMMENT_CONTENT: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("commentContent", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("commentContent", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString())));
				}
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_COMMENT_PUBLISH_TIME: {
				Date time = (Date) daoCriteria.getContent();
				criteria.add(Restrictions.eq("commentPublishTime", time));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_COMMNET_PUBLISH_TIME_RANGE: {
				Date[] timeRange = (Date[]) daoCriteria.getContent();
				criteria.add(Restrictions.between("commentPublishTime", timeRange[0], timeRange[1]));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_COMMENT_PUBLISHER_ID: {
				criteria = criteria.createCriteria("commentPublisher");
				criteria.add(Restrictions.eq("userId", (long) daoCriteria.getContent()));
			}
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean canProcess(Class<? extends Object> clazz) {
		return (clazz == AnnouncementComment.class);
	}
}
