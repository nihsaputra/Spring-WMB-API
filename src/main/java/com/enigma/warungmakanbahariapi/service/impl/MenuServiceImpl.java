package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.Menu;
import com.enigma.warungmakanbahariapi.model.request.MenuRequest;
import com.enigma.warungmakanbahariapi.repository.MenuRepository;
import com.enigma.warungmakanbahariapi.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu create(MenuRequest request) {
        Menu builMenu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        return menuRepository.save(builMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu update(Menu request) {
        Menu menuId = getById(request.getId());
        Menu builMenu = Menu.builder()
                .id(menuId.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build();
        return menuRepository.save(builMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(String id) {
        Menu menu = getById(id);
        menuRepository.delete(menu);
        return "OK";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu getById(String id) {
        Optional<Menu> menuId = menuRepository.findById(id);
        if (!menuId.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"menu not found");
        return menuId.get();
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> findAll = menuRepository.findAll();
        return findAll;
    }


}
