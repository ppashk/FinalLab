package com.lab.web.form;

import com.lab.enums.Role;

public class RegistrationForm {
    private String username;
    private String password;
    private String passwordConfirm;
    private Role role;

    public RegistrationForm() {
    }

    public RegistrationForm(String username, String password, String passwordConfirm, Role role) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
