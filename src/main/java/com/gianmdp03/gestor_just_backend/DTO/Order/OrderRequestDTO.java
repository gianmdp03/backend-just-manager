package com.gianmdp03.gestor_just_backend.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderRequestDTO (@NotNull BigDecimal amount,
                               String description,
                               @NotNull @PastOrPresent LocalDate saleDate,
                               @NotNull Long customerId,
                               @NotEmpty List<Long> productIds){}
