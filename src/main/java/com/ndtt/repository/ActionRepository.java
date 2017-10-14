package com.ndtt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ndtt.domain.Action;

public interface ActionRepository extends PagingAndSortingRepository<Action, String>{

}
