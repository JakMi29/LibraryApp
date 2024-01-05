package com.example.OpinionService.controller;

import com.example.OpinionService.business.OpinionService;
import com.example.OpinionService.domain.request.AddOpinionRequest;
import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opinion")
@AllArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/addOpinion")
    public ResponseEntity<Void> AddOpinion(
            @RequestBody AddOpinionRequest request
    ) {
        opinionService.addOpinion(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/book/{bookId}/{pageNumber}/{pageSize}")
    public ResponseEntity<List<OpinionEntity>> AddOpinion(
            @PathVariable Integer bookId,
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(opinionService.findOpinionByBook(bookId,pageNumber,pageSize));
    }
}
