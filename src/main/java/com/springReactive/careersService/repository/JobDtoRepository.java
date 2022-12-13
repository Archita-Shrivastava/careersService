package com.springReactive.careersService.repository;

import com.springReactive.careersService.model.Job;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface JobDtoRepository extends ReactiveCassandraRepository<Job,Integer> {
    @AllowFiltering
    Mono<Job> findByjobId(int jobId);
}
