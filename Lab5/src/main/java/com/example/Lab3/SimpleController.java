package com.example.Lab3;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SimpleController {
    private final Service service;
    @Autowired
    public SimpleController(Service service){
        this.service = service;
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Lesson lesson, BindingResult bindingResult, Model model){
        service.create(lesson.getId(), lesson.getDiscipline(), lesson.getLessonType(), lesson.getAudience(), lesson.getAddress(), lesson.getStart());
        return "redirect:/main";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        final boolean deleted = service.delete(id);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public ModelAndView getMainPage(){
        Lesson lesson = new Lesson(0,"","","","","");
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("lessons",service.getAll());
        mav.addObject("lesson",lesson);
        return mav;
    }
}
