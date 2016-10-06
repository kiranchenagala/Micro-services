package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Manufacturer;

@RestController
public class OrderMSController {

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/mfrPost", method=RequestMethod.GET)
	public ResponseEntity<String> getData(){
		List<ServiceInstance> list=client.
				getInstances("manufacturer-microservice");
		if(null!=list && list.size() > 0){
			URI uri = list.get(0).getUri();
			if(null!=uri){
				Manufacturer manufacturer1 = new 
						Manufacturer(123123, new Date(), "test1");
				Manufacturer manufacturer2 = new 
						Manufacturer(35326, new Date(), "test2");
				List<Manufacturer> mFrList = new LinkedList<Manufacturer>();
				mFrList.add(manufacturer1);
				mFrList.add(manufacturer2);
				try {
					try {
						return (new RestTemplate()).postForEntity(new URI(uri.toString().concat("/datarest")),
								mFrList.get(0), String.class);
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (RestClientException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
