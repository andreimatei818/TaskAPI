package com.task.ING.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String authority;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.authority = user.getAuthority();
    }
}
