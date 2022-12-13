package com.springReactive.careersService.controller;

import com.springReactive.careersService.model.Employee;
import com.springReactive.careersService.model.Job;
import com.springReactive.careersService.model.JobResponse;
import com.springReactive.careersService.service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class JobController {
    @Autowired
    private JobServices jobServices;

    @PostMapping("/createJob")
    public Mono<JobResponse> createJob(@RequestBody @Valid Job job){
        return this.jobServices.createJob(job);

    }
    @GetMapping("/findEmpForJob/{jobId}")
    public Flux<Employee> findEmpForJobId(@PathVariable("jobId") int jobId){
        return this.jobServices.findEmpForJobId(jobId);
    }

    @GetMapping("/getJobProfileFromCache/{jobId}")
    public Mono<Job> getJObProfileFromCache(@PathVariable Integer jobId){
        return this.jobServices.getJobProfileFromCache(jobId);
    }
}
