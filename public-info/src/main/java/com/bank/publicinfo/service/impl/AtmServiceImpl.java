package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link AtmService}
 */
@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {

    private final static String MESSAGE = "Банкомат не найден с id ";

    private final AtmRepository repository;
    private final AtmMapper mapper;
    private final EntityNotFoundSupplier supplierNotFound;

    /**
     * @param ids список технических идентификаторов {@link AtmEntity}
     * @return лист {@link AtmDto}
     */
    @Override
    public List<AtmDto> findAllById(List<Long> ids) {
        final List<AtmEntity> atms = repository.findAllById(ids);
        supplierNotFound.checkForSizeAndLogging(MESSAGE, ids, atms);
        return mapper.toDtoList(atms);
    }

    /**
     * @param atmDto {@link AtmDto}
     * @return {@link AtmDto}
     */
    @Override
    @Transactional
    public AtmDto create(AtmDto atmDto) {
        final AtmEntity atm = repository.save(mapper.toEntity(atmDto));
        return mapper.toDto(atm);
    }

    /**
     * @param atm {@link AtmDto}
     * @return {@link AtmDto}
     */
    @Override
    @Transactional
    public AtmDto update(Long id, AtmDto atm) {
        final AtmEntity entity = repository.findById(id)
                .orElseThrow(() -> (
                        supplierNotFound.getException(MESSAGE, id)
                ));

        final AtmEntity updatedAtm = mapper.mergeToEntity(atm, entity);
        return mapper.toDto(updatedAtm);
    }

    /**
     * @param id технический идентификатор {@link AtmEntity}
     * @return объект {@link AtmDto}
     */
    @Override
    public AtmDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> supplierNotFound.getException(MESSAGE, id)));
    }
}
