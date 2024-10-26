package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;

import java.util.List;

/**
 * Сервис для {@link BranchEntity} и {@link BranchDto}
 */
public interface BranchService {

    /**
     * @param ids технический идентификатор {@link BranchEntity}
     * @return лист {@link BranchDto}
     */
    List<BranchDto> findAllById(List<Long> ids);

    /**
     * @param branch {@link BranchDto}
     * @return {@link BranchDto}
     */
    BranchDto create(BranchDto branch);

    /**
     * @param id     технический идентификатор {@link BranchEntity}
     * @param branch {@link BranchDto}
     * @return {@link BranchDto}
     */
    BranchDto update(Long id, BranchDto branch);

    /**
     * @param id технический идентификатор {@link BranchEntity}
     * @return {@link BranchDto}
     */
    BranchDto findById(Long id);
}
