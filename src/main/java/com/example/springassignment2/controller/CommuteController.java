package com.example.springassignment2.controller;

import com.example.springassignment2.entity.Commute;
import com.example.springassignment2.entity.Job;
import com.example.springassignment2.service.CommuteService;
import com.example.springassignment2.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/commute")
public class CommuteController implements WebMvcConfigurer
{
    static final String COMMUTE = "commute";
    static final String COMMUTEFORM = "commute-form";
    Logger logger = LoggerFactory.getLogger(CommuteController.class);
    private CommuteService commuteService;
    private JobService jobService;

    @Autowired
    public CommuteController(CommuteService theCommuteService,JobService theJobService)
    {
        commuteService = theCommuteService;
        jobService = theJobService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create commute attribute to bind form data
        Commute theCommute = new Commute();

        theModel.addAttribute(COMMUTE, theCommute);

        List<Job> theJobs =  jobService.findAll();

        //add to the spring model
        theModel.addAttribute("job",theJobs);

        logger.info("...Calling commute-form for adding a commute...");

        return COMMUTEFORM;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("commuteId") int theId , Model theModel)
    {
        //get the commute from the db using the id passed
        Commute theCommute = commuteService.findById(theId);

        //set commute as a model attribute to prepopulate the form
        theModel.addAttribute(COMMUTE, theCommute);

        List<Job> theJobs =  jobService.findAll();

        //add to the spring model
        theModel.addAttribute("job",theJobs);

        logger.info("...Calling commute-form for updating a commute...");

        //send over to our form
        return COMMUTEFORM;
    }

    @PostMapping("/save")
    public String saveCommute(@Valid @ModelAttribute("commute") Commute theCommute, BindingResult theBindingResult,Model theModel)
    {
        if (theBindingResult.hasErrors())
        {
            List<Job> theJobs =  jobService.findAll();

            //add to the spring model
            theModel.addAttribute("job",theJobs);
            return COMMUTEFORM;
        }

        //save the commute
        commuteService.save(theCommute);

        logger.info("...Saving the commute option provided and redirecting to job list...");

        //using a redirect to prevent duplicate submissions
        return "redirect:/job/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("commuteId") int theId)
    {

        commuteService.deleteById(theId);

        logger.info("...Deleting a commute option and redirecting to job list...");

        return "redirect:/job/list";
    }

    @GetMapping("/showCommute")
    public String listJobs(@RequestParam("jobId") int theId,Model theModel)
    {
        List<Commute> theCommute = commuteService.findByJobId(theId);
        theModel.addAttribute(COMMUTE,theCommute);

        logger.info("...Showing commute options for a given job...");

        return "commute-list";
    }

}
