package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.Menu;
import com.enigma.warungmakanbahariapi.model.request.MenuRequest;

import java.util.List;

public interface MenuService {
    Menu create(MenuRequest request);
    Menu update(Menu request);
    String delete(String id);
    Menu getById(String id);
    List<Menu> getAll();
}
