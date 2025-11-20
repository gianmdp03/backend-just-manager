package com.gianmdp03.gestor_just_backend.Service;

import com.gianmdp03.gestor_just_backend.DTO.Location.LocationDetailDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationListDTO;
import com.gianmdp03.gestor_just_backend.DTO.Location.LocationRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    LocationDetailDTO addLocation(LocationRequestDTO locationRequestDTO);
    Page<LocationListDTO> listLocations(Pageable pageable);
    void deleteLocation(Long id);
}
