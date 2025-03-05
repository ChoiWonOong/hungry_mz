package com.menu.controller;

import com.menu.service.MenuService;
import com.naver.storage.NcpObjectStorageService;
import data.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @Autowired
    private NcpObjectStorageService storageService;

    @GetMapping("/menu/list")
    public List<MenuDto> getMenuListOrderByPriceAsc(){
        return menuService.getMenuListOrderByPriceAsc();
    }
    @GetMapping("/menu/{menuId}")
    public MenuDto getMenuByMenuId(@PathVariable int menuId){
        return menuService.getMenuByMenuId(menuId);
    }
    @GetMapping("/menu/restaurant/{restaurantId}")
    public List<MenuDto> getMenuListByRestaurantId(@PathVariable int restaurantId){
        return menuService.getMenuListByRestaurantId(restaurantId);
    }
}
