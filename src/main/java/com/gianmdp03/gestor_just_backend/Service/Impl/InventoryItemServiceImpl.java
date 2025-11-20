package com.gianmdp03.gestor_just_backend.Service.Impl;

import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemListDTO;
import com.gianmdp03.gestor_just_backend.DTO.InventoryItem.InventoryItemRequestDTO;
import com.gianmdp03.gestor_just_backend.Exception.NotFoundException;
import com.gianmdp03.gestor_just_backend.Mapper.InventoryItemMapper;
import com.gianmdp03.gestor_just_backend.Model.InventoryItem;
import com.gianmdp03.gestor_just_backend.Model.Location;
import com.gianmdp03.gestor_just_backend.Model.Product;
import com.gianmdp03.gestor_just_backend.Repository.InventoryItemRepository;
import com.gianmdp03.gestor_just_backend.Repository.LocationRepository;
import com.gianmdp03.gestor_just_backend.Repository.ProductRepository;
import com.gianmdp03.gestor_just_backend.Service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public InventoryItemListDTO addInventoryItem(InventoryItemRequestDTO inventoryItemRequestDTO) {
        Product product = productRepository.findById(inventoryItemRequestDTO.getProductId()).orElseThrow(
                () -> new NotFoundException("Product ID does not exist")
        );
        Location location = locationRepository.findById(inventoryItemRequestDTO.getLocationId()).orElseThrow(
                () -> new NotFoundException("Location ID does not exist")
        );

        InventoryItem inventoryItem = inventoryItemRepository.save(inventoryItemMapper.toEntity(inventoryItemRequestDTO));
        return inventoryItemMapper.toListDto(inventoryItem);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItems(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findAll(pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItemsByProduct(Long productId, Pageable pageable) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product ID does not exist"));
        Page<InventoryItem> page = inventoryItemRepository.findAllByProduct(product, pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listInventoryItemsByLocation(Long locationId, Pageable pageable) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new NotFoundException("Location ID does not exist"));
        Page<InventoryItem> page = inventoryItemRepository.findAllByLocation(location, pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listNonExpiredInventoryItems(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findByExpireDateBefore(LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    public Page<InventoryItemListDTO> listExpiredInventoryItems(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findByExpireDateGreaterThanEqual(LocalDate.now(), pageable);
        if(page.isEmpty())
            throw new NotFoundException("List is empty");
        return page.map(inventoryItemMapper::toListDto);
    }

    @Override
    @Transactional
    public void deleteInventoryItem(Long id) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(id).orElseThrow
                (() -> new NotFoundException("InventoryItem ID does not exist"));
        inventoryItemRepository.delete(inventoryItem);
    }
}
