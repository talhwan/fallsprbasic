package com.thc.fallsprbasic.domain;

import com.thc.fallsprbasic.dto.DefaultDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Post extends AuditingFields{
    @Setter @Column(nullable=false)
    String title;
    @Setter @Column(nullable=true)
    String content;

    protected Post(){}
    private Post(Boolean deleted, String title, String content){
        this.deleted = deleted;
        this.title = title;
        this.content = content;
    }

    public static Post of(String title, String content){
        return new Post(false, title, content);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
