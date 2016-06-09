package cn.wheel.tiyuguanmanager.announcement.util;

public class HTMLUtils {
	public static String removeHTMLTag(String raw) {
		return raw.replace("<p>", "").replace("</p>", "").replace("<strong>", "").replace("</strong>", "").replace("&nbsp;", " ").replace("<li>", " ")
				.replace("</li>", " ").replace("<ol>", " ").replace("</ol>", " ").replace("&gt;", ">").replace("&lt;", "<").replace(" ", "");
	}
}
