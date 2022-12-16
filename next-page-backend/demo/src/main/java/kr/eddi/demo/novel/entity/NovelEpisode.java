package kr.eddi.demo.novel.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class NovelEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long episodeNumber;

    @Column
    private String episodeTitle; // 에피소드별 제목

    @Column
    private String text; // 에피소드 내용

    @Column
    private Boolean needToBuy; // 유료결제여부

    @CreatedDate
    private LocalDate uploadedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "information_id")
    private NovelInformation information;

    // 여기에 댓글 리스트 매핑
    // @OneToMany
    // private List<코멘트 엔티티> commentList;

    public NovelEpisode(Long episodeNumber, String episodeTitle, String text, Boolean needToBuy, NovelInformation information) {
        this.episodeNumber = episodeNumber;
        this.episodeTitle = episodeTitle;
        this.text = text;
        this.needToBuy = needToBuy;
        this.information = information;
    }

    /**
     * 맵핑된 소설 정보 엔티티에 해당 에피소드를 업데이트 합니다.
     */
    public void updateToInformation() {
        this.information.updateEpisode(this);
    }

}