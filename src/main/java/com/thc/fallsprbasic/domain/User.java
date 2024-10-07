package com.thc.fallsprbasic.domain;

import com.thc.fallsprbasic.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id Long id;

    @Setter @Column(unique=true, nullable=false)
    String username;

    @Setter @Column(nullable=false)
    String password;

    @Setter String name;
    @Setter String phone;

    public UserDto.CreateResDto toCreateResDto(){
        UserDto.CreateResDto res = new UserDto.CreateResDto();
        res.setId(getId());
        return res;
    }
}
