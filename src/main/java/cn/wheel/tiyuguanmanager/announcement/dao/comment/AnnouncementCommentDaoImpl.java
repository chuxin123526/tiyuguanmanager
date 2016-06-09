package cn.wheel.tiyuguanmanager.announcement.dao.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.AnnouncementCommentHibernateCallback;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.QueryEntityCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.common.util.hibernate.RowCountHibernateCallBack;

@Repository("announcementCommentDao")
public class AnnouncementCommentDaoImpl implements IAnnouncementCommentDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public void insert(AnnouncementComment entity) {
		this.hibernateTemplate.save(entity);
	}

	@Override
	public void update(AnnouncementComment entity) {
		this.hibernateTemplate.update(entity);
	}

	@Override
	public void delete(AnnouncementComment entitiy) {
		this.hibernateTemplate.delete(entitiy);
	}

	@Override
	public AnnouncementComment findById(long id) {
		return this.hibernateTemplate.get(AnnouncementComment.class, id);
	}

	@Override
	public long count() {
		return this.hibernateTemplate.execute(new QueryEntityCountHibernateCallBack("AnnouncementComment"));
	}

	@Override
	public long count(DaoCriteria[] criterias) {
		return this.hibernateTemplate.execute(new RowCountHibernateCallBack(criterias, AnnouncementComment.class));
	}

	@Override
	public List<AnnouncementComment> find(DaoCriteria[] criterias) {
		return this.hibernateTemplate.execute(new AnnouncementCommentHibernateCallback(criterias));
	}

	@Override
	public List<AnnouncementComment> find(DaoCriteria[] criterias, int offset, int count) {
		return this.hibernateTemplate.execute(new AnnouncementCommentHibernateCallback(criterias).enablePaging(offset, count));
	}

}
