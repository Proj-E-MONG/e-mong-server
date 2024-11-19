package com.yours.emong.domain.message.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.yours.emong.domain.message.config.TwilioConfig;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final TwilioConfig twilioConfig;
    private final UserJpaRepository userJpaRepository;

    public void sendMessages() {
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
        List<UserEntity> users = userJpaRepository.findAllByAccessStartDate(fiveYearsAgo);

        for (UserEntity user: users) {
            sendMessage("+82" + user.getPhoneNumber(), "이몽 서비스로 졸업한 친구들을 만나요!(test)");
        }
    }

    public void deleteExpiredUsers() {
        LocalDate usageTime = LocalDate.now().minusMonths(1);
        List<UserEntity> users = userJpaRepository.findAllByAccessStartDate(usageTime.minusYears(5));

        userJpaRepository.deleteAll(users);
    }

    private void sendMessage(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getFromPhoneNumber()),
                message //유저 전번, twilio 가상전번, 보낼 메세지
        ).create();
    }

}
