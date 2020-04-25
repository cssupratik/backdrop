package backdropv1.exception;

import java.util.Date;

public class ExceptionDetails {
	private Date timestamp;
	private String msg;
	private String msgDetails;
	
	public ExceptionDetails() {
		super();
	}
	public ExceptionDetails(Date timestamp, String msg, String msgDetails) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.msgDetails = msgDetails;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgDetails() {
		return msgDetails;
	}
	public void setMsgDetails(String msgDetails) {
		this.msgDetails = msgDetails;
	}
	
}
