package cn.wheel.tiyuguanmanager.announcement.service.announcement.comment;

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
import cn.wheel.tiyuguanmanager.announcement.dao.comment.IAnnouncementCommentDao;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.AnnouncementMultiTypeCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment.AnnouncementCommentAnnouncementIdCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment.AnnouncementCommentAnnouncementTitleCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment.AnnouncementCommentMultyTypeCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment.AnnouncementCommentPublisherCriteria;
import cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment.AnnouncementCommentTimeRangeCriteria;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementCommentNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.CommentDeletePermissionDeniedException;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryShowback;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentVO;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;

@Service("announcementCommentService")
public class AnnouncementCommentServiceImpl implements IAnnouncementCommentService {

	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	@Resource
	private IUserDao userDao;

	@Resource
	private IAnnouncementDao announcementDao;

	@Resource
	private IAnnouncementCommentDao commentDao;

	@Transactional
	@Override
	public void publishComment(AnnouncementCommentVO commentVO) {
		long annoucementId = commentVO.getAnnouncementId();
		Announcement announcement = this.announcementDao.findById(annoucementId);
		if (announcement == null || announcement.getAnnouncementStatus() != AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT) {
			throw new AnnouncementNotFoundException();
		}

		long userId = commentVO.getPublishUserId();
		User user = this.userDao.findById(userId);
		if (user == null) {
			throw new UserNotExistException();
		}

		AnnouncementComment comment = new AnnouncementComment();
		comment.setAnnouncement(announcement);
		comment.setCommentContent(commentVO.getContent());
		comment.setCommentPublisher(user);
		comment.setCommentPublishTime(new Date());
		comment.setCommentStatus(AnnouncementConstants.CommentStatus.STATUS_PUBLISHED);

		this.commentDao.insert(comment);
	}

