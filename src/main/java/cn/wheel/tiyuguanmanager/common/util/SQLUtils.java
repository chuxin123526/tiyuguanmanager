package cn.wheel.tiyuguanmanager.common.util;

/**
 * һЩ�����ݿ��йصĸ�����
 * 
 * @author DENG YURONG
 * 
 */
public class SQLUtils {
	public static String wrapLikeCriteria(String value) {
		return "%" + value + "%";
	}
}
