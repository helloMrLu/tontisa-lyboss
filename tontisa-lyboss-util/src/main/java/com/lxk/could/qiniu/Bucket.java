package com.lxk.could.qiniu;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringStyle;

import com.tontisa.common.lang.Objects;
import com.tontisa.common.lang.Strings;
import com.tontisa.common.lang.Throwables;

/**
 * 七牛空间配置
 * @author Sam
 *
 */
public class Bucket implements Serializable {
	
	 
	private static final long serialVersionUID = 4010980051139597448L;
	private String name;
	private String dn;
	private Boolean isPrivate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getFileUrl(String key) {
		if (Strings.isBlank(dn)) {
			Throwables.makeThrow("the bucket dn is empty!");
		}
		if (dn.trim().endsWith("/"))
			return dn + key;
		else 
			return dn + "/" + key;
	}
	
	public String toString() {
		return Objects.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public static void main(String[] args) { 
	}
}
