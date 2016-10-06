package com.example;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Manufacturer;

@FeignClient("manufacturer-microservice")
public interface ManufacturerFeignClient {
	
	@RequestMapping(value = "/datarest", method = RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	Resources<Manufacturer> findAll();
	
	@RequestMapping(value = "/datarest/search/findByStartDateBefore/{date}", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	Resources<Manufacturer> findByStartDateBefore(@RequestParam("date") @DateTimeFormat(iso=ISO.DATE) Date date);

}
