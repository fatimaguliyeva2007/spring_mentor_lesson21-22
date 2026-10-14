package com.example.matrix_java_mentor_2.scheduler;

import com.example.matrix_java_mentor_2.entity.TalkEntity;
import com.example.matrix_java_mentor_2.enums.TalkStatus;
import com.example.matrix_java_mentor_2.repository.TalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TalkStatusJob {

    private static final ZoneId BAKU = ZoneId.of("Asia/Baku");
    private final TalkRepository talkRepository;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Baku")
    @Transactional
    public void finishTalks() {
        try {
            LocalDateTime now = LocalDateTime.now(BAKU);
            List<TalkEntity> scheduled = talkRepository.findByStatus(TalkStatus.SCHEDULED);

            int changed = 0;
            for (TalkEntity talk : scheduled) {
                if (talk.getScheduledAt() == null || talk.getDurationMinutes() == null) {continue;}
                LocalDateTime endTime = talk.getScheduledAt().plusMinutes(talk.getDurationMinutes());
                if (endTime.isBefore(now)) {talk.setStatus(TalkStatus.FINISHED);
                    changed++;
                }
            }

            if (changed > 0) {
                log.info("{} talks marked as FINISHED", changed);
            } else {
                log.debug("No talks to mark as FINISHED");
            }
        } catch (Exception ex) {
            log.error("Talk status job failed", ex);
        }
    }
}
