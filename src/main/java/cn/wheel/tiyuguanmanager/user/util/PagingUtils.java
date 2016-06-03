package cn.wheel.tiyuguanmanager.user.util;

/**
 * ��ҳһЩ����
 * 
 * @author DENG YURONG
 * 
 */
public class PagingUtils {
	public static final int KEY_BOUNDS_MIN = 0;
	public static final int KEY_BOUNDS_MAX = 1;

	/**
	 * �����ҳ�����������±߽�
	 * 
	 * @param currentPage
	 *            ��ǰ����ҳ��
	 * @param maxPage
	 *            һ���ж���ҳ
	 * @param offset
	 *            ǰ����ʾ����ҳ
	 * @return һ�����飬��һ��Ԫ���ǵ���ҳ���½磬�ڶ���Ԫ���ǵ���ҳ���Ͻ�
	 */
	public static int[] getPageNavigationBounds(int currentPage, int maxPage, int offset) {
		int[] retValue = new int[2];

		retValue[KEY_BOUNDS_MIN] = (currentPage >= offset + 1 ? currentPage - offset : 1);
		retValue[KEY_BOUNDS_MAX] = (currentPage + offset >= maxPage ? maxPage : currentPage + offset);

		return retValue;
	}

	/**
	 * �������ҳ��
	 * 
	 * @param total
	 *            һ���ж��ٸ���Ŀ�����ҳ
	 * @param countPerPage
	 *            ÿһҳ�ж��ٸ�
	 * @return ��Ҫ�ֶ���ҳ
	 */
	public static int getMaxPage(int total, int countPerPage) {
		if (total == 0) {
			return 0;
		}

		return ((total / countPerPage) + (total % countPerPage != 0 ? 1 : 0));
	}

	/**
	 * ����ָ��ҳ���һ����������ж�����Ե�ƫ����
	 * 
	 * @param page
	 *            ҳ��
	 * @param count
	 *            ÿҳ�ж��ٸ���Ŀ
	 * @return ��һ����Ŀ��ƫ��
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
