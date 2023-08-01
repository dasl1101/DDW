package com.web.DDW.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass //entity 클래스들이 이 클래스를 상속하면 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //이 클래스에 auditing기능을 포함시킨다
public abstract class BaseTimeEntity {

    @Column(name = "created_date", nullable = false)
    @CreatedDate    //엔티티가 생성되어 저장될 때 시간이 자동저장된다.
    private String createdDate;

    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate    //조회한 엔티티의 값을 변경할 때 시간이 자동저장된다.
    private String modifiedDate;

    // 해당 엔티티를 저장하기 이전에 실행
    @PrePersist
    public void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate = this.createdDate;
    }

    //해당 엔티티를 업데이트 하기 이전에 실행
    @PreUpdate
    public void onPreUpdate(){
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

}
