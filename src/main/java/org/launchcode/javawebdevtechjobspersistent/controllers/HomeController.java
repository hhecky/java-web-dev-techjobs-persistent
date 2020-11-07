package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId) {  // removed @RequestParam List<Integer> skills

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {

         Optional<Employer> result = employerRepository.findById(employerId);
            Employer employer = result.get();
            model.addAttribute("title", "Add job to: " + employer.getName());
            newJob.setEmployer(employer);
            model.addAttribute("newJob", newJob); //from 18.5
            jobRepository.save(newJob);
            //model.addAttribute("jobId", newJob.getId());  not sure this is needed? trying to get /view/{jobId} to come up on webpage
            return "redirect:/view/{jobId}";  //Model has no value for key 'jobId', but updating db correctly
    }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }


}
