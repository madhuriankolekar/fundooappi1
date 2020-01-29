package com.bridgelabz.fundoonotes.response;

public class Response {
	
	private Integer statuscode;
	private String msg;

	public Integer getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Response(Integer statuscode,String msg) {
	this.statuscode=statuscode;
	this.msg=msg;
	}
}
