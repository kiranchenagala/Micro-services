package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.model.Manufacturer;

@RepositoryRestResource(path="/datarest")
public interface ManufacturerRepository extends MongoRepository<Manufacturer, Integer> {

//	@Query(value="{'startDate':?0}", fields="{'startDate':0}")
	
	//http://localhost:8888/sg/daterest/search/findByStartDateBefore?startDate=2016-06-09
	List<Manufacturer> findByStartDateBefore(@Param("startDate") @DateTimeFormat(iso=ISO.DATE) Date startDate);
}
