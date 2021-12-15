package com.slink.walkover.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slink.walkover.model.Url;
import com.slink.walkover.service.UrlService;


@RestController
public class UrlController {
	@Autowired
	UrlService service;

	@RequestMapping(value = "/slink/create-short",method =RequestMethod.POST)
	public void createShort(@RequestParam("url")String url){
		String lng = "";
		lng = url;
		if(lng != null)
			service.createShortUrl(lng);
		else
			System.out.println("request denied");
	}
	
	@RequestMapping(value = "/slink/{srt}",method =RequestMethod.GET)
	public Url getLong(@PathVariable String srt, HttpServletResponse response) throws IOException{
		String lngUrl = "def";
		Url url = service.getUrl(srt);
		if(url != null) {
			lngUrl = url.getLng();
		}
			
		
		System.out.println(lngUrl);
		response.sendRedirect(lngUrl);
		return url;
	}
}