	@Transactional
	@Override
	public AnnouncementCommentQueryResult query(AnnouncementCommentQueryVO queryVO) throws AnnouncementNotFoundException {
		AnnouncementCommentQueryResult result = new AnnouncementCommentQueryResult();
		List<DaoCriteria> daoCriteriasList = new ArrayList<>();
		AnnouncementCommentQueryShowback showback = new AnnouncementCommentQueryShowback();

		// 1. 拼装查询条件
		boolean findAll = true;

		int[] typeArray = queryVO.getType();
		if (typeArray != null && typeArray.length > 0) {
			for (int type : typeArray) {
				switch (type) {
				case AnnouncementCommentQueryVO.CRITERIA_TYPE_COMMENT_ANNOUNCEMENT_ID: {
					DaoCriteria announcementId = new AnnouncementCommentAnnouncementIdCriteria(queryVO.getAnnouncementId());
					daoCriteriasList.add(announcementId);

					showback.setAnnouncementIdIncluded(true);
					showback.setAnnouncementId(queryVO.getAnnouncementId());

					findAll = false;
				}
					break;
				case AnnouncementCommentQueryVO.CRITERIA_TYPE_COMMENT_PUBLISHER_ID: {
					DaoCriteria publisherId = new AnnouncementCommentPublisherCriteria(queryVO.getUserId());
					daoCriteriasList.add(publisherId);

					findAll = false;
				}
				case AnnouncementCommentQueryVO.CRITERIA_TYPE_COMMENT_TIME_RANGE: {
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

					DaoCriteria timeRangeCriteria = new AnnouncementCommentTimeRangeCriteria(beginTime, endTime);
					daoCriteriasList.add(timeRangeCriteria);

					showback.setCommentTimeRangeIncluded(true);
					showback.setRawTime(queryVO.getRawTime());

					findAll = false;
				}
					break;
				case AnnouncementCommentQueryVO.CRITERIA_TYPE_COMMENT_TYPE: {
					showback.setCommentTypeIncluded(true);

					int[] commentTypes = queryVO.getCommentType();
					for (int i : commentTypes) {
						if (i == AnnouncementConstants.CommentStatus.STATUS_PUBLISHED) {
							showback.setTypePublishedIncluded(true);
						} else if (i == AnnouncementConstants.CommentStatus.STATUS_HIDDEN) {
							showback.setTypeHiddenIncluded(true);
						} else if (i == AnnouncementConstants.CommentStatus.STATUS_DELETED) {
							showback.setTypeDeletedIncluded(true);
						}
					}

					DaoCriteria typesCriteria = new AnnouncementMultiTypeCriteria(queryVO.getCommentType());
					daoCriteriasList.add(typesCriteria);

					findAll = false;
				}
					break;
				case AnnouncementCommentQueryVO.CRITERIA_TYPE_COMMENT_ANNOUNCEMENT_TITLE: {
					DaoCriteria announcementTitleCriteria = new AnnouncementCommentAnnouncementTitleCriteria(queryVO.getAnnouncementTitle());
					daoCriteriasList.add(announcementTitleCriteria);

					showback.setAnnouncementTitleIncluded(true);
					showback.setAnnonucementTitle(queryVO.getAnnouncementTitle());

					findAll = false;
				}
					break;

				default:
					break;
				}
			}
		} else {
			findAll = true;
		}

		result.setShowback(showback);
		DaoCriteria[] criterias = new DaoCriteria[daoCriteriasList.size()];
		for (int i = 0; i < daoCriteriasList.size(); i++) {
			criterias[i] = daoCriteriasList.get(i);
		}

		// 2. 查询符合条件的数量
		long totalCount = 0;
		if (!findAll) {
			totalCount = this.commentDao.count(criterias);
		} else {
			totalCount = this.commentDao.count();
		}

		// 3. 计算分页参数
		int totalPage = PagingUtils.getMaxPage(totalCount, UserConstants.ITEM_PER_PAGE);
		result.setTotalCount(totalCount);
		result.setMaxPage(totalPage);

		// 首先必须保证页码在有效的范围里
		int requestedPage = queryVO.getPage();
		if (requestedPage < 0) {
			// 如果是负数则是倒数，则是去倒数页面
			requestedPage = totalPage + requestedPage + 1;
		}

		// 对超出范围的页码做出处理
		if (requestedPage <= 0) {
			requestedPage = 1;
		} else if (requestedPage > totalPage) {
			requestedPage = totalPage;
		}

		showback.setPage(requestedPage);
		result.setCurrentPage(requestedPage);

		// 4. 实际的查询过程
		List<AnnouncementComment> commentList = this.commentDao.find(criterias, PagingUtils.calcFirstOffset(requestedPage, UserConstants.ITEM_PER_PAGE),
				UserConstants.ITEM_PER_PAGE);
		result.setResult(commentList);
		result.setCurrentPageItem(commentList.size());

		return result;
	}

	@Transactional
	@Override
	public void forbidAnnouncementComment(long commentId) throws AnnouncementNotFoundException {
		AnnouncementComment comment = this.commentDao.findById(commentId);
		if (comment == null) {
			throw new AnnouncementCommentNotFoundException();
		}

		comment.setCommentStatus(AnnouncementConstants.CommentStatus.STATUS_HIDDEN);
	}

	@Transactional
	@Override
	public void showAnnouncementComment(long commentId) throws AnnouncementCommentNotFoundException {
		AnnouncementComment comment = this.commentDao.findById(commentId);
		if (comment == null) {
			throw new AnnouncementCommentNotFoundException();
		}

		comment.setCommentStatus(AnnouncementConstants.CommentStatus.STATUS_PUBLISHED);
	}

	@Transactional
	@Override
	public AnnouncementCommentQueryResult listPublishedComment(long announcementId, int page) throws AnnouncementNotFoundException {
		DaoCriteria[] criterias = new DaoCriteria[] { new AnnouncementCommentAnnouncementIdCriteria(announcementId),
				new AnnouncementCommentMultyTypeCriteria(new int[] { AnnouncementConstants.CommentStatus.STATUS_PUBLISHED }) };

		AnnouncementCommentQueryResult result = new AnnouncementCommentQueryResult();

		// 1. 总数量
		long totalCount = this.commentDao.count(criterias);
		int totalPage = PagingUtils.getMaxPage(totalCount, UserConstants.ITEM_PER_PAGE);
		result.setTotalCount(totalCount);
		result.setMaxPage(totalPage);

		// 2. 计算参数
		if (page < 0) {
			page = totalPage + page + 1;
		}

		if (page <= 0) {
			page = 1;
		} else if (page > totalPage) {
			page = totalPage;
		}

		result.setCurrentPage(page);

		// 3. 实际的查询过程
		List<AnnouncementComment> commentList = this.commentDao.find(criterias, PagingUtils.calcFirstOffset(page, UserConstants.ITEM_PER_PAGE),
				UserConstants.ITEM_PER_PAGE);
		result.setResult(commentList);

		return result;
	}

