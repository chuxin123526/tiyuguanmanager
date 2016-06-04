package cn.wheel.tiyuguanmanager.user.util.hibernate.criteria;

import java.util.ArrayList;
import java.util.List;

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
			case DaoCriteria.TYPE_USER_USERNAME: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("username", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("username", "%" + daoCriteria.getContent().toString() + "%"));
				}
			}
				break;

			case DaoCriteria.TYPE_USER_PASSWORD: {
				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("password", daoCriteria.getContent().toString()));
				}
			}
				break;
			case DaoCriteria.TYPE_USER_ROLE_NAME: {
				criteria = criteria.createCriteria("role");

				if (op == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("name", daoCriteria.getContent().toString()));
				} else if (op == DaoCriteria.OP_LIKE) {
					criteria.add(Restrictions.like("name", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString())));
				}
			}
				break;
			case DaoCriteria.TYPE_USER_ROLE_ID: {
				criteria.createCriteria("role");
				criteria.add(Restrictions.eq("roleId", (long) daoCriteria.getContent()));
			}
				break;

			case DaoCriteria.TYPE_USER_ACCOUNT_TYPE: {
				int criteriaInt = daoCriteria.getOp();
				List<Integer> criteriaIntList = new ArrayList<Integer>();

				if ((criteriaInt & 0x1) == 0x1) {
					criteriaIntList.add(0);
				}

				if ((criteriaInt & 0x2) == 0x2) {
					criteriaIntList.add(2);
				}

				if ((criteriaInt & 0x4) == 0x4) {
					criteriaIntList.add(1);
				}

				criteria.add(Restrictions.in("type", criteriaIntList));
			}
				break;
			case DaoCriteria.TYPE_USER_EXCLUED_FORBIDDEN: {
				if (daoCriteria.getOp() == DaoCriteria.OP_TRUE) {
					criteria.add(Restrictions.eq("status", 0));
				}
			}

			default:
				break;
			}
		}
	}
}
