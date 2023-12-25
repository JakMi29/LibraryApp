package com.example.OpinionService.infrastructure.database.repository;

import com.example.OpinionService.business.dao.OpinionDAO;
import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import com.example.OpinionService.business.dao.OpinionDAO;
import com.example.OpinionService.infrastructure.database.repository.jpa.OpinionJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class OpinionRepository implements OpinionDAO {

    private final OpinionJpaRepository repository;


    @Override
    public void save(OpinionEntity Opinion) {
        repository.save(Opinion);
    }


    @Override
    public List<OpinionEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().toList();
    }
}
