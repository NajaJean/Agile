package core;

public class NotifyObject {
	
	// Different codes for different client notifications
	private int notifyCode;
	// Message could be container is outside threshold, 
	// container has arrived at location,
	// or container did not make it to location (initial ideas)
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
