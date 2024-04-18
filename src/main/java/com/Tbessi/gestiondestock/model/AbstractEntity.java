package com.Tbessi.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @CreatedDate
    @Column(name="creationDate",nullable = false)
    @JsonIgnore
    private Instant creationDate;
    @LastModifiedBy
    @Column(name ="lastModifiedDate" )
    @JsonIgnore
    private Instant lastModifiedDate;
}
