package cn.wheel.tiyuguanmanager.user.util;

/**
 * 信息校验工具类
 * 
 * @author DENG YURONG
 * 
 */
public class InfoCheckUtils {
	private static int[] CITIZENID_WEIGHT = new int[] { 7, 9, 10, 5, 8, 4, 2,
			1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	private static char[] CITIZENID_CHECKCODE = new char[] { '1', '0', 'X',
			'9', '8', '7', '6', '5', '4', '3', '2' };

	public static boolean checkCitizenId(String citizenId) {
		if (citizenId.length() != 18) {
			return false;
		}

		int sum = 0;
		for (int i = 0; i < 17; i++) {
			char idChar = citizenId.charAt(i);
			int offset = (int) (idChar - '0');
			if (offset < 0 || offset > 9) {
				return false;
			}

			sum += (offset * CITIZENID_WEIGHT[i]);
		}

		int mod = sum % 11;

		return (citizenId.charAt(17) == CITIZENID_CHECKCODE[mod]);
	}

	public static boolean checkMobilePhone(String mobilePhone) {
		int length = mobilePhone.length();

		return (length == 11);
	}
}
