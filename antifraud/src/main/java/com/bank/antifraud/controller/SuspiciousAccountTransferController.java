package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для {@link SuspiciousAccountTransferDto}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/suspicious/account/transfer")
public class SuspiciousAccountTransferController {
    private final SuspiciousAccountTransferService service;

    /**
     * @param id технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link ResponseEntity} {@link SuspiciousAccountTransferDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SuspiciousAccountTransferDto> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param ids список технических идентификаторов {@link SuspiciousAccountTransferEntity}
     * @return {@link ResponseEntity} c листом {@link SuspiciousAccountTransferDto}
     */
    @GetMapping
    public ResponseEntity<List<SuspiciousAccountTransferDto>> readAll(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }

    /**
     * @param suspiciousTransfer {@link SuspiciousAccountTransferDto}
     * @return {@link ResponseEntity} {@link SuspiciousAccountTransferDto}
     */
    @PostMapping("/create")
    public ResponseEntity<SuspiciousAccountTransferDto> create(
            @Valid @RequestBody SuspiciousAccountTransferDto suspiciousTransfer) {
        return ResponseEntity.ok(service.save(suspiciousTransfer));
    }

    /**
     * @param suspiciousTransfer {@link SuspiciousAccountTransferDto}
     * @param id                 технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link ResponseEntity} {@link SuspiciousAccountTransferDto}
     */
    @PutMapping("/{id}")
    public ResponseEntity<SuspiciousAccountTransferDto> update(
            @RequestBody SuspiciousAccountTransferDto suspiciousTransfer,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.update(id, suspiciousTransfer));
    }
}
