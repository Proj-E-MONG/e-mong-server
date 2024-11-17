package com.yours.emong.domain.message.service;

import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteDataService {

    private final UserJpaRepository userJpaRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleCleanUp() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        List<User> users = userJpaRepository.findAllByAccessDateBefore(oneMonthAgo);
        for (User user : users) {
            rabbitTemplate.convertAndSend("sms_queue", user.getPhoneNumber());
        }
    }
}
