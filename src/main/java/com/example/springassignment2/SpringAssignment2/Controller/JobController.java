package com.example.springassignment2.SpringAssignment2.Controller;

import com.example.springassignment2.SpringAssignment2.Dao.SkillRepository;
import com.example.springassignment2.SpringAssignment2.Entity.Job;
import com.example.springassignment2.SpringAssignment2.Entity.Skill;
import com.example.springassignment2.SpringAssignment2.Service.JobService;
import com.example.springassignment2.SpringAssignment2.Service.SkillService;
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
@RequestMapping("/job")
public class JobController implements WebMvcConfigurer
{
    Logger logger = LoggerFactory.getLogger(JobController.class);
    private JobService jobService;
    private SkillService skillService;

    @Autowired
    public JobController(JobService theJobService, SkillService theSkillService)
    {
        jobService = theJobService;
        skillService = theSkillService;
    }

    @GetMapping("/list")
    public String listOfJobs(Model theModel)
    {
        List<Job> theJobs = jobService.findAll();

        logger.info("...Getting the list of jobs...");
        theModel.addAttribute("jobs",theJobs);

        return "jobs-list";
    }

    @GetMapping("/showJob")
    public String jobById(@RequestParam("jobId") int theId,Model theModel)
    {
        Job theJob = jobService.findById(theId);
        List<Skill> theSkills = skillService.findSkillsByJobsId(theId);
        theModel.addAttribute("jobs",theJob);
        theModel.addAttribute("skills",theSkills);

        logger.info("...getting a job based on the id provided...");

        return "job-info";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Job theJob = new Job();
        List<Skill> theSkill = skillService.findAll();

        logger.info("...Calling jobs-form for adding a job...");

        theModel.addAttribute("jobs", theJob);
        theModel.addAttribute("skills",theSkill);

        return "jobs-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("jobId") int theId , Model theModel)
    {
        //get the job from the db using the id passed
        Job theJob = jobService.findById(theId);
       //List<Skill> theSkills = skillService.findSkillsByJobsId(theId);

        //set job as a model attribute to prepopulate the form
        theModel.addAttribute("jobs",theJob);
        //theModel.addAttribute("skills",theSkills);

        logger.info("...Calling jobs-form for updating a job...");

        //send over to our form
        return "jobs-form";
    }

    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute("jobs") Job theJob, BindingResult theBindingResult,Model theModel)
    {
        if (theBindingResult.hasErrors())
        {
            List<Skill> theSkill = skillService.findAll();
            theModel.addAttribute("skills",theSkill);
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
