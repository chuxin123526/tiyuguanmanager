package cn.wheel.tiyuguanmanager.announcement.service.announcement.comment;

import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementCommentNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.CommentDeletePermissionDeniedException;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentVO;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;

public interface IAnnouncementCommentService {

	/**
	 * 获得指定公告编号对应公告的评论的数量
	 * 
	 * @param announcementId
	 *            要获得评论数量的公告公告编号
	 * @return 该公告下评论的数量
	 */
	public long getCommentCountOfAnnouncement(long announcementId);

	/**
	 * 根据公告编号和页码列出公告评论
	 * 
	 * @param announcementId
	 *            公告编号
	 * @param page
	 *            页码
	 * @throws AnnouncementNotFoundException
	 *             如果指定编号对应的公告实体不存在，则会抛出这个异常
	 * @return 结果
	 */
	public AnnouncementCommentQueryResult listPublishedComment(long announcementId, int page) throws AnnouncementNotFoundException;

	/**
	 * 发表一条公告评论
	 * 
	 * @param commentVO
	 *            里面应该有被评论公告的编号，发表评论用户的编号和评论内容
	 * @throws UserNotExistException
	 *             如果表单中用户编号指定的用户不存在，则会抛出这个异常
	 * @throws AnnouncementNotFoundException
	 *             如果不存在评论表单中指定的公告，则会抛出这个异常
	 */
	public void publishComment(AnnouncementCommentVO commentVO) throws UserNotExistException, AnnouncementNotFoundException;

	/**
	 * 根据表单中查询条件从数据库中查询符合条件的评论实体类
	 * 
	 * @param queryVO
	 *            表单
	 * @return 结果
	 * @throws AnnouncementNotFoundException
	 *             如果指定的公告编号无效，则会抛出这个异常
	 */
	public AnnouncementCommentQueryResult query(AnnouncementCommentQueryVO queryVO) throws AnnouncementNotFoundException;

	/**
	 * 对外隐藏某一条公告评论
	 * 
	 * @param commentId
	 *            公告评论的编号
	 * @throws AnnouncementNotFoundException
	 *             如果指定编号对应的公告评论不存在，则会抛出这个异常
	 */
	public void forbidAnnouncementComment(long commentId) throws AnnouncementNotFoundException;

	/**
	 * 对外显示一条已经被隐藏的公告评论
	 * 
	 * @param commentId
	 *            公告评论的编号
	 * @throws AnnouncementCommentNotFoundException
	 *             如果指定编号对应的公告评论不存在，则会抛出这个异常
	 */
	public void showAnnouncementComment(long commentId) throws AnnouncementCommentNotFoundException;

	/**
	 * 根据特定的公告编号和指定的返回获得该公告的评论
	 * 
	 * @param announcementId
	 *            公告评论
	 * @param from
	 *            从哪一条评论开始获取
	 * @param count
	 *            获取多少条
	 * @return 包含有评论以及相关信息的对象类
	 */
	public AnnouncementCommentQueryResult getAnnouncementComment(long announcementId, int from, int count);

	/**
	 * 用户删除自己的评论
	 * 
	 * @param commentId
	 *            要删除的评论编号
	 * @param userId
	 *            当前登录的用户用户编号
	 * @throws CommentDeletePermissionDeniedException
	 *             如果发起请求的用户并不是该评论的发布者，则没有权限删除这条评论，会抛出该异常
	 * @throws AnnouncementNotFoundException
	 *             如果指定的公告没有找到，则会抛出该异常
	 */
	public void deleteAnnouncement(long commentId, long userId) throws CommentDeletePermissionDeniedException, AnnouncementNotFoundException;

	/**
	 * 根据公告编号和页码查询该公告下对应的所有评论信息
	 * 
	 * @param announcementId
	 *            公告编号
	 * @param page
	 *            页码
	 * @return 含有评论内容以及其他附加内容的结果对象
	 */
	public AnnouncementCommentQueryResult findCommentsByAnnouncementId(long announcementId, int page);

	/**
	 * 对一条进行删除的评论进行恢复操作
	 * 
	 * @param commentId
	 *            要进行恢复操作的评论编号
	 * @throws AnnouncementCommentNotFoundException
	 *             如果指定评论编号对应的评论对象不存在，则抛出这个异常
	 */
	public void recoverComment(long commentId) throws AnnouncementCommentNotFoundException;
}
