package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import com.bank.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller для {@link HistoryEntity}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService service;

    /**
     * @param id технический идентификатор {@link HistoryEntity}
     * @return {@link ResponseEntity} c {@link HistoryDto} и HttpStatus OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<HistoryDto> read(@PathVariable Long id) {
        return new ResponseEntity<>(service.readById(id), HttpStatus.OK);
    }

    /**
     * @param id список технических идентификаторов {@link HistoryEntity}
     * @return {@link ResponseEntity} c {@link HistoryDto} и HttpStatus OK
     */
    @GetMapping
    public ResponseEntity<List<HistoryDto>> readAll(@RequestParam("id") List<Long> id) {
        return new ResponseEntity<>(service.readAllById(id), HttpStatus.OK);
    }

    /**
     * @param history {@link HistoryDto}
     * @return {@link ResponseEntity} c {@link HistoryDto} и HttpStatus OK
     */
    @PostMapping
    public ResponseEntity<HistoryDto> create(@RequestBody HistoryDto history) {
        return new ResponseEntity<>(service.create(history), HttpStatus.OK);
    }

    /**
     * @param id      технический идентификатор {@link HistoryEntity}
     * @param history {@link HistoryDto}
     * @return {@link ResponseEntity} c {@link HistoryDto} и HttpStatus OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<HistoryDto> update(@PathVariable Long id,
                                             @RequestBody HistoryDto history) {
        return new ResponseEntity<>(service.update(id, history), HttpStatus.OK);
    }
}
