package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security {
    @Id
    private String id;
    private String qualifications;
    private String workSchedule;
//    @OneToOne
//    @JoinColumn(name = "id_user")
//    private User user;
}
