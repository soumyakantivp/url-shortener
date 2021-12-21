package com.slink.walkover.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.slink.walkover.model.Url;
import com.slink.walkover.security.CustomUserDetails;
import com.slink.walkover.service.UrlService;


@Controller
public class UrlController {
	@Autowired
	UrlService service;

	@RequestMapping(value = "/home",method =RequestMethod.GET)
	public String index(ModelMap model){
		//getLoggedInUserName(model);
		return "home";
	}
	
	@RequestMapping(value = "/create-short",method =RequestMethod.GET)
	public String createShort(HttpServletRequest r, ModelMap modelmap, Model model, @RequestParam("url")String url){
		CustomUserDetails cus = getLoggedInUserName(modelmap);
		List<Url> list = cus.getUrls();
		if(url != null) {
			Url newUrl = service.createShortUrl(r, url);
			if(newUrl != null) {
				list.add(newUrl);
				cus.setUrls(list);
			}
		}
		else
			System.out.println("request denied");
		model.addAttribute("urls", list);
		return "home";
	}
	
	@RequestMapping(value = "/slink/{srt}",method =RequestMethod.GET)
	public void getLong(@PathVariable String srt,HttpServletRequest r, HttpServletResponse response) throws IOException{
		String lngUrl = "def";
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(r)
		        .replacePath(null)
		        .build()
		        .toUriString();
		Url url = service.getUrl(baseUrl+"/slink/"+srt);
		if(url != null) {
			lngUrl = url.getLng();
		}
			
		
		System.out.println(lngUrl);
		response.sendRedirect(lngUrl);
	}
	
	private CustomUserDetails getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof CustomUserDetails) {
			//System.out.println(((CustomUserDetails) principal).getUrls());
			return ((CustomUserDetails) principal);
		}

		return null;
	}
}
