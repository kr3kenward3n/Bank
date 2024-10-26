package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Маппер {@link HistoryEntity} и {@link HistoryDto}
 */
@Mapper(componentModel = "spring")
public interface HistoryMapper {

    /**
     * @param historyDto {@link HistoryDto}
     * @return {@link HistoryEntity}
     */
    @Mapping(target = "id", ignore = true)
    HistoryEntity toEntity(HistoryDto historyDto);

    /**
     * @param historyEntity {@link HistoryEntity}
     * @return {@link HistoryDto}
     */
    HistoryDto toDto(HistoryEntity historyEntity);

    /**
     * @param historyDto {@link HistoryDto}
     * @param historyEntity {@link HistoryEntity}
     * @return {@link HistoryEntity}
     */
    @Mapping(target = "id", ignore = true)
    HistoryEntity mergeToEntity(HistoryDto historyDto, @MappingTarget HistoryEntity historyEntity);

    /**
     * @param historyEntityList лист {@link HistoryEntity}
     * @return лист {@link HistoryDto}
     */
    List<HistoryDto> toListDto(List<HistoryEntity> historyEntityList);
}
