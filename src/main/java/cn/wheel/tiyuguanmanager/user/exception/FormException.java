package cn.wheel.tiyuguanmanager.user.exception;

import java.util.Map;

/**
 * �û��������׳����쳣
 * 
 * @author DENG YURONG
 * 
 */
public class FormException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> errorMessages;

	public FormException() {
		super("�û�������");
	}

	public FormException(Map<String, Object> errMsgs) {
		super("�û�������");
		this.errorMessages = errMsgs;
	}

	public Map<String, Object> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, Object> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
