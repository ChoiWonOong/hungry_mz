package data.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("RestaurantDto")
@Data
public class RestaurantDto {
    private int restaurantId;
    private String title;
    private String location;
    private String image;
    private Timestamp registeredDate;
}
