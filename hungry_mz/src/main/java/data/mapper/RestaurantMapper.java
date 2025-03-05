package data.mapper;

import data.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantMapper {
    RestaurantDto getRestaurantById(int id);
}
