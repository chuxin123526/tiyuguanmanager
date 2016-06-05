package cn.wheel.tiyuguanmanager.announcement.dao.announcement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.AnnouncementHibernateCallback;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.QueryEntityCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.common.util.hibernate.RowCountHibernateCallBack;

@Repository("announcementDao")
public class AnnouncementDaoImpl implements IAnnouncementDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public void insert(Announcement entity) {
		this.hibernateTemplate.save(entity);
	}

	@Override
	public void update(Announcement entity) {
		this.hibernateTemplate.update(entity);
	}

	@Override
	public void delete(Announcement entitiy) {
		this.hibernateTemplate.delete(entitiy);
	}

	@Override
	public Announcement findById(long id) {
		return this.hibernateTemplate.get(Announcement.class, id);
	}

	@Override
	public long count() {
		return this.hibernateTemplate.execute(new QueryEntityCountHibernateCallBack("Announcement"));
	}

	@Override
	public long count(DaoCriteria[] criterias) {
		return this.hibernateTemplate.execute(new RowCountHibernateCallBack(criterias, Announcement.class));
	}

	@Override
	public List<Announcement> find(DaoCriteria[] criterias) {
		return this.hibernateTemplate.execute(new AnnouncementHibernateCallback(criterias));
	}

	@Override
	public List<Announcement> find(DaoCriteria[] criterias, int offset, int count) {
		return this.hibernateTemplate.execute(new AnnouncementHibernateCallback(criterias).enablePaging(offset, count));
	}
}
