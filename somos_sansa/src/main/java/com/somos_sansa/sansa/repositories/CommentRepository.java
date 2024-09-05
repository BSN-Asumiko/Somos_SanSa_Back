package com.somos_sansa.sansa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somos_sansa.sansa.models.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByTopicId(int topicId);
}
