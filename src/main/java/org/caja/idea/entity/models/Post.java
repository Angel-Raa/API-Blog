package org.caja.idea.entity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.caja.idea.utils.Message;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @NotBlank(message = Message.TITLE_REQUIRED)
    private String title;
    private String content;
    @Column(name ="created")
    @CreationTimestamp
    private LocalDateTime created;
    @Column(name ="updated")
    @UpdateTimestamp
    private LocalDateTime updated;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Users.class)
    @JoinColumn(name = "author_id")
    private Users author;

    public Post(Long id, String title, String content, LocalDateTime created, LocalDateTime updated, Users author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.author = author;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    
}
