package com.flintintoe.weekly4.menucontroller;

import com.flintintoe.weekly4.menu.Menu;
import com.flintintoe.weekly4.menu.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public MenuDto createMenu(MenuDto menuDto) {
        Menu menu = menuDto.toEntity();
        Menu savedMenu = menuRepository.save(menu);
        return MenuDto.of(savedMenu);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(MenuDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MenuDto> getMenuById(int id) {
        return menuRepository.findById(id)
                .map(MenuDto::of);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getMenuByCategory(String category) {
        return menuRepository.findByCategory(category).stream()
                .map(MenuDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<MenuDto> updateMenu(int id, MenuDto menuDto) {
        return menuRepository.findById(id)
                .map(existingMenu -> {
                    existingMenu.update(menuDto.getName(), menuDto.getCategory(), menuDto.getDescription(), menuDto.getPrice());
                    return MenuDto.of(menuRepository.save(existingMenu));
                });
    }

    @Transactional
    public boolean deleteMenu(int id) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menuRepository.delete(menu);
                    return true;
                })
                .orElse(false);
    }
}
