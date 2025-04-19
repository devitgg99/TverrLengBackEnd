package org.example.tverleng.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
//    private Long userId;
    private String fullName;
    private String email;
    private String password;
}
