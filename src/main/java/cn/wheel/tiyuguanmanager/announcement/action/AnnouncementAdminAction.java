package cn.wheel.tiyuguanmanager.announcement.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryShowback;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;

@Controller("announcementAdminAction")
@Scope("prototype")
public class AnnouncementAdminAction {

	private static SimpleDateFormat format;

	static {
		format = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Resource
	private IAnnouncementService announcementService;

	/**
	 * 用于接收前台传过来的表单
	 */
	private AnnouncementVO announcement;

	/**
	 * 用作 json 返回
	 */
	private Map<String, Object> ajaxReturn;

	/**
	 * 用于接收前台传来的查询表单
	 */
	private AnnouncementQueryVO query;

	/**
	 * 页面标题，用于页面复用
	 */
	private String tipWord;

	/**
	 * 表明当前页面的功能，用于页面的复用
	 */
	// --- 下面的是针对列表页面 ---
	// 1 -- 所有的已发布的公告列表
	// 2 -- 所有的草稿列表
	// 3 -- 公告查询结果页面
	//
	// --- 下面是针对公告编辑页面的 ---
	// 1 -- 创建新公告
	// 2 -- 修改公告或草稿
	//
	// --- 下面是针对消息页面的 ---
	// 1 -- 成功发布公告
	// 2 -- 成功发布草稿
	// 3 -- 成功修改公告内容
	// 4 -- 成功修改草稿内容
	// 5 -- 无法找到指定的草稿
	// 6 -- 成功保存并发布公告
	private int function;

	// 用于回显查询条件
	private AnnouncementQueryShowback showback;

	// 查询结果
	private List<Announcement> announcementList;

	// 如果是进行跳转到达的页面，表示从哪里跳转过来的
	// 1 -- 发表公告成功
	// 2 -- 保存草稿成功
	// 3 -- 删除公告成功
	private int from;

	// 分页用数据
	private int page;
	private int minPage;
	private int maxPage;
	private int allPages[];

	// 提示信息
	private String msgWord;

	public String getMsgWord() {
		return msgWord;
	}

	public void setMsgWord(String msgWord) {
		this.msgWord = msgWord;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMinPage() {
		return minPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int[] getAllPages() {
		return allPages;
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public AnnouncementVO getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(AnnouncementVO announcement) {
		this.announcement = announcement;
	}

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public AnnouncementQueryVO getQuery() {
		return query;
	}

	public void setQuery(AnnouncementQueryVO query) {
		this.query = query;
	}

	public String getTipWord() {
		return tipWord;
	}

	public void setTipWord(String tipWord) {
		this.tipWord = tipWord;
	}

	public AnnouncementQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(AnnouncementQueryShowback showback) {
		this.showback = showback;
	}

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	/**
	 * 跳转到公告管理模块首页
	 * 
	 * @return
	 */
	public String homePage() {
		return "success";
	}

	/**
	 * 创建公告的页面
	 * 
	 * @return
	 */
	public String createAnnouncementPage() {
		this.tipWord = "创建新公告";
		this.function = 1;

		return "success";
	}

	/**
	 * 查看已经发布的公告的列表
	 * 
	 * @return
	 */
	public String publishedAnnouncementList() {
		AnnouncementQueryVO queryVO = new AnnouncementQueryVO();
		queryVO.setCriteria(new int[] { 5 });
		queryVO.setType(new int[] { AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT });

		AnnouncementQueryResult result = announcementService.queryAnnouncement(queryVO, page);
		int[] bounds = PagingUtils.getPageNavigationBounds(result.getCurrentPage(), result.getMaxPage(), 3);

		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);
		this.showback = result.getShowback();
		this.tipWord = "所有已经发布的公告";
		this.announcementList = result.getResult();
		this.function = 1;

		return "success";
	}

	// 打开草稿页面
	public String draftAnnouncementList() {
		AnnouncementQueryVO queryVO = new AnnouncementQueryVO();
		queryVO.setCriteria(new int[] { 5 });
		queryVO.setType(new int[] { AnnouncementConstants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT });

		AnnouncementQueryResult result = announcementService.queryAnnouncement(queryVO, page);

		int[] bounds = PagingUtils.getPageNavigationBounds(result.getCurrentPage(), result.getMaxPage(), 3);

		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);
		this.showback = result.getShowback();
		this.tipWord = "所有草稿";
		this.announcementList = result.getResult();
		this.function = 2;

		return "success";
	}

	/**
	 * 处理公告发布的方法
	 * 
	 * @return
	 */
	public String publishAnnouncement() {
		try {
			this.announcementService.publishNewAnnouncement(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISH_SUCCESS);
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", AnnouncementConstants.AjaxCode.FORM_EXCEPTION);
		}

		return "json";
	}

	/**
	 * 保存草稿的操作
	 * 
	 * @return
	 */
	public String publishDraft() {
		try {
			this.announcementService.saveNewAnnouncementDraft(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISH_DRAFT_SUCCESS);
			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
			return "json";
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", AnnouncementConstants.AjaxCode.FORM_EXCEPTION);
			return "json";
		}
	}

	// 将一份草稿发布为正式公告
	public String pushDraft() {
		try {
			this.announcementService.publishDraft(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUSH_DRAFT_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (UserNotExistException e) {
			// 不会进这里
		} catch (SpecifiedAnnouncementIsNotDraftException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_SPECIFIED_ANNOUNCEMENT_IS_NOT_DRAFT);
		}

		return "json";
	}

	// 打开查询页面
	public String queryPage() {
		this.tipWord = format.format(new Date());

		return "success";
	}

	// 真正执行查询的处理的那个方法
	public String doQuery() {
		this.function = 3;
		this.tipWord = "查询结果";

		AnnouncementQueryResult queryResult = this.announcementService.queryAnnouncement(query, this.page);
		this.showback = queryResult.getShowback();
		this.announcementList = queryResult.getResult();

		int[] bounds = PagingUtils.getPageNavigationBounds(queryResult.getCurrentPage(), queryResult.getMaxPage(), 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(minPage, maxPage);
		this.page = queryResult.getCurrentPage();

		return "success";
	}

	// 修改公告或者草稿
	public String updateAnnouncementPage() {
		Announcement announce = this.announcementService.findAnnouncementById(announcement.getAnnouncementId());
		if (announce == null) {
			this.function = 5;
			return "404";
		}

		this.function = 2;
		announcement.setTitle(announce.getAnnouncementTitle());
		announcement.setContent(announce.getAnnouncementContent());
		announcement.setType(announce.getAnnouncementStatus());

		return "success";
	}

	// 处理修改草稿请求的方法
	public String doUpdateDraft() {
		this.announcement.setType(AnnouncementConstants.AnnouncementStatus.STATUS_DRAFT_ANNOUNCEMENT);

		try {
			this.announcementService.updateAnnouncement(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_DRAFT_UPDATE_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", AnnouncementConstants.AjaxCode.FORM_EXCEPTION);
		}

		return "json";
	}

	// 处理修改公告请求方法
	public String doUpdateAnnouncement() {
		this.announcement.setType(AnnouncementConstants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT);

		try {
			this.announcementService.updateAnnouncement(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_UPDATE_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", AnnouncementConstants.AjaxCode.FORM_EXCEPTION);
		}

		return "json";
	}

	// 处理
	public String doDeleteAnnouncement() {
		try {
			this.announcementService.deleteAnnouncement(announcement.getAnnouncementId());

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_DELETE_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
		}

		return "json";
	}

	// 显示结果页面
	public String showMessage() {
		return "success";
	}
}
