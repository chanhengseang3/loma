package com.chanheng.demo.userproduct;

import com.chanheng.demo.RestTemplateService;
import com.chanheng.demo.apidata.ResponseData;
import com.chanheng.demo.user.User;
import com.chanheng.demo.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/userproducts")
@RequiredArgsConstructor
public class UserProductController {

    private static final String PRODUCT_URL = "https://fakestoreapi.com/products";

    private final UserRepository userRepository;

    @PostConstruct
    private void init() {//init some user data
        var user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setUsername(UUID.randomUUID().toString());
        userRepository.save(user1);
        var user2 = new User();
        user2.setId(UUID.randomUUID().toString());
        user2.setUsername(UUID.randomUUID().toString());
        userRepository.save(user2);
    }

    @GetMapping
    public ResponseData get(@AuthenticationPrincipal OAuth2User principal) throws ExecutionException, InterruptedException {
        log.info(principal.getName());
        var users = CompletableFuture.supplyAsync(userRepository::findAll);
        var products = CompletableFuture.supplyAsync(() -> RestTemplateService.get(PRODUCT_URL, null, List.class).getBody());

        CompletableFuture<ResponseData> completableFuture = CompletableFuture
                        .allOf(users, products)
                        .thenApply(responseData -> {
                            try {
                                return ResponseData.of(users.get(), products.get());
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        });

        return completableFuture.get();
    }
}
