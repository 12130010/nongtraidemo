package com.ndtt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ndtt.domain.SingleUnit;

public interface UnitRepository extends PagingAndSortingRepository<SingleUnit, String>{

}
