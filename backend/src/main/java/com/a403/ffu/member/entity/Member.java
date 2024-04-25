package com.a403.ffu.member.entity;

import com.a403.ffu.global.security.oauth.mapper.AuthProvider;
import com.a403.ffu.model.Oauth2;
import com.a403.ffu.model.Role;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    @Column(unique = true)
    private String email;

    private String name;

    @ColumnDefault("true")
    private Boolean isActive;

    private String profileImage;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Counselor counselor;

    @Embedded
    private Oauth2 oauth2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_role", joinColumns = @JoinColumn(name = "member_no", referencedColumnName = "member_no"))
    private List<Role> roles = new ArrayList<>(List.of(Role.ROLE_USER));

    @Builder
    public Member(Long no, String email, String name, String profileImage,
            String accountId, AuthProvider authProvider) {
        this.no = no;
        this.email = email;
        this.name = name;
        this.isActive = true;
        this.profileImage = profileImage;
        this.oauth2 = new Oauth2(authProvider, accountId);
    }

    public List<SimpleGrantedAuthority> getAuthorityRole() {
        return roles.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void giveAuthority(Role role) {
        roles.add(role);
    }

    public void updateProfileImage(String path) {
        this.profileImage = path;
    }
}
