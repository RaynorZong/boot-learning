package com.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "jpa_blog")
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    private User author;
}
