package com.example.benchboard.controller;

import com.example.benchboard.entity.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController {

    @GetMapping("/calculator")
    public String showCalculator(Model model){
        model.addAttribute("newCalc", new Calculator());
        return "calculator";
    }

    @PostMapping("/calculate")
    public String getResult(@Validated @ModelAttribute("newCalc") Calculator newCalc, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return ("redirect:/calculator?error");
        }

        float weight = newCalc.getWeight();
        int reps = newCalc.getReps();
        int rpe = newCalc.getRpe();
        double result = weight / (1.0278 - (0.0278 * (reps + (10 - rpe))));
        model.addAttribute("result", result);

        return "results";
    }
}
