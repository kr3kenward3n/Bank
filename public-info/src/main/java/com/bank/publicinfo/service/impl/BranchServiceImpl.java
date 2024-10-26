package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import com.bank.publicinfo.service.BranchService;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link BranchService}
 */
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final static String MESSAGE = "Информации об отделении не найдено с id ";

    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final EntityNotFoundSupplier supplierNotFound;

    /**
     * @param ids список технических идентификаторов {@link BranchEntity}
     * @return лист {@link BranchDto}
     */
    @Override
    public List<BranchDto> findAllById(List<Long> ids) {
        final List<BranchEntity> branches = repository.findAllById(ids);
        supplierNotFound.checkForSizeAndLogging(MESSAGE, ids, branches);
        return mapper.toDtoList(branches);
    }

    /**
     * @param branchDto {@link BranchDto}
     * @return {@link BranchDto}
     */
    @Override
    @Transactional
    public BranchDto create(BranchDto branchDto) {
        final BranchEntity branch = repository.save(mapper.toEntity(branchDto));
        return mapper.toDto(branch);
    }

    /**
     * @param branch {@link BranchDto}
     * @return {@link BranchDto}
     */
    @Override
    @Transactional
    public BranchDto update(Long id, BranchDto branch) {
        final BranchEntity entity = repository.findById(id)
                .orElseThrow(() -> (
                        supplierNotFound.getException(MESSAGE, id)
                ));

        final BranchEntity updatedBranch = mapper.mergeToEntity(branch, entity);
        return mapper.toDto(updatedBranch);
    }

    /**
     * @param id технический идентификатор {@link BranchEntity}
     * @return {@link BranchDto}
     */
    @Override
    public BranchDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> supplierNotFound.getException(MESSAGE, id)));
    }
}
