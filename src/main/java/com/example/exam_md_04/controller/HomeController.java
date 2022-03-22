package com.example.exam_md_04.controller;

import com.example.exam_md_04.model.City;
import com.example.exam_md_04.model.Country;
import com.example.exam_md_04.service.ICityService;
import com.example.exam_md_04.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;

    @ModelAttribute(value = "country")
    private Iterable<Country> findAll() {
        return iCountryService.findAll();
    }

    @GetMapping
    public ModelAndView showCities() {
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable<City> cities = iCityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<City> cities = iCityService.findOne(id);
        modelAndView.addObject("cities",cities.get());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("city") City city,
                               BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            return new ModelAndView("create").addObject("city", city);
        }

        iCityService.save(city);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        Optional<City> city = iCityService.findOne(id);
        city.ifPresent(value -> modelAndView.addObject("city", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@Valid @ModelAttribute("city") City city,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("city", city);
            return "/edit";
        }
        city.setId(id);
        iCityService.save(city);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/index");
        iCityService.delete(id);
        Iterable<City> cities = iCityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
}