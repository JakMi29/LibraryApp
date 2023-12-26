package com.example.BookService.business;

import com.example.BookService.business.dao.BookDAO;
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
public class BookPaginationService {
    private final BookDAO bookDAO;

    @Transactional
    public List<LibraryBookEntity> paginateAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookDAO.findAll(pageable);
    }
}
