package cn.wheel.tiyuguanmanager.announcement.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryResult;

@Controller("getAnnouncementCommentAction")
@Scope("prototype")
public class GetAnnouncementCommentAction {

	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Resource
	private IAnnouncementCommentService commentService;

	private Map<String, Object> ajaxReturn;
	private long announcementId;
	private int from;
	private int count;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
	}

	public String execute() {
		AnnouncementCommentQueryResult result = this.commentService.getAnnouncementComment(announcementId, from, count);

		this.ajaxReturn = new HashMap<String, Object>();
		this.ajaxReturn.put("totalCount", result.getTotalCount());
		this.ajaxReturn.put("hasMore", (result.getTotalCount() != 0 && (from + count < result.getTotalCount())));
		fillToMap(result.getResult());

		return "json";
	}

	private void fillToMap(List<AnnouncementComment> comments) {
		List<Map<String, Object>> infos = new ArrayList<>();
		for (AnnouncementComment comment : comments) {
			Map<String, Object> map = new HashMap<>();
			map.put("content", comment.getCommentContent());
			map.put("time", formatter.format(comment.getCommentPublishTime()));
			map.put("publisher", comment.getCommentPublisher().getUserId());
			map.put("id", comment.getCommentId());

			infos.add(map);
		}

		this.ajaxReturn.put("comments", infos);
	}
}
