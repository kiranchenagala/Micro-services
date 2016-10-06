package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;

@RestController
public class ManufacturerMSCBController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces =MediaType.APPLICATION_JSON_VALUE)
	
	public Resources<Manufacturer> getData(){
		return manufacturerService.findAll();
	}
	
}
