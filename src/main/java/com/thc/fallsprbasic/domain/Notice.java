package com.thc.fallsprbasic.domain;

import com.thc.fallsprbasic.dto.DefaultDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import com.thc.fallsprbasic.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Notice extends AuditingFields{
    @Setter @Column(nullable=false)
    String title;
    @Setter @Column(nullable=true)
    String content;
    @Setter @Column(nullable=true)
    String img;

    protected Notice(){}
    private Notice(Boolean deleted, String title, String content, String img){
        this.deleted = deleted;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public static Notice of(String title, String content, String img){
        return new Notice(false, title, content, img);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
