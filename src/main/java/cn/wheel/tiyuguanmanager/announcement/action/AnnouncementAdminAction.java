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
	 * ���ڽ���ǰ̨�������ı�
	 */
	private AnnouncementVO announcement;

	/**
	 * ���� json ����
	 */
	private Map<String, Object> ajaxReturn;

	/**
	 * ���ڽ���ǰ̨�����Ĳ�ѯ��
	 */
	private AnnouncementQueryVO query;

	/**
	 * ҳ����⣬����ҳ�渴��
	 */
	private String tipWord;

	/**
	 * ������ǰҳ��Ĺ��ܣ�����ҳ��ĸ���
	 */
	// --- �����������б�ҳ�� ---
	// 1 -- ���е��ѷ����Ĺ����б�
	// 2 -- ���еĲݸ��б�
	// 3 -- �����ѯ���ҳ��
	//
	// --- ��������Թ���༭ҳ��� ---
	// 1 -- �����¹���
	// 2 -- �޸Ĺ����ݸ�
	//
	// --- �����������Ϣҳ��� ---
	// 1 -- �ɹ���������
	// 2 -- �ɹ������ݸ�
	// 3 -- �ɹ��޸Ĺ�������
	// 4 -- �ɹ��޸Ĳݸ�����
	// 5 -- �޷��ҵ�ָ���Ĳݸ�
	// 6 -- �ɹ����沢��������
	private int function;

	// ���ڻ��Բ�ѯ����
	private AnnouncementQueryShowback showback;

	// ��ѯ���
	private List<Announcement> announcementList;

	// ����ǽ�����ת�����ҳ�棬��ʾ��������ת������
	// 1 -- ������ɹ�
	// 2 -- ����ݸ�ɹ�
	// 3 -- ɾ������ɹ�
	private int from;

	// ��ҳ������
	private int page;
	private int minPage;
	private int maxPage;
	private int allPages[];

	// ��ʾ��Ϣ
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
	 * ��ת���������ģ����ҳ
	 * 
	 * @return
	 */
	public String homePage() {
		return "success";
	}

	/**
	 * ���������ҳ��
	 * 
	 * @return
	 */
	public String createAnnouncementPage() {
		this.tipWord = "�����¹���";
		this.function = 1;

		return "success";
	}

	/**
	 * �鿴�Ѿ������Ĺ�����б�
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
		this.tipWord = "�����Ѿ������Ĺ���";
		this.announcementList = result.getResult();
		this.function = 1;

		return "success";
	}

	// �򿪲ݸ�ҳ��
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
		this.tipWord = "���вݸ�";
		this.announcementList = result.getResult();
		this.function = 2;

		return "success";
	}

	/**
	 * �����淢���ķ���
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
	 * ����ݸ�Ĳ���
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

	// ��һ�ݲݸ巢��Ϊ��ʽ����
	public String pushDraft() {
		try {
			this.announcementService.publishDraft(announcement);

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUSH_DRAFT_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (UserNotExistException e) {
			// ���������
		} catch (SpecifiedAnnouncementIsNotDraftException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_SPECIFIED_ANNOUNCEMENT_IS_NOT_DRAFT);
		}

		return "json";
	}

	// �򿪲�ѯҳ��
	public String queryPage() {
		this.tipWord = format.format(new Date());

		return "success";
	}

	// ����ִ�в�ѯ�Ĵ�����Ǹ�����
	public String doQuery() {
		this.function = 3;
		this.tipWord = "��ѯ���";

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

	// �޸Ĺ�����߲ݸ�
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

	// �����޸Ĳݸ�����ķ���
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

	// �����޸Ĺ������󷽷�
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

	// ����
	public String doDeleteAnnouncement() {
		try {
			this.announcementService.deleteAnnouncement(announcement.getAnnouncementId());

			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_DELETE_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_PUBLISHER_NOT_FOUND);
		}

		return "json";
	}

	// ��ʾ���ҳ��
	public String showMessage() {
		return "success";
	}
}
