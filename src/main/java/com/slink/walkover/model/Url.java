package com.slink.walkover.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Url {
	private String lng; //long url
	@Id
	private String srt; //short url
	private Integer count;
	
	public Url() {
		super();
	}
	
	
	
	public Url(String lng, String srt, Integer count) {
		super();
		this.lng = lng;
		this.srt = srt;
		this.count = count;
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
	
	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	@Override
	public String toString() {
		return "Url [lng=" + lng + ", srt=" + srt + ", count=" + count + "]";
	}
	
	
}
