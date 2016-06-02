package cn.wheel.tiyuguanmanager.user.exception;

import java.util.Map;

/**
 * �û��������׳����쳣
 * 
 * @author DENG YURONG
 * 
 */
public class FormException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMessages;

	public FormException() {
		super("�û�������");
	}

	public FormException(Map<String, String> errMsgs) {
		super("�û�������");
		this.errorMessages = errMsgs;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
