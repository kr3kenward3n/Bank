package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.service.AccountDetailsIdService;
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
 * Контроллер для {@link AccountDetailsIdEntity}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/details")
@Tag(name = "Контроллер для банковского счёта")
public class AccountDetailsIdController {

    private final AccountDetailsIdService service;

    /**
     * @param id технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Получение информации о банковском счёте по его ID")
    @GetMapping("/read/{id}")
    public ResponseEntity<AccountDetailsIdDto> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Создание банковского счёта")
    @PostMapping("/create")
    public ResponseEntity<AccountDetailsIdDto> create(@RequestBody AccountDetailsIdDto accountDetailsId) {
        return ResponseEntity.ok(service.save(accountDetailsId));
    }

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @param id               технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity<AccountDetailsIdDto>}
     */
    @Operation(summary = "Обновление банковского счёта")
    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDetailsIdDto> update(@PathVariable Long id,
                                                      @RequestBody AccountDetailsIdDto accountDetailsId) {
        return ResponseEntity.ok(service.update(id, accountDetailsId));
    }

    /**
     * @param ids лист технических идентификаторов {@link AccountDetailsIdEntity}
     * @return {@link ResponseEntity} с листом {@link List<AccountDetailsIdDto>}
     */
    @Operation(summary = "Получение списка банковских счетов по переданным параметрам IDs")
    @GetMapping("read/all")
    public ResponseEntity<List<AccountDetailsIdDto>> readAllById(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
