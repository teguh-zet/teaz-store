package com.teaz.ecommerce.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teaz.ecommerce.entity.Pengguna;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {

    @SuppressWarnings("unused")
    private String userName;
    private String email;
    private String nama;
    @JsonIgnore
    private String password;
    private String roles;

    

    public UserDetailsImpl(String userName, 
    String email, 
    String nama, 
    String password, 
    String roles) {
        this.userName = userName;
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.roles = roles;
    }

    public static UserDetailsImpl build(Pengguna pengguna) {
        return new UserDetailsImpl(
            pengguna.getId(),
            pengguna.getEmail(),
            pengguna.getNama(),
            pengguna.getPassword(),
            pengguna.getRoles()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(StringUtils.hasText(roles)){
            String [] splits = roles.replaceAll("", "").split(",");
            for(String string : splits){
                authorities.add(new SimpleGrantedAuthority(string));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {  
        // TODO Auto-generated method stub 
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
