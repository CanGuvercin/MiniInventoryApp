package com.guvercin.MiniInventoryApp.Controller;


import com.guvercin.MiniInventoryApp.Model.Item;
import com.guvercin.MiniInventoryApp.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItems (){
        return itemRepository.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @PutMapping
    public Item updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found.."));
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        item.setQuantity(itemDetails.getQuantity());
        return itemRepository.save(item);
    }

    @DeleteMapping
    public void deleteItem(@PathVariable Long id){
        itemRepository.deleteById(id);
    }


}
