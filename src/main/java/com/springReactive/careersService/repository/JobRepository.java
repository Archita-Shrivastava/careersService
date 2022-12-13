package com.springReactive.careersService.repository;

import com.springReactive.careersService.model.Job;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JobRepository extends ReactiveCassandraRepository<Job,Integer> {
    @AllowFiltering
    Flux<Job> findByJobId(int jobId);
    @AllowFiltering
    Mono<Boolean> existsByJobId(int jobId);
}
