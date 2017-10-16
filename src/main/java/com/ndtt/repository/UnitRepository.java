package com.ndtt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ndtt.domain.SingleUnit;

public interface UnitRepository extends PagingAndSortingRepository<SingleUnit, String> {
	List<SingleUnit> findByOwnersContainsAndLevel(List<String> owners, int level, Pageable pageable);
}
