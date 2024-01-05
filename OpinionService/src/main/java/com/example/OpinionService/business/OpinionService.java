package com.example.OpinionService.business;

import com.example.OpinionService.business.dao.OpinionDAO;
import com.example.OpinionService.domain.request.AddOpinionRequest;
import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class OpinionService {
    private final OpinionDAO OpinionDAO;
    private final OpinionPaginationService opinionPaginationService;

    public void addOpinion(AddOpinionRequest request) {
        OpinionEntity opinion = OpinionEntity.builder()
                .bookId(request.getBookId())
                .rating(request.getRating())
                .text(request.getComment())
                .build();
        OpinionDAO.save(opinion);
    }

    public List<OpinionEntity> findOpinionByBook(Integer bookId, Integer pageNumber, Integer pageSize) {
return opinionPaginationService.paginateByBookId(bookId,pageNumber,pageSize);
    }
}
