package com.slink.walkover.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.slink.walkover.model.Url;
import com.slink.walkover.service.UrlService;


@Controller
public class UrlController {
	@Autowired
	UrlService service;

	@RequestMapping(value = "/home",method =RequestMethod.GET)
	public String index(){
		
		return "home";
	}
	
	@RequestMapping(value = "/create-short",method =RequestMethod.GET)
	public String createShort(Model model, @RequestParam("url")String url){
		List<Url> list = new ArrayList<Url>();
		if(url != null) {
			Url newUrl = service.createShortUrl(url);
			if(newUrl != null)
				list.add(newUrl);
		}
		else
			System.out.println("request denied");
		model.addAttribute("urls", list);
		return "home";
	}
	
	@RequestMapping(value = "/slink/{srt}",method =RequestMethod.GET)
	public void getLong(@PathVariable String srt, HttpServletResponse response) throws IOException{
		String lngUrl = "def";
		Url url = service.getUrl("http://localhost:8080/slink/"+srt);
		if(url != null) {
			lngUrl = url.getLng();
		}
			
		
		System.out.println(lngUrl);
		response.sendRedirect(lngUrl);
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}
}
