package com.hb0730.security5.authorization.login.authorization.server.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.security5.authorization.login.authorization.server.model.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author bing_huang
 */

/**
 * 用户信息
 *
 * @author bing_huang
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> userDetails = userList();
        Optional<UserDetails> detailsOptional = userDetails
                .stream()
                .filter(details -> details.getUsername().equals(username)).findFirst();
        if (detailsOptional.isPresent()) {
            return detailsOptional.get();
        }
        throw new UsernameNotFoundException("用户不存在");
    }

    public List<UserDetails> userList() {
        List<UserDetails> users = Lists.newArrayList();
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "test:permission");
        AuthUser user = new AuthUser(
                "Administrator",
                PASSWORD_ENCODER.encode("123456"),
                true,
                true,
                true,
                true,
                authorityList
        );
        user.setUserId(-1L);
        user.setNickname("超级管理员");
        users.add(user);
        authorityList = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        user = new AuthUser(
                "admin",
                PASSWORD_ENCODER.encode("123456"),
                true,
                true,
                true,
                true,
                authorityList
        );
        user.setUserId(1L);
        user.setNickname("管理员");
        users.add(user);
        return users;
    }
}
