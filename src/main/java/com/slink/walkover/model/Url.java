package com.slink.walkover.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Url {
	private String lng; //long url
	@Id
	private String srt; //short url
	
	
	public Url() {
		super();
	}
	
	
	
	public Url(String lng, String srt) {
		super();
		this.lng = lng;
		this.srt = srt;
	}



	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getSrt() {
		return srt;
	}
	public void setSrt(String srt) {
		this.srt = srt;
	}
	
	@Override
	public String toString() {
		return "Url [lng=" + lng + ", srt=" + srt + "]";
	}
	
	
	
	
}
