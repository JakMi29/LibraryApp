package com.example.OpinionService.controller;

import com.example.OpinionService.business.OpinionService;
import com.example.OpinionService.domain.request.AddOpinionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opinion")
@AllArgsConstructor
public class OpinionController {

    private final OpinionService OpinionService;

    @PostMapping("/addOpinion")
    public ResponseEntity<Void> AddOpinion(
            @RequestBody AddOpinionRequest request
    ) {
        OpinionService.addOpinion(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
