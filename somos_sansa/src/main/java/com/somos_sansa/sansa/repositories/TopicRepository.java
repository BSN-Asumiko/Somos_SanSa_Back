package com.somos_sansa.sansa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somos_sansa.sansa.models.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository <Topic, Integer> {
    List<Topic> findByBranchId(int branchId);
}
