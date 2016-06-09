package cn.wheel.tiyuguanmanager.announcement.service.announcement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.dao.announcement.IAnnouncementDao;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementContentCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementKeywordSearchCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementMultiTypeCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementOrderCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementPublisherIdCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementTimeRangeCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementTitleCriteria;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryShowback;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.announcement.vo.validator.AnnouncementInsertVOValidator;
import cn.wheel.tiyuguanmanager.announcement.vo.validator.AnnouncementUpdateValidator;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

@Service("announcementService")
public class AnnouncementServiceImpl implements IAnnouncementService {

	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	@Resource
	private IUserDao userDao;

	@Resource
	private IAnnouncementDao announcementDao;

	@Transactional
	@Override
	public void publishNewAnnouncement(AnnouncementVO announcementVO) throws UserNotExistException {
		// 1. 校验表单
		AnnouncementInsertVOValidator validator = new AnnouncementInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. 提取发布者用户信息
		User user = userDao.findById(announcementVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. 组装 PO
		Date time = new Date();
		Announcement announcement = new Announcement();
		announcement.setAnnouncementPublisher(user);
		announcement.setAnnouncementPublisherTime(time);
		announcement.setAnnouncementLastChangeTime(time);
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());
		announcement.setAnnouncementStatus(AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);

		// 4. 交给持久层写入数据库
		announcementDao.insert(announcement);
	}

	@Transactional
	@Override
	public void saveNewAnnouncementDraft(AnnouncementVO announcementVO) throws UserNotExistException {
		// 1. 校验表单
		AnnouncementInsertVOValidator validator = new AnnouncementInsertVOValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. 提取发布者用户信息
		User user = userDao.findById(announcementVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. 组装 PO
		Date time = new Date();
		Announcement announcement = new Announcement();
		announcement.setAnnouncementPublisher(user);
		announcement.setAnnouncementPublisherTime(null);
		announcement.setAnnouncementLastChangeTime(time);
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());
		announcement.setAnnouncementStatus(AnnouncementConstants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT);

		// 4. 交给持久层写入数据库
		announcementDao.insert(announcement);
	}

