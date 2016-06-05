package cn.wheel.tiyuguanmanager.announcement.service.announcement;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.announcement.constant.Constants;
import cn.wheel.tiyuguanmanager.announcement.dao.announcement.IAnnouncementDao;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementOrderCriteria;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.announcement.vo.validator.AnnouncementInsertVOValidator;
import cn.wheel.tiyuguanmanager.announcement.vo.validator.AnnouncementUpdateValidator;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

@Service("announcementService")
public class AnnouncementServiceImpl implements IAnnouncementService {

	@Resource
	private IUserDao userDao;

	@Resource
	private IAnnouncementDao announcementDao;

	@Transactional
	@Override
	public void publishNewAnnouncement(AnnouncementVO announcementVO) throws UserNotExistException {
		// 1. У���
		AnnouncementInsertVOValidator validator = new AnnouncementInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. ��ȡ�������û���Ϣ
		User user = userDao.findById(announcementVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. ��װ PO
		Date time = new Date();
		Announcement announcement = new Announcement();
		announcement.setAnnouncementPublisher(user);
		announcement.setAnnouncementPublisherTime(time);
		announcement.setAnnouncementLastChangeTime(time);
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());
		announcement.setAnnouncementStatus(Constants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);

		// 4. �����־ò�д�����ݿ�
		announcementDao.insert(announcement);
	}

	@Transactional
	@Override
	public void saveNewAnnouncementDraft(AnnouncementVO announcementVO) throws UserNotExistException {
		// 1. У���
		AnnouncementInsertVOValidator validator = new AnnouncementInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. ��ȡ�������û���Ϣ
		User user = userDao.findById(announcementVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. ��װ PO
		Date time = new Date();
		Announcement announcement = new Announcement();
		announcement.setAnnouncementPublisher(user);
		announcement.setAnnouncementPublisherTime(time);
		announcement.setAnnouncementLastChangeTime(time);
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());
		announcement.setAnnouncementStatus(Constants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT);

		// 4. �����־ò�д�����ݿ�
		announcementDao.insert(announcement);
	}

	@Transactional
	@Override
	public void publishDraft(AnnouncementVO draftAnnouncementVO) throws UserNotExistException {
		// 1. ��ȡ�ݸ����
		Announcement draft = this.announcementDao.findById(draftAnnouncementVO.getAnnouncementId());
		if (draft == null) {
			throw new AnnouncementNotFoundException();
		}

		// 2. �޸�״̬
		if (draft.getAnnouncementStatus() != Constants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT) {
			throw new SpecifiedAnnouncementIsNotDraftException();
		}
		draft.setAnnouncementStatus(Constants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);

		// 3. ���ķ���ʱ��
		Date now = new Date();
		draft.setAnnouncementPublisherTime(now);
		draft.setAnnouncementLastChangeTime(now);

		// 4. ֪ͨ�־ò����
		announcementDao.update(draft);
	}

	@Transactional
	@Override
	public List<Announcement> listLatestAnnouncement() {
		return announcementDao.find(new DaoCriteria[] { new AnnouncementOrderCriteria(true) });
	}

	@Transactional
	@Override
	public List<Announcement> listLateseAnnouncement(int count) {
		return announcementDao.find(new DaoCriteria[] { new AnnouncementOrderCriteria(true) }, 0, count);
	}

	@Transactional
	@Override
	public void deleteAnnouncement(long announcementId) {
		Announcement announcement = announcementDao.findById(announcementId);
		if (announcement == null) {
			throw new AnnouncementNotFoundException();
		}
	}

	@Transactional
	@Override
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException {
		// 1. У���
		AnnouncementUpdateValidator validator = new AnnouncementUpdateValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. ��ȡԭ�еĹ���
		Announcement announcement = announcementDao.findById(announcementVO.getAnnouncementId());
		if (announcement == null) {
			throw new AnnouncementNotFoundException();
		}

		// 3. �޸���Ϣ
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());

		// 4. ���ʱ����Ϣ
		announcement.setAnnouncementLastChangeTime(new Date());

		// 5. �־ò㱣��
		announcementDao.update(announcement);
	}

}
