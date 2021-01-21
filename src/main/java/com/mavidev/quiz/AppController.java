package com.mavidev.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private LocationService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return "index";
    }

    @RequestMapping("/locations")
    public String showLocationsPage(Model model) {
        List<Location> listLocations = service.listAll();
        model.addAttribute("listLocations", listLocations);

        return "locations";
    }

    @RequestMapping("/locations/add")
    public String showNewLocationPage(Model model) {
        Location location = new Location();
        model.addAttribute("location", location);

        return "addLocation";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("location") Location location) {
        service.save(location);

        return "redirect:/locations";
    }

    @RequestMapping("/locations/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("editLocation");
        Location location = service.get(id);
        mav.addObject("location", location);

        return mav;
    }

    @RequestMapping("/locations/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);

        return "redirect:/locations";
    }
}
