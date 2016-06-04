package cn.wheel.tiyuguanmanager.user.util.hibernate.criteria;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.util.SQLUtils;

public class UserCriteriaUtils {
	public static void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias) {
		if (criteria == null || daoCriterias == null) {
			return;
		}

		for (DaoCriteria daoCriteria : daoCriterias) {
			int type = daoCriteria.getType();
			int op = daoCriteria.getOp();

			switch (type) {
			case DaoCriteria.TYPE_USER_USERNAME:
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("username", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("username", "%" + daoCriteria.getContent().toString() + "%"));
				}
				break;

			case DaoCriteria.TYPE_USER_PASSWORD:
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("password", daoCriteria.getContent().toString()));
				}
				break;
			case DaoCriteria.TYPE_USER_ROLE_NAME:
				criteria = criteria.createCriteria("role");

				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("name", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("name", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString())));
				}
				break;

			default:
				break;
			}
		}
	}
}
