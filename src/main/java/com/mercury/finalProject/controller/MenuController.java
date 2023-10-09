package com.mercury.finalProject.controller;

import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.Menu;
import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.http.Response;
import com.mercury.finalProject.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu){
        System.out.println("Saving: " + menu);
        return menuService.saveMenu(menu);
    }

    @DeleteMapping("{id}")
    public Response deleteMenuById(@PathVariable int id){
        System.out.println("Deleting menu: " + id);
        return menuService.deleteMenuById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenuById(@PathVariable int id, @RequestBody Menu menu){
        Menu oldMenu = menuService.getMenuById(id);
        if(oldMenu != null){
            menu.setId(id);
            return menuService.saveMenu(menu);
        }else{
            throw new RuntimeException("Menu not found.");
        }
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable int id){
        return menuService.getMenuById(id);
    }

    @GetMapping("/name/{name}")
    public Menu getMenuByName(@PathVariable String name){
        return menuService.getMenuByName(name);
    }

    @GetMapping("/menuCategory/{menuCategory}")
    public List<Menu> getMenusByMenuCategory(@PathVariable String menuCategory){
        return menuService.getMenusByMenuCategory(menuCategory);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Menu> getMenusByRestaurantId(@PathVariable int restaurantId) {
        return menuService.getMenusByRestaurantId(restaurantId);
    }

    @PostMapping("/restaurant")
    public List<Menu> getMenusByRestaurant(@RequestBody Restaurant restaurant) {
        return menuService.getMenusByRestaurant(restaurant);
    }

    @GetMapping
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @PostMapping("/{menuId}/flavor")
    public ResponseEntity<Menu> addFlavorToMenu(@PathVariable int menuId, @RequestBody Flavor flavor) {
        return menuService.addFlavorToMenu(menuId, flavor);
    }

    @DeleteMapping("/{menuId}/flavor/{flavorId}")
    public ResponseEntity<Menu> deleteFlavorFromMenu(@PathVariable int menuId, @PathVariable int flavorId) {
        return menuService.deleteFlavorFromMenu(menuId, flavorId);
    }

    @PutMapping("/{menuId}/flavor")
    public ResponseEntity<Menu> updateFlavorFromMenu(@PathVariable int menuId, @RequestBody Flavor updatedFlavor) {
        return menuService.updateFlavorFromMenu(menuId, updatedFlavor);
    }

    @GetMapping("/{menuId}/flavors")
    public List<Flavor> getAllFlavorsFromMenuId(@PathVariable int menuId) {
        return menuService.getAllFlavorsFromMenuId(menuId);
    }

}
