package com.springReactive.careersService.model;

import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(value = "job")
public class Job implements Serializable {
    @PrimaryKeyColumn(name = "job_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int jobId;
    @Column("job_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String jobName;
    @PrimaryKeyColumn(name = "java_exp", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private double javaExperience;
    @PrimaryKeyColumn(name = "spring_exp", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private double springExperience;
}
