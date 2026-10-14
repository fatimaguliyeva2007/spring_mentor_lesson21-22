package com.example.matrix_java_mentor_2.repository;

import com.example.matrix_java_mentor_2.entity.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<SpeakerEntity, Long> {
    boolean existsByEmail(String email);
}
