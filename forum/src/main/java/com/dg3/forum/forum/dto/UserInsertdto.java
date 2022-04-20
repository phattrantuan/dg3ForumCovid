package com.dg3.forum.forum.dto;

import java.util.Date;

import com.dg3.forum.forum.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertdto {

	private String email;
	private String password;
	private String username;

	private String phone_number;
	private String address;
	private Date date_of_birth;

	private String img_avatar;
	private String description;
	
	
	public UserInsertdto(Users entity) {
        this.email = entity.getUsername();
        this.password = entity.getPassword();
        this.username = entity.getUsername();
        this.phone_number = entity.getPhone_number();
        this.address = entity.getAddress();
        this.date_of_birth = entity.getDate_of_birth();
        this.img_avatar = entity.getImg_avatar();
        this.description = entity.getDescription();
        
      ;
    }
}
