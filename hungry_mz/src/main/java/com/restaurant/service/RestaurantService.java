package com.restaurant.service;

import data.dto.RestaurantDto;
import data.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantMapper mapper;
    public RestaurantDto getRestaurantById(int id){
        return mapper.getRestaurantById(id);
    }
    public List<RestaurantDto> getRestaurantList(){
        return mapper.getRestaurantList();
    }
}
