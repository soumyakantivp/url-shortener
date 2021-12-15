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
	
	public void createShortUrl(String lng) {
		String srt = UUID.randomUUID().toString().substring(0,4)+(100+Math.round(Math.random()*1000));
		Url url = new Url(lng, srt);
		repo.save(url);
	}
	
	public Url getUrl(String srt) {
		Optional<Url> o = repo.findById(srt);
		if(o.isPresent()) {
			System.out.println(o.get());
			return o.get();
		}
		return null;	
	}

}