	@Transactional
	@Override
	public long getCommentCountOfAnnouncement(long announcementId) {
		DaoCriteria[] criterias = new DaoCriteria[] { new AnnouncementCommentAnnouncementIdCriteria(announcementId),
				new AnnouncementCommentMultyTypeCriteria(new int[] { AnnouncementConstants.CommentStatus.STATUS_PUBLISHED }) };

		return this.commentDao.count(criterias);
	}

	@Transactional
	@Override
	public AnnouncementCommentQueryResult getAnnouncementComment(long announcementId, int from, int count) {
		AnnouncementCommentQueryResult result = new AnnouncementCommentQueryResult();

		// 1. 拼装查询条件
		DaoCriteria[] criterias = new DaoCriteria[] { new AnnouncementCommentAnnouncementIdCriteria(announcementId),
				new AnnouncementCommentMultyTypeCriteria(new int[] { AnnouncementConstants.CommentStatus.STATUS_PUBLISHED }) };

		// 2. 查询总数量
		long totalCount = commentDao.count(criterias);

		// 3. 查询内容
		List<AnnouncementComment> comments = this.commentDao.find(criterias, from, count);

		result.setCurrentPageItem(comments.size());
		result.setTotalCount(totalCount);
		result.setResult(comments);

		return result;
	}

	@Transactional
	@Override
	public void deleteAnnouncement(long commentId, long userId) throws CommentDeletePermissionDeniedException {
		AnnouncementComment comment = this.commentDao.findById(commentId);
		if (comment == null) {
			throw new AnnouncementNotFoundException();
		}

		if (comment.getCommentPublisher().getUserId() != userId) {
			throw new CommentDeletePermissionDeniedException();
		}

		comment.setCommentStatus(AnnouncementConstants.CommentStatus.STATUS_DELETED);
		commentDao.update(comment);
	}

	@Transactional
	@Override
	public AnnouncementCommentQueryResult findCommentsByAnnouncementId(long announcementId, int page) {
		// 1. 组装查询条件
		DaoCriteria[] criterias = new DaoCriteria[] { new AnnouncementCommentAnnouncementIdCriteria(announcementId) };

		// 2. 计算分页参数
		long totalCount = this.commentDao.count(criterias);
		int totalPage = PagingUtils.getMaxPage(totalCount, UserConstants.ITEM_PER_PAGE);
		if (page < 0) {
			page = totalPage + page + 1;
		}

		if (page <= 0) {
			page = 1;
		} else if (page > totalPage) {
			page = totalPage;
		}

		// 3. 实际的查询
		List<AnnouncementComment> resultList = this.commentDao.find(criterias, PagingUtils.calcFirstOffset(page, UserConstants.ITEM_PER_PAGE),
				UserConstants.ITEM_PER_PAGE);

		// 4. 组装结果类
		AnnouncementCommentQueryResult result = new AnnouncementCommentQueryResult();
		result.setTotalCount(totalCount);
		result.setMaxPage(totalPage);
		result.setCurrentPageItem(resultList.size());
		result.setCurrentPage(page);
		result.setResult(resultList);

		return result;
	}

	@Transactional
	@Override
	public void recoverComment(long commentId) {
		AnnouncementComment comment = this.commentDao.findById(commentId);
		if (comment == null) {
			throw new AnnouncementCommentNotFoundException();
		}

		comment.setCommentStatus(AnnouncementConstants.CommentStatus.STATUS_PUBLISHED);
		commentDao.update(comment);
	}
}
