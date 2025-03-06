package com.webCrawler.controller;

import com.webCrawler.service.RestaurantCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class WebCrawlerController {
    private RestaurantCrawler restaurantCrawler;
    public WebCrawlerController(RestaurantCrawler restaurantCrawler){
        this.restaurantCrawler = restaurantCrawler;
    }
    //@GetMapping("/webcrawl")
    public void crawling() throws IOException {
        restaurantCrawler.crawlWebsite();
    }
}
