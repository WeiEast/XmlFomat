package com.burp;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("url") 
public class RequestBean {
	
	@XStreamAsAttribute()
	private String id;
	@XStreamAsAttribute()
	private String url;
	@XStreamAsAttribute()
	private String method;
	
	private String description;
	private List header;
	private List body;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List getHeader() {
		return header;
	}
	public void setHeader(List header) {
		this.header = header;
	}
	public List getBody() {
		return body;
	}
	public void setBody(List body) {
		this.body = body;
	}
	
}
