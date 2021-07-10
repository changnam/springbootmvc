package com.honsoft.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.honsoft.web.entity.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Car>{

}