	@Transactional
	@Override
	public void publishDraft(AnnouncementVO draftAnnouncementVO) throws UserNotExistException {
		// 1. 提取草稿对象
		Announcement draft = this.announcementDao.findById(draftAnnouncementVO.getAnnouncementId());
		if (draft == null) {
			throw new AnnouncementNotFoundException();
		}

		// 2. 修改状态
		if (draft.getAnnouncementStatus() != AnnouncementConstants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT) {
			throw new SpecifiedAnnouncementIsNotDraftException();
		}
		draft.setAnnouncementStatus(AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);

		// 3. 更改发布时间
		Date now = new Date();
		draft.setAnnouncementPublisherTime(now);
		draft.setAnnouncementLastChangeTime(now);

		// 4. 通知持久层更新
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
	public void deleteAnnouncement(long announcementId) throws AnnouncementNotFoundException {
		Announcement announcement = announcementDao.findById(announcementId);
		if (announcement == null) {
			throw new AnnouncementNotFoundException();
		}

		announcement.setAnnouncementStatus(AnnouncementConstants.AnnouncementStatus.STATUS_DELETED_ANNOUNCEMENT);
	}

	@Transactional
	@Override
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException {
		// 1. 校验表单
		AnnouncementUpdateValidator validator = new AnnouncementUpdateValidator();
		boolean validated = false;

		try {
			validated = validator.validate(announcementVO);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException();
		}

		// 2. 提取原有的公告和修改用户
		Announcement announcement = announcementDao.findById(announcementVO.getAnnouncementId());
		if (announcement == null) {
			throw new AnnouncementNotFoundException();
		}

		User user = userDao.findById(announcementVO.getUserId());
		if (user == null) {
			throw new UserNotExistException();
		}

		// 3. 修改信息
		announcement.setAnnouncementTitle(announcementVO.getTitle());
		announcement.setAnnouncementContent(announcementVO.getContent());
		announcement.setAnnouncementStatus(announcementVO.getType());
		announcement.setAnnouncementPublisher(user);

		// 4. 变更时间信息
		Date now = new Date();
		if (announcement.getAnnouncementPublisherTime() == null) {
			announcement.setAnnouncementPublisherTime(now);
		}
		announcement.setAnnouncementLastChangeTime(now);

		// 5. 持久层保存
		announcementDao.update(announcement);
	}

	@Transactional
	@Override
	public AnnouncementQueryResult queryAnnouncement(AnnouncementQueryVO queryVO, int page) {
		boolean findAll = false;
		AnnouncementQueryResult result = new AnnouncementQueryResult();
		AnnouncementQueryShowback showback = new AnnouncementQueryShowback();

		// 1. 根据表单数据拼装查询条件
		// 组装条件
		List<DaoCriteria> daoCriteriasList = new ArrayList<>();
		int[] formCriterias = queryVO.getCriteria();
		if (formCriterias == null || formCriterias.length == 0) {
			findAll = true;
		} else {
			for (int i : formCriterias) {
				switch (i) {
				case 1: {
					// 标题
					String title = queryVO.getTitle();
					if (title != null && !"".equals(title.trim())) {
						DaoCriteria titleCriteria = new AnnouncementTitleCriteria(title, false);
						daoCriteriasList.add(titleCriteria);

						showback.setTitleIncluded(true);
						showback.setTitle(title);

						findAll = false;
					}
				}
					break;
				case 2: {
					// 内容
					String content = queryVO.getContent();
					if (content != null && !"".equals(content.trim())) {
						DaoCriteria contentCriteia = new AnnouncementContentCriteria(content, false);
						daoCriteriasList.add(contentCriteia);

						showback.setContentIncluded(true);
						showback.setContent(content);

						findAll = false;
					}
				}
					break;
				case 3: {
					// 发布者
					DaoCriteria publisherIdCriteria = new AnnouncementPublisherIdCriteria(queryVO.getPublisherId());
					daoCriteriasList.add(publisherIdCriteria);

					showback.setPublisherIncluded(true);
					showback.setPublisherId(queryVO.getPublisherId());

					findAll = false;
				}
					break;
				case 4: {
					// 时间
					String beginTimeStr = queryVO.getRawTime().substring(0, 10) + " 00:00:00.000";
					String endTimeStr = queryVO.getRawTime().substring(13, 23) + " 23:59:59.999";

					Date beginTime = null;
					Date endTime = null;

					try {
						beginTime = formatter.parse(beginTimeStr);
						endTime = formatter.parse(endTimeStr);
					} catch (ParseException e) {
						continue;
					}

					DaoCriteria timeRangeCriteria = new AnnouncementTimeRangeCriteria(beginTime, endTime);
					daoCriteriasList.add(timeRangeCriteria);

					showback.setRawTime(queryVO.getRawTime());
					showback.setPublishTimeIncluded(true);
					showback.setBeginTime(beginTimeStr);
					showback.setEndTime(endTimeStr);

					findAll = false;
				}
					break;
				case 5: {
					// 公告类型
					int[] typeArray = queryVO.getType();
					if (typeArray != null) {
						showback.setTypeIncluded(true);

						for (int type : typeArray) {
							if (type == AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT) {
								showback.setTypePublishedIncluded(true);
							} else if (type == AnnouncementConstants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT) {
								showback.setTypeDraftIncluded(true);
							} else if (type == AnnouncementConstants.AnnouncementStatus.STATUS_DELETED_ANNOUNCEMENT) {
								showback.setTypeDeletedIncluded(true);
							}
						}

						DaoCriteria typeCriteria = new AnnouncementMultiTypeCriteria(typeArray);
						daoCriteriasList.add(typeCriteria);

						findAll = false;
					}
				}
					break;

				default:
					break;
				}
			}
		}

		// 判断是否为倒序
		if (queryVO.getDesc() == 1) {
			DaoCriteria descOrder = new AnnouncementOrderCriteria(true);
			daoCriteriasList.add(descOrder);
		}

		DaoCriteria[] criterias = new DaoCriteria[daoCriteriasList.size()];
		for (int i = 0; i < daoCriteriasList.size(); i++) {
			criterias[i] = daoCriteriasList.get(i);
		}

		// 2. 查询结果集数量
		long totalCount = 0;
		if (!findAll) {
			totalCount = announcementDao.count(criterias);
		} else {
			totalCount = announcementDao.count();
		}

		// 3. 进行分页
		int countPerPage = cn.wheel.tiyuguanmanager.user.constants.UserConstants.ITEM_PER_PAGE;
		int maxPage = PagingUtils.getMaxPage(totalCount, countPerPage);
		if (page < 0) {
			page = maxPage + page + 1;
		}

		if (page < 1) {
			page = 1;
		} else if (page > maxPage) {
			page = maxPage;
		}
		showback.setPage(page);

		// 4. 查询
		List<Announcement> resultList = this.announcementDao.find(criterias, PagingUtils.calcFirstOffset(page, countPerPage), countPerPage);

		// 5. 组装结果对象
		result.setTotalCount(totalCount);
		result.setCurrentPage(page);
		result.setMaxPage(maxPage);
		result.setCurrentPageItem(resultList.size());
		result.setResult(resultList);
		result.setShowback(showback);

		return result;
	}

	@Transactional
	@Override
	public Announcement findAnnouncementById(long announcementId) {
		return this.announcementDao.findById(announcementId);
	}

	@Transactional
	@Override
	public AnnouncementQueryResult publicAnnouncementList(int page, int countPerPage) {
		AnnouncementQueryResult result = new AnnouncementQueryResult();

		// 1. 拼装查询条件
		DaoCriteria[] criterias = new DaoCriteria[] {
				new AnnouncementMultiTypeCriteria(new int[] { AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT }),
				new AnnouncementOrderCriteria(true) };

		// 2. 查询总数量，计算分页参数
		long totalCount = this.announcementDao.count(criterias);
		int maxPage = PagingUtils.getMaxPage(totalCount, countPerPage);

		if (totalCount == 0) {
			result.setTotalCount(0);
			return result;
		}

		// 3. 实际的查询过程
		if (page < 0) {
			page = page + maxPage + 1;
		}

		if (page <= 0) {
			page = 1;
		} else if (page > maxPage) {
			page = maxPage;
		}

		List<Announcement> resultList = this.announcementDao.find(criterias, PagingUtils.calcFirstOffset(page, countPerPage), countPerPage);

		// 4. 组装查询结果对象
		result.setTotalCount(totalCount);
		result.setCurrentPageItem(resultList.size());
		result.setMaxPage(maxPage);
		result.setResult(resultList);
		result.setCurrentPage(page);

		return result;
	}

	@Transactional
	@Override
	public AnnouncementQueryResult keywordSearch(String keyword, int page) {
		AnnouncementQueryResult result = new AnnouncementQueryResult();

		// 1. 拼装查询条件
		DaoCriteria[] criterias = new DaoCriteria[] { new AnnouncementKeywordSearchCriteria(keyword) };

		// 2. 查询数量
		long totalCount = announcementDao.count(criterias);
		result.setTotalCount(totalCount);

		if (totalCount == 0) {
			return result;
		}

		// 3. 计算分页参数
		int maxPage = PagingUtils.getMaxPage(totalCount, 10);
		if (page < 0) {
			page = page + maxPage + 1;
		}

		if (page <= 0) {
			page = 1;
		} else if (page > maxPage) {
			page = maxPage;
		}

		result.setMaxPage(maxPage);
		result.setCurrentPage(page);

		// 4. 实际的查询
		List<Announcement> resultList = this.announcementDao.find(criterias, PagingUtils.calcFirstOffset(page, 10), 10);
		result.setCurrentPageItem(resultList.size());
		result.setResult(resultList);

		return result;
	}

	@Transactional
	@Override
	public void recoverAnnouncement(long announcementId) throws AnnouncementNotFoundException {
		Announcement announcement = this.announcementDao.findById(announcementId);
		if (announcement == null || announcement.getAnnouncementStatus() != AnnouncementConstants.AnnouncementStatus.STATUS_DELETED_ANNOUNCEMENT) {
			throw new AnnouncementNotFoundException();
		}

		announcement.setAnnouncementStatus(AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);
		announcementDao.update(announcement);
	}

}
