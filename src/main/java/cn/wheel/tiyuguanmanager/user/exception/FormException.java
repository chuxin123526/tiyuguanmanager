package cn.wheel.tiyuguanmanager.user.exception;

import java.util.Map;

/**
 * 用户表单有误抛出的异常
 * 
 * @author DENG YURONG
 * 
 */
public class FormException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMessages;

	public FormException() {
		super("用户表单有误");
	}

	public FormException(Map<String, String> errMsgs) {
		super("用户表单有误");
		this.errorMessages = errMsgs;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
