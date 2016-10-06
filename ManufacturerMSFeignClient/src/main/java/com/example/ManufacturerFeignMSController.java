package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Manufacturer;

@RestController
public class ManufacturerFeignMSController {

	@Autowired
	private ManufacturerFeignClient feignClient;
	
	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces =MediaType.APPLICATION_JSON_VALUE)
	public Resources<Manufacturer> getData(){
		return feignClient.findAll();
	}
	
	@RequestMapping(value="/getByDate", method=RequestMethod.GET, 
			produces =MediaType.APPLICATION_JSON_VALUE)
	public Resources<Manufacturer> getByDate(@RequestParam("startDate")
	         @DateTimeFormat(iso=ISO.DATE) Date date){
		return feignClient.findByStartDateBefore(date);
	}
}
