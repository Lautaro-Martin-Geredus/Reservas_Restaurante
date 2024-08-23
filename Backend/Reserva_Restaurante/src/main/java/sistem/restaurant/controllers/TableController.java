package sistem.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistem.restaurant.dtos.table.NewTableDto;
import sistem.restaurant.dtos.table.TableeDto;
import sistem.restaurant.services.TableService;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController
{
    @Autowired
    private TableService tableService;

    @PostMapping("/{name}")
    public ResponseEntity<TableeDto> createTable(@PathVariable String name, @RequestBody NewTableDto tableeDto)
    {
        return ResponseEntity.ok(tableService.createTable(name, tableeDto));
    }

    @GetMapping("")
    public ResponseEntity<List<NewTableDto>> getAllTables()
    {
        return ResponseEntity.ok(tableService.getAllTables());
    }
}
