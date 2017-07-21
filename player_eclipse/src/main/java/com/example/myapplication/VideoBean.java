package com.example.myapplication;

import java.io.Serializable;
/**
 * 实体类
 */
public class VideoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public VideoBean(String url) {
		super();
		this.url = url;
	}

}
