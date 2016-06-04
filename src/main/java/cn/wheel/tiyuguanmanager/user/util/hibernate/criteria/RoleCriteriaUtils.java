package cn.wheel.tiyuguanmanager.user.util.hibernate.criteria;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.util.SQLUtils;

public class RoleCriteriaUtils {
	public static void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias) {
		if (criteria == null && daoCriterias == null) {
			return;
		}

		for (DaoCriteria daoCriteria : daoCriterias) {
			int op = daoCriteria.getOp();
			int type = daoCriteria.getType();

			switch (type) {
			case DaoCriteria.TYPE_ROLE_NAME:
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
