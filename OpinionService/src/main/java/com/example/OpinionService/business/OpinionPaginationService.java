package com.example.OpinionService.business;

import com.example.OpinionService.business.dao.OpinionDAO;
import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionPaginationService {
    private final OpinionDAO opinionDAO;

    @Transactional
    public List<OpinionEntity> paginateByBookId(Integer bookId, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return opinionDAO.findByBook(bookId, pageable);
    }


}
