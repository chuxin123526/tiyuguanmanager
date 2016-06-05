package cn.wheel.tiyuguanmanager.common.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;

public abstract class AbstractDaoCriteria implements DaoCriteria {

	public AbstractDaoCriteria(int type, int op, Object content) {
		this.type = type;
		this.op = op;
		this.content = content;
	}

	private int type;
	private int op;
	private Object content;

	public DaoCriteria setType(int type) {
		this.type = type;
		return this;
	}

	public DaoCriteria setOp(int op) {
		this.op = op;
		return this;
	}

	public DaoCriteria setContent(Object content) {
		this.content = content;
		return this;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public int getOp() {
		return this.op;
	}

	@Override
	public Object getContent() {
		return this.content;
	}

}
