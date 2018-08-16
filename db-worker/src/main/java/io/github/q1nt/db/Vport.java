package io.github.q1nt.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vport")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vport {

    @Id
    private String foo;
    @Column
    private String status;
}
