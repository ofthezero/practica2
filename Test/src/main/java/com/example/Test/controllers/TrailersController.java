package com.example.Test.controllers;

import com.example.Test.models.Trailers;

import com.example.Test.repositories.TrailersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/trailers")
public class TrailersController {

    @Autowired
    private TrailersRepository trailersRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Trailers> trailers = trailersRepository.findAll();
        model.addAttribute("trailers",trailers);
        return "trailers/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        return "trailers/adde-trailers";
    }

    @PostMapping("/adde")
    public String adds(
            @RequestParam("nameT") String nameT,
            @RequestParam("raiting1") String raiting1,
            @RequestParam("raiting2") String raiting2,
            @RequestParam("date") Integer date,
            @RequestParam("season") Integer season,
            Model model)
    {
        Trailers trailersOne = new Trailers(nameT,raiting1,raiting2,date,season);
        trailersRepository.save(trailersOne);
        return "redirect:/trailers/";
    }
    @GetMapping("/seartrailers")
    public String adds(
            @RequestParam("nameT") String nameT,
            Model model)
    {
        List<Trailers> trailersList = trailersRepository.findByNameT(nameT);
        model.addAttribute("trailers",trailersList);

        return "trailers/indet";
    }

}
