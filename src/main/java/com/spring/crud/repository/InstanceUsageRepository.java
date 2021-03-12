package com.spring.crud.repository;

import com.spring.crud.model.InstanceUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.*;


public interface InstanceUsageRepository extends JpaRepository<InstanceUsage,Integer> , PagingAndSortingRepository<InstanceUsage,Integer> {
    @Query(value = "select p from instance_usage_details p where p.instance_id=?1 order by p.time desc")
    List<InstanceUsage> findAllByInstanceId(int id);
    @Query("select p  from instance_usage_details p order by p.instance_id")
    List<InstanceUsage> findAllInAscending();

    @Query(value = "select * from instance_usage_details where instance_id=?1 order by time desc limit ?2",nativeQuery = true )
    List<InstanceUsage> findAllByInstanceIdLimit(int id, int limit);

    Page<InstanceUsage> findAll(Pageable pageable);
}
