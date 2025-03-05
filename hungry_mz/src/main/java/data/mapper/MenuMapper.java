package data.mapper;

import data.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    void insertMenu(MenuDto dto);
    List<MenuDto> getMenusByRestaurantId(int restaurantId);
    MenuDto getMenuByMenuId(int menuId);
    void deleteMenuByMenuId(int menuId);
    void updateMenu(MenuDto dto);
}
