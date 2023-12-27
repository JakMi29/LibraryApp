package com.example.BookService.business;

import com.example.BookService.business.dao.ShopBookDAO;
import com.example.BookService.infrastructure.database.entity.ShopBookEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopBookPaginationService {
    private final ShopBookDAO bookDAO;

    @Transactional
    public List<ShopBookEntity> paginateAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAll(pageable);
    }

    @Transactional
    public List<ShopBookEntity> paginateAllByCategory(int pageNumber, int pageSize, String category) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAllByCategory(pageable, category);
    }

    @Transactional
    public List<ShopBookEntity> paginateAvailable(int pageNumber, int pageSize) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAvailable(pageable);
    }

    @Transactional
    public List<ShopBookEntity> paginateAvailableByCategory(int pageNumber, int pageSize, String category) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAvailableByCategory(pageable, category);
    }
}
