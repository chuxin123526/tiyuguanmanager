package cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.common.dao.criteria.CriteriaProcessor;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.SQLUtils;

public class AnnouncementCriteriaUtils implements CriteriaProcessor {

	@Override
	public void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias) {
		if (daoCriterias == null || daoCriterias.length == 0) {
			return;
		}

		for (DaoCriteria daoCriteria : daoCriterias) {
			int type = daoCriteria.getType();
			int op = daoCriteria.getOp();

			switch (type) {
			case DaoCriteria.TYPE_ANNOUNCEMENT_TITLE: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("announcementTitle", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("announcementTitle", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString().replace(" ", "%"))));
				}
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_CONTENT: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("announcementContent", daoCriteria.getContent().toString()));
				} else {
					criteria.add(Restrictions.like("announcementContent", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString())));
				}
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_PUBLISH_TIME: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("announcementPublisherTime", daoCriteria.getContent()));
				}
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_PUBLISH_TIME_RANGE: {
				Date[] range = (Date[]) daoCriteria.getContent();

				// criteria.add(Restrictions.between("announcementPublisherTime",
				// range[0], range[1]));
				criteria.add(Restrictions.ge("announcementPublisherTime", range[0]));
				criteria.add(Restrictions.le("announcementPublisherTime", range[1]));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_PUBLISHER_ID: {
				criteria = criteria.createCriteria("announcementPublisher");
				criteria.add(Restrictions.eq("userId", daoCriteria.getContent()));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_STATUS: {
				criteria.add(Restrictions.eq("announcementStatus", daoCriteria.getContent()));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_TIME_ORDER: {
				if (op == DaoCriteria.ORDER_ASC) {
					criteria.addOrder(Order.asc("announcementPublisherTime"));
				} else if (op == DaoCriteria.ORDER_DESC) {
					criteria.addOrder(Order.desc("announcementPublisherTime"));
				}
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_MULTI_STATUS: {
				int[] m = (int[]) daoCriteria.getContent();
				if (m.length == 0) {
					continue;
				}
				List<Integer> cc = new ArrayList<Integer>();
				for (int i = 0; i < m.length; i++) {
					cc.add(m[i]);
				}

				criteria.add(Restrictions.in("announcementStatus", cc));
			}
				break;
			case DaoCriteria.TYPE_ANNOUNCEMENT_COMMENT_SEARCH_KEYWORD: {
				String keyword = daoCriteria.getContent().toString().replace(" ", "%");
				if (keyword == null || "".equals(keyword.trim())) {
					continue;
				}

				criteria.add(Restrictions.or(Restrictions.like("announcementTitle", SQLUtils.wrapLikeCriteria(keyword)),
						Restrictions.like("announcementContent", SQLUtils.wrapLikeCriteria(keyword))));
			}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean canProcess(Class<? extends Object> clazz) {
		return (clazz == Announcement.class);
	}

}
