package cn.wheel.tiyuguanmanager.user.util.hibernate.criteria;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.common.dao.criteria.CriteriaProcessor;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.SQLUtils;
import cn.wheel.tiyuguanmanager.user.po.User;

public class UserCriteriaUtils implements CriteriaProcessor {
	@Override
	public void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias) {
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
				criteria = criteria.createCriteria("role");

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
			case DaoCriteria.TYPE_USER_USER_ID: {
				if (daoCriteria.getOp() == DaoCriteria.OP_EQUAL) {
					criteria.add(Restrictions.eq("userId", (long) daoCriteria.getContent()));
				} else if (daoCriteria.getOp() == DaoCriteria.OP_DISEQUAL) {
					criteria.add(Restrictions.ne("userId", (long) daoCriteria.getContent()));
				}
			}

			default:
				break;
			}
		}

	}

	@Override
	public boolean canProcess(Class<? extends Object> clazz) {
		return (clazz == User.class);
	}
}
