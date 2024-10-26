package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link HistoryService}
 */
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper mapper;
    private final HistoryRepository repository;

    /**
     * @param id технический идентификатор {@link HistoryEntity}.
     * @return {@link HistoryDto}
     */
    @Override
    public HistoryDto readById(Long id) {

        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> returnEntityNotFoundException("история по указанному id не найдена")));
    }

    /**
     * @param id список технических идентификаторов {@link HistoryEntity}.
     * @return список {@link HistoryDto}
     */
    @Override
    public List<HistoryDto> readAllById(List<Long> id) {

        final List<HistoryEntity> histories = repository.findAllById(id);

        if (id.size() > histories.size()) {
            throw returnEntityNotFoundException("истории по указанным id не найдены");
        }

        return mapper.toListDto(histories);
    }

    /**
     * @param historyDto {@link HistoryDto}
     * @return {@link HistoryDto}
     */
    @Override
    @Transactional
    public HistoryDto create(HistoryDto historyDto) {
        final HistoryEntity history = repository.save(mapper.toEntity(historyDto));
        return mapper.toDto(history);
    }

    /**
     * @param id         технический идентификатор {@link HistoryEntity}.
     * @param historyDto {@link HistoryDto}
     * @return {@link HistoryDto}
     */
    @Override
    @Transactional
    public HistoryDto update(Long id, HistoryDto historyDto) {

        final HistoryEntity history = repository.findById(id)
                .orElseThrow(() -> returnEntityNotFoundException("указанная история не найдена " + id));

        final HistoryEntity updatedHistory = repository.save(
                mapper.mergeToEntity(historyDto, history));

        return mapper.toDto(updatedHistory);
    }
    private EntityNotFoundException returnEntityNotFoundException(String massage) {
        return new EntityNotFoundException(massage);
    }
}
