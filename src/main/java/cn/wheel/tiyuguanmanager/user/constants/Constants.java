package cn.wheel.tiyuguanmanager.user.constants;

public class Constants {
	/**
	 * 分页中，每一页项目的数量
	 */
	public static final int ITEM_PER_PAGE = 5;

	/**
	 * 分页导航前后偏移页面
	 */
	public static final int NAVI_PAGE_OFFSET = 3;

	/**
	 * 用于表示通讯录项的类型
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class ContratType {
		/**
		 * 手机号码
		 */
		public static final int TYPE_MOBILE = 1;

		/**
		 * 通信地址
		 */
		public static final int TYPE_ADDRESS = 2;

		/**
		 * 电子邮件
		 */
		public static final int TYPE_EMAIL = 3;
	}

	/**
	 * 证件类型
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class IdentifierType {
		/**
		 * 公民身份证号码
		 */
		public static final int TYPE_CITIZEN_ID = 1;

		/**
		 * 护照
		 */
		public static final int TYPE_PASSPORT = 2;

		/**
		 * 军人证
		 */
		public static final int TYPE_ARMY = 3;
	}

	/**
	 * 性别
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class Gender {
		/**
		 * 女
		 */
		public static final int GENDER_FEMALE = 0;

		/**
		 * 男
		 */
		public static final int GENDER_MALE = 1;
	}

	/**
	 * ajax 请求中 json 的结果编码
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AjaxReturnValue {
		/**
		 * 当前操作需要登录
		 */
		public static final int NOT_LOGIN = 1;

		/**
		 * 当前登录用户没有指定的权限
		 */
		public static final int PERMISSION_DENIED = 2;

		/**
		 * 因为用户名和密码错误导致的登录失败败
		 */
		public static final int LOGIN_FAILED_DUE_TO_INFO_ERROR = 3;

		/**
		 * 用户名为空
		 */
		public static final int LOGIN_EMPTY_USERNAME = 4;

		/**
		 * 密码为空
		 */
		public static final int LOGIN_EMPTY_PASSWORD = 5;

		/**
		 * 登录成功
		 */
		public static final int LOGIN_SUCCESS = 6;

		/**
		 * 注销成功
		 */
		public static final int LOGOUT_SUCCESS = 7;

		/**
		 * 表单有误
		 */
		public static final int FORM_EXCEPTION = 8;

		/**
		 * 用户已经存在
		 */
		public static final int USER_EXIST = 9;

		/**
		 * 注册成功
		 */
		public static final int REGISTER_SUCCESS = 10;

		/**
		 * 表示当前登录的用户处于禁用状态
		 */
		public static final int USER_FORBIDDEN = 11;

		/**
		 * 表示要创建的这个角色已经在系统中存在同名角色
		 */
		public static final int ROLE_EXIST = 12;
		
		/**
		 * 表示成功创建一个角色
		 */
		public static final int ROLE_CREATE_SUCCESS = 13;
	}

	/**
	 * 表示用户状态
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class UserStatus {
		/**
		 * 正常状态
		 */
		public static final int NORMAL = 0;

		/**
		 * 表示当前账号已经被禁用
		 */
		public static final int DISABLED = 1;
	}
}
