package ru.twemi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="fundraising_amount")
    private String fundraisingAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User projectOwner;

    @ManyToMany
    @JoinTable(
            name = "user_sub",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "sub_id")}
    )
    private Set<User> subscribers;

    @ManyToOne
    @JoinTable(name = "category_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category categories;

}
