package com.plopcas.bb.controller;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Battle page controller.
 */
@Controller
public class BattleController {
    private final Faker faker;

    public BattleController() {
        faker = new Faker();
    }

    @GetMapping("battle")
    public String getBattlePage() {
        return "battle";
    }
}
