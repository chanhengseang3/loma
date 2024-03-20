package com.chanheng.demo.apidata;

import com.chanheng.demo.user.User;
import com.chanheng.demo.userproduct.UserProductData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseData {
    private long statusCode;
    private Object data;

    public static ResponseData of(List<User> users, List<Object> products) {
        var data = users.stream()
                .map(user -> new UserProductData(user, products))
                .toList();
        return new ResponseData(200, data);
    }
}
