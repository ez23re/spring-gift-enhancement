package gift.controller;

import gift.dto.WishRequest;
import gift.entity.Wish;
import gift.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Wish>> getWishes(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        List<Wish> wishes = wishService.getWishes(token);
        return ResponseEntity.ok(wishes);
    }

    @PostMapping("/add")
    public ResponseEntity<Wish> addWish(@RequestBody WishRequest wishRequest, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        Wish wish = wishService.addWish(token, wishRequest);
        return ResponseEntity.ok(wish);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeWish(@RequestBody WishRequest wishRequest, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }
        wishService.removeWish(token, wishRequest.getProductId());
        return ResponseEntity.ok().build();
    }
}