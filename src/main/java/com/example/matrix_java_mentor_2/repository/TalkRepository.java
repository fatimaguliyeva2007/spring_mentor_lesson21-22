package com.example.matrix_java_mentor_2.repository;

import com.example.matrix_java_mentor_2.entity.RoomTalkCount;
import com.example.matrix_java_mentor_2.entity.TalkEntity;
import com.example.matrix_java_mentor_2.enums.TalkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<TalkEntity, Long> {
    List<TalkEntity> findBySpeakerId(Long speakerId);
    long countBySpeakerId(Long speakerId);
    List<TalkEntity> findByStatus(TalkStatus status);

    @Query("""
            select t.room as room, count(t) as talkCount
            from TalkEntity t
            group by t.room
            order by t.room
            """)
    List<RoomTalkCount> countTalksPerRoom();
}
