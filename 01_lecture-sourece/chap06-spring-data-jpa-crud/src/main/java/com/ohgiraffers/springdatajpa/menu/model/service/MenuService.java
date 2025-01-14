package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;
    // Bean 으로 등록한 modelmapper 의존성 주입
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    /* 1. 메뉴 코드로 특정 메뉴 조회하기 */
    public MenuDTO findMenuByMenuCode(int menuCode) {

        Menu foundMenu = repository.findById(menuCode)
                                    // 잘못 된 파라미터가 넘겨왔을 때 예외처리 필수
                                   .orElseThrow(IllegalArgumentException::new);

        // entity -> dto
        // map(변환 대상, 변환 할 타입)
        return modelMapper.map(foundMenu, MenuDTO.class);
    }

    /* 페이징 처리하지 않은 메뉴 리스트 조회하기 */
    public List<MenuDTO> findMenuList() {

//        List<Menu> menuList = repository.findAll();   정렬 없는 findAll
        List<Menu> menuList = repository.findAll(Sort.by("menuPrice").descending());    // 가격순으로 정렬

        // stream : 컬렉션(List 등등) 데이터를 처리하기 위해 나열
        return menuList.stream()
                // map : 스트림화 된 데이터를 꺼내 매핑 및 변환
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                // collect : 스트림화 된 데이터를 다시 List 로 변환
                .collect(Collectors.toList());

    }

    /* 페이징 처리를 한 메뉴 전체 조회 */
    public Page<MenuDTO> findMenuListByPaging(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );

        Page<Menu> menuList = repository.findAll(pageable);

        return menuList.map(
                menu -> modelMapper.map(menu, MenuDTO.class)
        );
    }

    public List<MenuDTO> findByMenuPrice(int menuPrice) {

        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

        return menuList.stream()
                       .map(menu -> modelMapper.map(menu, MenuDTO.class))
                       .collect(Collectors.toList());

    }

    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList
                = categoryRepository.findAllCategory();

        return categoryList.stream()
                           .map(category -> modelMapper.map(category, CategoryDTO.class))
                           .collect(Collectors.toList());
    }

    // DML 구문이기 때문에 @Transaction
    @Transactional
    public void registNewMenu(MenuDTO newMenu) {

        // 지금까지는 Entity 를 DTO 로 변환했다면
        // DML 구문에서는 DTO 타입을 Entity 로 변환을 해야
        // Persistence Context == JPA 가 관리를 해준다.
        repository.save(modelMapper.map(newMenu, Menu.class));

    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {

        /* update 는 엔티티를 특정해서 필드의 값을 변경해주면 된다. */
        /* JPA는 변경 감지 기능이 있다.
        *   따라서 하나의 엔티티를 특정해서 필드 값을 변경하면
        *   변경된 값으로 flush (반영) 을 해준다.
        *  */

        // 엔티티 찾기(특정)
        Menu foundMenu = repository.findById(modifyMenu.getMenuCode())
                                   .orElseThrow(IllegalArgumentException::new); // menuCode가 없으면 예외 발생시킬거야!

        System.out.println("찾은 Entity 값 = " + foundMenu);

        /* 1. setter 를 통해 update 기능 - (지양한다.) */
//        foundMenu.setMenuName(modifyMenu.getMenuName());

        /* 2. @Builder 를 통해 update 기능 */
//        foundMenu = foundMenu.toBuilder()
//                             .menuName(modifyMenu.getMenuName())
//                             .build();
//        // build 를 통해서 foundMenu 새롭게 탄생 시켰으니
//        // save 메소드를 통해 JPA 에게 전달
//        repository.save(foundMenu);

        /* 3. Entity 내부에 Builder 패턴을 구현 */
        foundMenu = foundMenu.menuName(modifyMenu.getMenuName())
                             .builder();

        repository.save(foundMenu);

        System.out.println("setter 사용 후 foundMenu = " + foundMenu);
    }

    @Transactional
    public void deleteMenu(int menuCode) {

        repository.deleteById(menuCode);

    }
}
