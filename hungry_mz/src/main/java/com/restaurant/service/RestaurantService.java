package com.restaurant.service;

import data.dto.RestaurantDto;
import data.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantMapper mapper;
    public RestaurantDto getRestaurantById(int id){
        return mapper.getRestaurantById(id);
    }
    public List<RestaurantDto> getRestaurantList(){
        return mapper.getRestaurantList();
    }
    public void insertRestaurant(RestaurantDto dto){
        mapper.insertRestaurant(dto);
    }
    public RestaurantDto getRestaurantByTitle(String title){
        return mapper.getRestaurantByTitle(title);
    }
    public boolean isExistByTitle(String title){
        return mapper.getCountByTitle(title)!=0?true:false;
    }
}
