package com.darkotrajkovski.finkiblog.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String title;

    @Lob
    @Column
    @NotEmpty
    private String content;

    @Column
    private Instant createdOn;

    @Column
    private Instant updatedOn;

    @Column
    @NotBlank
    private String username;

    public Post(@NotBlank String title, @NotEmpty String content, @NotBlank String username) {
        this.title = title;
        this.content = content;
        this.createdOn = Instant.now();
        this.updatedOn = Instant.now();
        this.username = username;
    }

    public Post() {
    }
}
