package com.yours.emong.domain.message.scheduler;

import com.yours.emong.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageScheduler {

    private final MessageService messageService;

    @Scheduled(cron = "0 0 12 * * ?") // 매일 12시에 실행
    public void scheduleGraduationMessages() {
        messageService.sendMessages();
    }

    @Scheduled(cron = "0 0 12 * * ?") // 매일 12시에 실행
    public void scheduleDataDeletion() {
        messageService.deleteExpiredUsers();
    }
}
