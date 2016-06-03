package cn.wheel.tiyuguanmanager.user.util;

/**
 * 分页一些计算
 * 
 * @author DENG YURONG
 * 
 */
public class PagingUtils {
	public static final int KEY_BOUNDS_MIN = 0;
	public static final int KEY_BOUNDS_MAX = 1;

	/**
	 * 计算分页导航栏的上下边界
	 * 
	 * @param currentPage
	 *            当前所在页面
	 * @param maxPage
	 *            一共有多少页
	 * @param offset
	 *            前后显示多少页
	 * @return 一个数组，第一个元素是导航页的下界，第二个元素是导航页的上界
	 */
	public static int[] getPageNavigationBounds(int currentPage, int maxPage, int offset) {
		int[] retValue = new int[2];

		retValue[KEY_BOUNDS_MIN] = (currentPage >= offset + 1 ? currentPage - offset : 1);
		retValue[KEY_BOUNDS_MAX] = (currentPage + offset >= maxPage ? maxPage : currentPage + offset);

		return retValue;
	}

	/**
	 * 计算最大页码
	 * 
	 * @param total
	 *            一共有多少个项目参与分页
	 * @param countPerPage
	 *            每一页有多少个
	 * @return 需要分多少页
	 */
	public static int getMaxPage(int total, int countPerPage) {
		if (total == 0) {
			return 0;
		}

		return ((total / countPerPage) + (total % countPerPage != 0 ? 1 : 0));
	}

	/**
	 * 计算指定页码第一项相对于所有对象而言的偏移量
	 * 
	 * @param page
	 *            页码
	 * @param count
	 *            每页有多少个项目
	 * @return 第一个项目的偏移
	 */
	public static int calcFirstOffset(int page, int count) {
		return ((page - 1) * count);
	}
	
	public static int[] buildPageArray(int from, int to) {
		int[] retValue = new int[to - from + 1];
		for (int i = from; i <= to; i++) {
			retValue[i - from] = i;
		}
		
		return retValue;
	}
}
