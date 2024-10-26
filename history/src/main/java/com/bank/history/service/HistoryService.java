package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;

import java.util.List;

/**
 * Service для {@link HistoryDto}.
 */
public interface HistoryService {

    /**
     * @param id технический идентификатор {@link HistoryEntity}.
     * @return {@link HistoryDto}
     */
    HistoryDto readById(Long id);

    /**
     * @param id список технических идентификаторов {@link HistoryEntity}.
     * @return список {@link HistoryDto}
     */
    List<HistoryDto> readAllById(List<Long> id);

    /**
     * @param history {@link HistoryDto}
     * @return {@link HistoryDto}
     */
    HistoryDto create(HistoryDto history);

    /**
     * @param id      технический идентификатор {@link HistoryEntity}.
     * @param history {@link HistoryDto}
     * @return {@link HistoryDto}
     */
    HistoryDto update(Long id, HistoryDto history);
}
