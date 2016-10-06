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

@RestController
public class ManufacturerMSLBController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces =MediaType.APPLICATION_JSON_VALUE)
	public Resources<Manufacturer> getData(){
		ServiceInstance instance = 
		loadBalancerClient.choose("manufacturer-microservice");
		
		System.out.println("Port========:"+instance.getPort());
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> entity = new ResponseEntity<String>(
				String.valueOf(instance.getPort()), HttpStatus.OK);
		
		Resources<Manufacturer> resources = null;
		try {
			resources = (Resources<Manufacturer>)( restTemplate.getForObject(new URI(instance.getUri().toString().
					concat("/datarest")), 
					(Object.class)));
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resources;
	}
	
}
