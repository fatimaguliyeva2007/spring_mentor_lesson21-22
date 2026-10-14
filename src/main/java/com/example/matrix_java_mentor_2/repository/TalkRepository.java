package com.example.matrix_java_mentor_2.repository;

import com.example.matrix_java_mentor_2.entity.TalkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<TalkEntity, Long> {
    List<TalkEntity> findBySpeakerId(Long speakerId);
    long countBySpeakerId(Long speakerId);
}
