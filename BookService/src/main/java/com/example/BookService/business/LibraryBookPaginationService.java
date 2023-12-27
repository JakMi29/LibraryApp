package com.example.BookService.business;

import com.example.BookService.business.dao.LibraryBookDAO;
import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LibraryBookPaginationService {
    private final LibraryBookDAO bookDAO;

    @Transactional
    public List<LibraryBookEntity> paginateAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAll(pageable);
    }

    @Transactional
    public List<LibraryBookEntity> paginateAllByCategory(int pageNumber, int pageSize, String category) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAllByCategory(pageable, category);
    }

    @Transactional
    public List<LibraryBookEntity> paginateAvailable(int pageNumber, int pageSize) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAvailable(pageable);
    }

    @Transactional
    public List<LibraryBookEntity> paginateAvailableByCategory(int pageNumber, int pageSize, String category) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAvailableByCategory(pageable, category);
    }
}
