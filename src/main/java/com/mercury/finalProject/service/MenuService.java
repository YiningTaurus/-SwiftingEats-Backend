package com.mercury.finalProject.service;

import com.mercury.finalProject.bean.Flavor;
import com.mercury.finalProject.bean.Menu;
import com.mercury.finalProject.bean.Restaurant;
import com.mercury.finalProject.dao.MenuDao;
import com.mercury.finalProject.http.Response;
import org.apache.activemq.openwire.v1.ResponseMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

//    public Response saveMenu(Menu menu){
//        try {
//            menuDao.save(menu);
//            return new Response(true, "Menu saving successfully!");
//        }catch (Exception e){
//            return new Response(false, "Menu saving failed. " + e.getMessage());
//        }
//    }

    public ResponseEntity<Menu> saveMenu(Menu menu) {
        try {
            Menu savedMenu = menuDao.save(menu);
            return new ResponseEntity<>(savedMenu, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Menu saving failed. " + e.getMessage());
        }
    }

    public Response deleteMenuById(int id){
        try {
            menuDao.deleteById(id);
            return new Response(true, "Menu deleting successfully!");
        }catch (Exception e){
            throw new RuntimeException("Menu deleted failed!" + e.getMessage());
        }
    }

    public Menu getMenuById(int id){
        return menuDao.findById(id).orElse(null);
    }

    public Menu getMenuByName(String name){
        return menuDao.findMenuByName(name);
    }

    public List<Menu> getMenusByMenuCategory(String menuCategory){
        return menuDao.findMenusByMenuCategory(menuCategory);
    }

    public List<Menu> getMenusByRestaurantId(int restaurantId){
        return menuDao.findMenusByRestaurantId(restaurantId);
    }

    public List<Menu> getMenusByRestaurant(Restaurant restaurant){
        return menuDao.findMenusByRestaurant(restaurant);
    }

    public List<Menu> getAllMenus(){
        return menuDao.findAll();
    }

    public ResponseEntity<Menu> addFlavorToMenu(int menuId, Flavor flavor) {
        try {
            Menu menu = getMenuById(menuId);
            if (menu != null) {
                menu.getFlavors().add(flavor);
                Menu updatedMenu = menuDao.save(menu);
                return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
            } else {
                throw new RuntimeException("Menu not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error adding flavor to menu. " + e.getMessage());
        }
    }

    public ResponseEntity<Menu> deleteFlavorFromMenu(int menuId, int flavorId) {
        try {
            Menu menu = getMenuById(menuId);
            if (menu != null) {
                Flavor flavorToRemove = null;
                for (Flavor flavor : menu.getFlavors()) {
                    if (flavor.getId() == flavorId) {
                        flavorToRemove = flavor;
                        break;
                    }
                }
                if (flavorToRemove != null) {
                    menu.getFlavors().remove(flavorToRemove);
                    Menu updatedMenu = menuDao.save(menu);
                    return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
                } else {
                    throw new RuntimeException("Flavor not found in menu.");
                }
            } else {
                throw new RuntimeException("Menu not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error removing flavor from menu. " + e.getMessage());
        }
    }



    public ResponseEntity<Menu> updateFlavorFromMenu(int menuId, Flavor updatedFlavor) {
        try {
            Menu menu = getMenuById(menuId);
            if (menu != null) {
                boolean isFound = false;
                List<Flavor> flavors = menu.getFlavors();
                for (int i = 0; i < flavors.size(); i++) {
                    if (flavors.get(i).getId() == updatedFlavor.getId()) {
                        flavors.set(i, updatedFlavor);
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    menu.setFlavors(flavors);
                    Menu updatedMenu = menuDao.save(menu);
                    return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
                } else {
                    throw new RuntimeException("Flavor not found in menu.");
                }
            } else {
                throw new RuntimeException("Menu not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating flavor in menu. " + e.getMessage());
        }
    }

    public List<Flavor> getAllFlavorsFromMenuId(int menuId) {
        Menu menu = getMenuById(menuId);
        if (menu != null) {
            return menu.getFlavors();
        } else {
            return new ArrayList<>();
        }
    }

}
