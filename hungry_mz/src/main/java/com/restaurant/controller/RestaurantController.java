package com.restaurant.controller;

import com.restaurant.service.RestaurantService;
import data.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/")
    public List<RestaurantDto>getRestaurantList(){
        return restaurantService.getRestaurantList();
    }
    @GetMapping("/{restaurantId}")
    public RestaurantDto getRestaurantById(@PathVariable int restaurantId){
        return restaurantService.getRestaurantById(restaurantId);
    }
}
