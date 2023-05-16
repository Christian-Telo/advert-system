package com.web.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.model.Advert;
import com.web.app.repository.AdvertRepository;

@Service
public class AdvertService {

	@Autowired
	private AdvertRepository repo;
	
	public Advert createAdvert(Advert advert) throws IOException {
		return repo.save(advert);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public List<Advert> allAdvert() {
		return repo.findAll();
	}
	
	public void updateAdvert(Advert advert) {
		repo.save(advert);
	}
}
