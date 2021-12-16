package com.slink.walkover.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slink.walkover.dao.UrlRepo;
import com.slink.walkover.model.Url;

@Service
public class UrlService {
	
	@Autowired
	UrlRepo repo;
	
	public Url createShortUrl(String lng) {
		String srt = "http://localhost:8080/slink/"+UUID.randomUUID().toString().substring(0,4)+(100+Math.round(Math.random()*1000));
		Url url = new Url(lng, srt, 0);
		repo.save(url);
		return url;
	}
	
	public Url getUrl(String srt) {
		Optional<Url> o = repo.findById(srt);
		if(o.isPresent()) {
			Url url = o.get();
			System.out.println(url);
			url.setCount(url.getCount()+1);
			repo.save(url);
			return url;
		}
		return null;	
	}

}
