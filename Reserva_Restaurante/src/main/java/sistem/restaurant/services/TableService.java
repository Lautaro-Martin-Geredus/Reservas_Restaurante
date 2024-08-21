package sistem.restaurant.services;

import org.springframework.stereotype.Service;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.dtos.table.TableeDto;

@Service
public interface TableService
{
    TableeDto createTable(String restaurantName, NewTableDto tableeDto);

    TableeDto updateTable(String restaurantName, NewTableDto tableeDto);

    boolean deleteTable(Long id);
}
