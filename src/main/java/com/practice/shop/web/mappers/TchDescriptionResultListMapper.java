package com.practice.shop.web.mappers;

import com.practice.shop.model.TchDescriptionResultList;
import com.practice.shop.web.dto.TchDescriptionResultListDto;
import org.mapstruct.Mapper;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring", uses = TeachersDescriptionMapper.class)
public interface TchDescriptionResultListMapper {

    TchDescriptionResultListDto entityToDto(TchDescriptionResultList list);

}
