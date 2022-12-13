package com.springReactive.careersService.service;

import com.hazelcast.map.IMap;
import com.springReactive.careersService.model.Employee;
import com.springReactive.careersService.model.Job;
import com.springReactive.careersService.model.JobResponse;
import com.springReactive.careersService.repository.JobDtoRepository;
import com.springReactive.careersService.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class JobServices {
    @Autowired
    private IMap<Integer, Job> userCache;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobDtoRepository jobDtoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<JobResponse> createJob(Job job) {
        return jobRepository.existsByJobId(job.getJobId())
                .flatMap(aBoolean -> {
                            if (aBoolean) {
                                return Mono.just(new JobResponse(job.getJobId(), job.getJobName(), job.getJavaExperience(), job.getSpringExperience(), "Already Exists"));
                            } else {
                                return jobRepository.save(job).
                                        map(j -> new JobResponse(job.getJobId(), job.getJobName(), job.getJavaExperience(), job.getSpringExperience(), "Created"));

                            }

                        }
                );
    }


    public Flux<Employee> findEmpForJobId(int jobId) {

        Flux<Employee> employeeFlux = jobRepository.findByJobId(jobId).flatMap(job -> {

            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/JavaExpGreaterThan/" + job.getJavaExperience())
                    .retrieve()
                    .bodyToFlux(Employee.class);


        });
        return employeeFlux;

    }
    public Mono<Job> getJobProfileFromCache(int id) {

        Mono<Job> result = getUserFromCache(id);
        return   result
                .switchIfEmpty(getUserFromDB(id))
                .flatMap(user -> saveUserToCache(user));
    }

    public Mono<Job> addUser(Job jobDto) {
        return jobDtoRepository.save(jobDto)
                .flatMap(jobDto1 -> saveUserToCache(jobDto));
    }

    private Mono<Job> getUserFromCache(int id) {

        return Mono.fromCompletionStage(userCache.getAsync(id));
        //return jobDto;
    }


    private Mono<? extends Job> saveUserToCache(Job user) {
        userCache.setAsync(user.getJobId(), user);
        return Mono.just(user);
    }

    private Mono<Job> getUserFromDB(int id) {
        return jobDtoRepository.findByjobId(id);
    }

}




