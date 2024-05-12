package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //member엔티티와 1:N 연결
    @JoinColumn(name = "member_id") //외래키 지정.
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) //member엔티티와 1:N 연결
    @JoinColumn(name = "category_id") //외래키 지정.
    private FoodCategory foodCategory;
}
