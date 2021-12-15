package com.slink.walkover.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slink.walkover.model.Url;

@Repository
public interface UrlRepo extends JpaRepository<Url, String>{
}
