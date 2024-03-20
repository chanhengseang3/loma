package com.chanheng.demo.userproduct;

import com.chanheng.demo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProductData {
    private User user;
    private List<Object> product;
}
