package com.spring.crud.repository;

import com.spring.crud.model.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstanceRepository extends JpaRepository<Instance,Integer> {

    @Query("select p from instance p where p.instance_name like ?1%")
    List<Instance> findByName(String name);

}
