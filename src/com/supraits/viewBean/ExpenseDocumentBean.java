package com.supraits.viewBean;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseDocumentBean {
	private String docId;
	private String docName;
	private String docSize;
	private String uploadedby;
	@DateTimeFormat
	private String uploadedon;
	private String docFlag;
	private String doctype;
	private String document;
	private byte[] data;
	
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocSize() {
		return docSize;
	}
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}
	public String getUploadedby() {
		return uploadedby;
	}
	public void setUploadedby(String uploadedby) {
		this.uploadedby = uploadedby;
	}
	public String getUploadedon() {
		return uploadedon;
	}
	public void setUploadedon(String uploadedon) {
		this.uploadedon = uploadedon;
	}
	public String getDocFlag() {
		return docFlag;
	}
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
}
