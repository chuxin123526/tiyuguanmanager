package cn.wheel.tiyuguanmanager.test.announcement;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnnouncementServiceTest extends BaseAppContextTest {

	private IAnnouncementService announcementService;

	@Before
	public void init() {
		ApplicationContext context = getApplicationContext();

		if (announcementService == null) {
			announcementService = context.getBean(IAnnouncementService.class);
		}
	}

	@Test
	public void test00_Insert() {
		AnnouncementVO announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("PY ����");
		announcementVO.setContent("�ⱳ��϶��в��ɸ��˵� PY ����");

		announcementService.publishNewAnnouncement(announcementVO);
	}

	@Test
	public void test01_Draft() {
		AnnouncementVO announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("USB ����");
		announcementVO.setContent("�ⱳ��϶��в��ɸ��˵� USB ���ף�����һ���ݸ壩");

		announcementService.saveNewAnnouncementDraft(announcementVO);
	}
	
	@Test
	public void test02_publishDraft() {
		AnnouncementVO announcementVO = new AnnouncementVO();
		announcementVO.setAnnouncementId(2);
		
		announcementService.publishDraft(announcementVO);
	}
}
