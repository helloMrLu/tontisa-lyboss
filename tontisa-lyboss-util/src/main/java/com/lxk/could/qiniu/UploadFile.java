package com.lxk.could.qiniu;

import java.io.InputStream;
/**
 * 上传文件信息对象
 * @author sam
 *
 */
public class UploadFile {
	private String key;
	private String extname;
	private String filename;
	private long size;
	private byte[] filedata;
	private UploadStatus status = UploadStatus.SUCCESS;
	
	public UploadStatus getStatus() {
		return status;
	}
	public void setStatus(UploadStatus status) {
		this.status = status;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getExtname() {
		return extname;
	}
	public void setExtname(String extname) {
		this.extname = extname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public void setFileData(InputStream ins) {
		
	}
	
	public void setFileData(byte[] data) {
		this.filedata = data;
	}
	
	public byte[] getFileData() {
		return filedata;
	}
}