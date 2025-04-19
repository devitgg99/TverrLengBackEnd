package org.example.tverleng.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {

    private Long userId;
    private String fullName;
    private String email;
    private String password;
//    private List<String> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for( String role : roles ) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
//            simpleGrantedAuthorities.add(simpleGrantedAuthority);
//        }
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
