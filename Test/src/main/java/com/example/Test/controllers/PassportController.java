package com.example.Test.controllers;

import com.example.Test.models.Passport;
import com.example.Test.repositories.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Passport> passport = passportRepository.findAll();
        model.addAttribute("passport",passport);
        return "passport/indes";
    }

    @GetMapping("/adds")
    public String addView(Model model)
    {
        return "passport/adds-passport";
    }

    @PostMapping("/adds")
    public String adds(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("petronymic") String petronymic,
            @RequestParam("snils_number") Integer snils_number,
            @RequestParam("oms_number") Integer oms_number,
            Model model)
    {
        Passport passportOne = new Passport(name,surname,petronymic,snils_number,oms_number);
        passportRepository.save(passportOne);
        return "redirect:/passport/";
    }
    @GetMapping("/searpassport")
    public String adds(
            @RequestParam("name") String name,
            Model model)
    {
        List<Passport> passportList = passportRepository.findByName(name);
        model.addAttribute("passport",passportList);

        return "passport/indes";
    }

}
