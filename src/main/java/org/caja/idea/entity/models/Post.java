package org.caja.idea.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import org.caja.idea.utils.constants.Message;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, unique = true)
    @NotBlank(message = Message.TITLE_REQUIRED)
    private String title;
    private String content;
    @Column(name ="create_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(name ="update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Comment.class, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Users.class)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Users users;

    public Post(Long id, String title, String content, LocalDateTime createAt, LocalDateTime updateAt, List<Comment> comments, Users users) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.comments = comments;
        this.users = users;
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
        return createAt;
    }

    public void setCreated(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdated() {
        return updateAt;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updateAt = updated;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
