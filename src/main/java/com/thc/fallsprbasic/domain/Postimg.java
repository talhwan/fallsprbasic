package com.thc.fallsprbasic.domain;

import com.thc.fallsprbasic.dto.PostimgDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Postimg extends AuditingFields{
    @Setter @Column(nullable=false) Long postId; //(FK)
    @Setter @Column(nullable=false) String url;

    protected Postimg(){}
    private Postimg(Boolean deleted, Long postId, String url){
        this.deleted = deleted;
        this.postId = postId;
        this.url = url;
    }

    public static Postimg of(Long postId, String url){
        return new Postimg(false, postId, url);
    }

    public PostimgDto.CreateResDto toCreateResDto() {
        return PostimgDto.CreateResDto.builder().id(getId()).url(getUrl()).build();
    }
}
