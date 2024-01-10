package com.example.datagenerator.mongoDB.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


public enum ApplicationType {

    PRZYZNANIE_MIEJSCA,
    PRZEDLUZENIE_WYNAJMU,
    ZMIANA_POKOJU,
    ZMIANA_AKADEMIKA,
    PRZEDWCZESNA_REZYGNACJA

}
