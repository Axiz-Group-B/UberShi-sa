package jp.co.shisa.entity;

import java.sql.Timestamp;

public class Log {
	private Integer logId;
	private Integer orderId;
	private Integer status;
	private Timestamp dateTime;
	private Integer checkFlag;

	public Log() {

	}

	public Log(Integer logId, Integer orderId, Integer status, Timestamp dateTime, Integer checkFlag) {
		super();
		this.logId = logId;
		this.orderId = orderId;
		this.status = status;
		this.dateTime = dateTime;
		this.checkFlag = checkFlag;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}





}
