///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package jquery.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import jquery.service.PartieCrudService;
//
///**
// *
// * @author tom
// */
////@Controller
////@RequestMapping("/genre")
//public class GenreController { 
//
//    @Autowired
//    private PartieCrudService genreCrudService;
//    
//    @RequestMapping(value="ajouter", method = RequestMethod.POST)
//    public String ajouterPOST( @ModelAttribute("genre") Genre g){
//        
//        genreCrudService.save(g);
//        
//        return "redirect:/genre/lister";
//    }
//    
//    @RequestMapping(value="ajouter", method = RequestMethod.GET)
//    public String ajouter(Model m){
//        
//        m.addAttribute("genre", new Genre());
//        
//        return "genre/ajouter";
//    }
//    
//    @RequestMapping(value = "lister", method = RequestMethod.GET)
//    public String lister(Model m){
//        
//        m.addAttribute("genres", genreCrudService.findAll());
//        
//        return "genre/lister";
//    }
//}
