package com.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.app.model.Advert;

public interface AdvertRepository extends JpaRepository<Advert, Integer>{

}
