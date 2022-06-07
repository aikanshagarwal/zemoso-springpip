package com.example.springassignment2.controller;

import com.example.springassignment2.entity.Job;
import com.example.springassignment2.entity.Location;
import com.example.springassignment2.entity.Skill;
import com.example.springassignment2.service.JobService;
import com.example.springassignment2.service.LocationService;
import com.example.springassignment2.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController implements WebMvcConfigurer
{
    final static String JOBLIST = "job-list";
    final static String JOBINFO = "job-info";
    final static String JOBFORM = "job-form";
    Logger logger = LoggerFactory.getLogger(JobController.class);
    private JobService jobService;
    private SkillService skillService;
    private LocationService locationService;



    @Autowired
    public JobController(JobService theJobService, SkillService theSkillService, LocationService theLocationService)
    {
        jobService = theJobService;
        skillService = theSkillService;
        locationService = theLocationService;
    }

    @GetMapping("/list")
    public String listOfJobs(Model theModel,String location,String skill,String skill2,String location2)
    {

        if(location != null)
        {
            logger.info("...Getting the list of jobs based on location...");
            theModel.addAttribute("jobs",jobService.findByLocation(location));
        }
        else if(skill !=null)
        {
            logger.info("...Getting the list of jobs based on skill...");
            theModel.addAttribute("jobs",jobService.findBySkill(skill));
        }
        else if(skill2!=null && location2!=null)
        {
            logger.info("...Getting the list of jobs based on skill and location...");
            theModel.addAttribute("jobs",jobService.findBySkillAndLocation(skill2,location2));
        }
        else
        {
            logger.info("...Getting the list of all jobs...");
            theModel.addAttribute("jobs",jobService.findAll());
        }

        return JOBLIST;
    }

    @GetMapping("/showJob")
    public String jobById(@RequestParam("jobId") int theId,Model theModel)
    {
        Job theJob = jobService.findById(theId);
        List<Skill> theSkills = skillService.findSkillsByJobsId(theId);
        List<Location> theLocations = locationService.findLocationsByJobsId(theId);

        theModel.addAttribute("jobs",theJob);
        theModel.addAttribute("skills",theSkills);
        theModel.addAttribute("locations",theLocations);

        logger.info("...getting a job and it's information based on the id provided...");

        return JOBINFO;
    }

    @GetMapping("/saveAJob")
    public String saveAJob(Authentication authentication,@RequestParam("jobId") int jobId,Model theModel)
    {
        jobService.saveAJob(jobId ,authentication.getName());
        theModel.addAttribute("jobs",jobService.findAll());
        logger.info("...Saving a job for the logged in user...");
        return JOBLIST;
    }

    @GetMapping("/unsaveAJob")
    public String unsaveAJob(Authentication authentication,@RequestParam("jobId") int jobId,Model theModel)
    {
        jobService.unsaveAJob(jobId ,authentication.getName());
        theModel.addAttribute("jobs",jobService.findAll());
        logger.info("...Unsaving a job for the logged in user...");
        return JOBLIST;
    }

    @GetMapping(value = "/showSavedJobs")
    public String showSavedJobs(Authentication authentication,Model theModel)
    {
        theModel.addAttribute("jobs",jobService.showSavedJobs(authentication.getName()));
        logger.info("...Showing saved jobs for the logged in user...");
        return JOBLIST;
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Job theJob = new Job();
        List<Skill> theSkill = skillService.findAll();
        List<Location> theLocation = locationService.findAll();

        logger.info("...Calling jobs-form for adding a job...");

        theModel.addAttribute("jobs", theJob);
        theModel.addAttribute("skills",theSkill);
        theModel.addAttribute("locations",theLocation);

        return JOBFORM;
    }

    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute("jobs") Job theJob, BindingResult theBindingResult,Model theModel)
    {
        if (theBindingResult.hasErrors())
        {
            List<Skill> theSkill = skillService.findAll();
            List<Location> theLocation = locationService.findAll();
            theModel.addAttribute("skills",theSkill);
            theModel.addAttribute("locations",theLocation);
            return "jobs-form";
        }

        //save the job
        jobService.save(theJob);

        logger.info("...Saving a new job and redirecting to job list...");

        //use a redirect to prevent duplicate submissions
        return "redirect:/job/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("jobId") int theId)
    {
        jobService.deleteById(theId);

        logger.info("...Deleting a job and redirecting to job list...");

        return "redirect:/job/list";
    }
}
