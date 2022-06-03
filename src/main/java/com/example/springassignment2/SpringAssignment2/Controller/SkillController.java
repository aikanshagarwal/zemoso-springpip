package com.example.springassignment2.SpringAssignment2.Controller;

import com.example.springassignment2.SpringAssignment2.Entity.Job;
import com.example.springassignment2.SpringAssignment2.Entity.Skill;
import com.example.springassignment2.SpringAssignment2.Service.JobService;
import com.example.springassignment2.SpringAssignment2.Service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/skill")
public class SkillController
{
    Logger logger = LoggerFactory.getLogger(SkillController.class);
    private JobService jobService;
    private SkillService skillService;

    @Autowired
    public SkillController(JobService theJobService, SkillService theSkillService)
    {
        jobService = theJobService;
        skillService = theSkillService;
    }

    @GetMapping("/showJobBySkills")
    public String jobBySkills(@RequestParam("skillIds") List<Integer> theIds, Model theModel)
    {
        List<Job> theJobs = jobService.findJobsBySkillsId(theIds);
        theModel.addAttribute("jobs",theJobs);

        logger.info("...getting a job based on the id provided...");

        return "job-info";
    }
}
