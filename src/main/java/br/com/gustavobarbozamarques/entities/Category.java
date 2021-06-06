package br.com.gustavobarbozamarques.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(generator = "category_seq_generator")
    @SequenceGenerator(name = "category_seq_generator", sequenceName = "category_seq", allocationSize = 1)
    private Integer id;

    private String name;

}
