package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.Menu;
import com.enigma.warungmakanbahariapi.model.request.MenuRequest;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/menus")
public class MenuController {
    private final MenuService menuService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MenuRequest request){
        Menu createMenu = menuService.create(request);
        WebResponse<Menu> response  = WebResponse.<Menu>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new menu")
                .data(createMenu)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Menu request){
        Menu updateMenu = menuService.update(request);
        WebResponse<Menu> response = WebResponse.<Menu>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly update menu")
                .data(updateMenu)
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String deleteMenu = menuService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly delete menu")
                .data(deleteMenu)
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Menu findMenuById = menuService.getById(id);
        WebResponse<Menu> response = WebResponse.<Menu>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get menu by id")
                .data(findMenuById)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Menu> menuAll = menuService.getAll();
        WebResponse<List<Menu>> response = WebResponse.<List<Menu>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all menu")
                .data(menuAll)
                .build();
        return ResponseEntity.ok(response);
    }

}
