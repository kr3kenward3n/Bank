package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper для {@link BranchEntity} и {@link BranchDto}
 */
@Mapper(componentModel = "spring")
public interface BranchMapper {

    /**
     * @param branch {@link BranchDto}
     * @return {@link BranchEntity}
     */
    @Mapping(target = "id", ignore = true)
    BranchEntity toEntity(BranchDto branch);

    /**
     * @param branch {@link BranchEntity}
     * @return {@link BranchDto}
     */
    BranchDto toDto(BranchEntity branch);

    /**
     * @param branchDto {@link BranchDto}
     * @param branch    {@link BranchEntity}
     * @return {@link BranchEntity}
     */
    @Mapping(target = "id", ignore = true)
    BranchEntity mergeToEntity(BranchDto branchDto, @MappingTarget BranchEntity branch);

    /**
     * @param branches список {@link BranchEntity}
     * @return список {@link BranchDto}
     */
    List<BranchDto> toDtoList(List<BranchEntity> branches);
}
