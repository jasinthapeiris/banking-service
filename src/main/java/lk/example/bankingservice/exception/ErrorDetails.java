package lk.example.bankingservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String path;
	private HttpStatus status;
	private String code;
	
	public ErrorDetails(Date timestamp, String message, String path, HttpStatus status,String code) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.status =status;
		this.code =code;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}