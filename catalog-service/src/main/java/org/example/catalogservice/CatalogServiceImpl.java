package org.example.catalogservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Data
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogDto> findAll() {
        Iterable<CatalogEntity> catalogEntities = catalogRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<CatalogDto> catalogDtoList = new ArrayList<>();

        catalogEntities.forEach(v -> {
            CatalogDto catalogDto = modelMapper.map(v, CatalogDto.class);
            catalogDtoList.add(catalogDto);
        });

        return catalogDtoList;
    }
}
