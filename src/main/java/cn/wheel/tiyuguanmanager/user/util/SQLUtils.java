package cn.wheel.tiyuguanmanager.user.util;

/**
 * 一些跟数据库有关的辅助类
 * 
 * @author DENG YURONG
 * 
 */
public class SQLUtils {
	public static String wrapLikeCriteria(String value) {
		return "%" + value + "%";
	}
}
