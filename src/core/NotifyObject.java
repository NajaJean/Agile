package core;

public class NotifyObject {
	
	private int notifyCode;
	private String notifyMessage;
	
	public NotifyObject(int notifyCode, String notifyMessage) {
		this.notifyCode = notifyCode;
		this.notifyMessage = notifyMessage;
	}
	
	public int getNotifyCode() {
		return notifyCode;
	}
	
	public void setNotifyCode(int notifyCode) {
		this.notifyCode = notifyCode;
	}
	
	public String getNotifyMessage() {
		return notifyMessage;
	}
	
	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}
}
