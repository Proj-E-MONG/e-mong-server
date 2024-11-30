package com.yours.emong.domain.myProfile.domain;

import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_my_page")
public class MyProfileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serialNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String profileImageUrl;


    private String introduction;

    @Column(nullable = false)
    private String username;


    private String phoneNumber;

    private String region;

    @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$", message = "생년월일은 yyyy.mm.dd 형식이여야 합니다.")
    private String birth;

    private boolean hasLover;

    private String mbti;

    @ElementCollection
    @CollectionTable(name = "related_links", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "link")
    //entity에서는 collection 데이터를 바로 저장 못함 -> related_links 라는 테이블에 값들을 키 밸류로 저장해서 사용. (새로운 테이블 생성)
    private List<String> relatedLinks; //related_links


}
