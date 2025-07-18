package src.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Members {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}