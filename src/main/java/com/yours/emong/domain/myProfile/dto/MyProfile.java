package com.yours.emong.domain.myProfile.dto;

import com.yours.emong.domain.myProfile.domain.MyProfileEntity;
import com.yours.emong.domain.user.dto.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MyProfile {

    @Column(nullable = false)
    private User user;

    @Column(nullable = false)
    private String serialNumber;

    private String profileImageUrl;

    private String introduction;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    private String region;

    private String birth;

    private boolean hasLover;

    private String mbti;

    private List<String> relatedLinks;


    public static MyProfile toMyProfile(MyProfileEntity myProfileEntity) {
        User userInstance = new User();
        User user = userInstance.toUser(myProfileEntity.getUser());

        return MyProfile.builder()
                .user(user)
                .profileImageUrl(myProfileEntity.getProfileImageUrl())
                .introduction(myProfileEntity.getIntroduction())
                .username(myProfileEntity.getUsername())
                .phoneNumber(myProfileEntity.getPhoneNumber())
                .region(myProfileEntity.getRegion())
                .birth(myProfileEntity.getBirth())
                .hasLover(myProfileEntity.isHasLover())
                .mbti(myProfileEntity.getMbti())
                .relatedLinks(myProfileEntity.getRelatedLinks())
                .build();
    }
}


