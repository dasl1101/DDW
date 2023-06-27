package com.web.DDW.domain.posts;

import com.web.DDW.domain.BaseTimeEntity;
import com.web.DDW.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;



    public void update(String content, int secret){
        this.comment = content;
    }
}