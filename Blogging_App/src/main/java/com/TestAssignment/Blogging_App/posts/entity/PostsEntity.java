package com.TestAssignment.Blogging_App.posts.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "posts")

public class PostsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "oid", updatable = false, nullable = false)
    private String oid;

    @Column(name = "title")
//    @NotNull
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "createdon")
    private Timestamp createdOn;

    @Column(name = "updatedon")
    private Timestamp updatedOn;

    @Column(name = "authorid")
    private String authorId;
}
