package data.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("MenuDto")
public class MenuDto {
    private int menuId;
    private int restaurantId;
    private String name;
    private String image;
    private Timestamp registeredDate;
}
