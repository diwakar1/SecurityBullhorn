package com.example.bullhorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BullhornsRepository bullhornsRepository;

    @RequestMapping("/")
    public String listBullHorns(Model model){
        model.addAttribute("bullhorns",bullhornsRepository.findAll());
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

   /* @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }*/

    @GetMapping("/add")
    public String bullHornForm(Model model){
        model.addAttribute("bullhorn",new BullHorn());
        return "bullhornform";
    }
    @PostMapping("/process")
    public String processBullHorn(@Valid BullHorn bullhorn, BindingResult result){
        if(result.hasErrors()){

            return "bullhornform";

        }
        bullhornsRepository.save(bullhorn);
        return "redirect:/";


    }
    @RequestMapping("/detail/{id}")
    public  String displayBullHorn(@PathVariable("id") long id, Model model) {
        model.addAttribute("bullhorn", bullhornsRepository.findById(id).get());
        return "show";

    }
    @RequestMapping("/update/{id}")
    public String updateBullHorn(@PathVariable("id")long id,Model model){
        model.addAttribute("bullhorn",bullhornsRepository.findById(id));
                return"bullhornform";
    }



}
