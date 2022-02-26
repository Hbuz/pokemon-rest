package com.poke.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @JsonIgnore
    @Id
    @GeneratedValue(generator="sqlite")
    @TableGenerator(name="sqlite", table="sqlite_sequence",
            pkColumnName="name", valueColumnName="seq",
            pkColumnValue="pokemon")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "moves")
    private String moves;

    @Column(name = "types")
    private String types;

    @Override
    public String toString() {
        return "{ name: '" + name + "\'," +
                " weight:'" + weight + "\'," +
                " height:'" + height + "\'," +
                " moves:'" + moves + "\'," +
                " types:'" + types + "\'" +
                "}";
    }
}
