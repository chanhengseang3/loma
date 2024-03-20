package com.chanheng.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "app_user")
public class User implements Serializable, Persistable<String> {

    @Id
    private String id;

    private String username;

    private String email;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return !StringUtils.hasText(id);
    }
}
