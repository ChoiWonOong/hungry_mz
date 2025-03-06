package data.mapper;

import data.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    RestaurantDto getRestaurantById(int id);
    List<RestaurantDto> getRestaurantList();
    void insertRestaurant(RestaurantDto dto);
    RestaurantDto getRestaurantByTitle(String title);
    int getCountByTitle(String title);
}
