package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
 * Контроллер для {@link AccountTransferDto}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Tag(name = "контроллер для переводов по номеру счета")
public class AccountTransferController {

    private final AccountTransferService service;

    /**
     * @param ids список технических идентификаторов {@link AccountTransferEntity}
     * @return {@link ResponseEntity} c листом {@link AccountTransferDto}
     */
    @GetMapping("/read/all")
    @Operation(summary = "получение списка переводов по номеру счета по их id")
    public ResponseEntity<List<AccountTransferDto>> readAll(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }

    /**
     * @param id технический идентификатор {@link AccountTransferEntity}
     * @return {@link ResponseEntity} {@link AccountTransferDto}
     */
    @GetMapping("/read/{id}")
    @Operation(summary = "получение перевода по номеру счета по его id")
    public AccountTransferDto read(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @return {@link ResponseEntity } {@link AccountTransferDto}
     */
    @PostMapping("/create")
    @Operation(summary = "создание перевода по номеру счета")
    public ResponseEntity<AccountTransferDto> create(@RequestBody AccountTransferDto accountTransfer) {
        return ResponseEntity.ok(service.save(accountTransfer));
    }

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @param id              технический идентификатор {@link AccountTransferEntity}
     * @return {@link ResponseEntity} {@link AccountTransferDto}
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "обновление перевода по номеру счета")
    public ResponseEntity<AccountTransferDto> update(@PathVariable("id") Long id,
                                                     @RequestBody AccountTransferDto accountTransfer) {
        return ResponseEntity.ok(service.update(id, accountTransfer));
    }
}
