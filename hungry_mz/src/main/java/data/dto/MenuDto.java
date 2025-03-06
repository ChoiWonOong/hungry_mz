package data.dto;


import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("MenuDto")
public class MenuDto {
    private int menuId;
    private int restaurantId;
    private int price;
    private String name;
    private String image ="";
    private String description="";
    private Timestamp registeredDate;

    public MenuDto(int restaurantId, String name, int price, String image, String description) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
