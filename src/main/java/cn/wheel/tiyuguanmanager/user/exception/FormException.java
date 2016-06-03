package cn.wheel.tiyuguanmanager.user.exception;

import java.util.Map;

/**
 * 用户表单有误抛出的异常
 * 
 * @author DENG YURONG
 * 
 */
public class FormException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> errorMessages;

	public FormException() {
		super("用户表单有误");
	}

	public FormException(Map<String, Object> errMsgs) {
		super("用户表单有误");
		this.errorMessages = errMsgs;
	}

	public Map<String, Object> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, Object> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
