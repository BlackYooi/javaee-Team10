package com.kindblack.team10.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;


/**
 * @author black
 * @date 2019/12/29 - 23:16
 */
@Entity(name = "resume")
@NamedEntityGraph(name = "resume.all", attributeNodes = {@NamedAttributeNode("recruit")})
public class Resume {
    private String id;
    private String name;
    private String age;
    private String howToContact;
    private String workingYears;
    private String jobSummary;
    private String educationExperience;
    private String projectExperience;
    private String deliverId;

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", length = 3)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "howtocontact", length = 50)
    public String getHowToContact() {
        return howToContact;
    }

    public void setHowToContact(String howToContact) {
        this.howToContact = howToContact;
    }

    @Basic
    @Column(name = "workingyears")
    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    @Basic
    @Column(name = "jobsummary")
    public String getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(String jobSummary) {
        this.jobSummary = jobSummary;
    }

    @Basic
    @Column(name = "educationexperience")
    public String getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }

    @Basic
    @Column(name = "projectexperience")
    public String getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(String projectExperience) {
        this.projectExperience = projectExperience;
    }

    @Basic
    @Column(name = "deliverid", length = 32)
    public String getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(String deliverId) {
        this.deliverId = deliverId;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", howToContact='" + howToContact + '\'' +
                '}';
    }

    private Recruit recruit;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    @JsonIgnoreProperties("resumes")
    /* @NotFound(action= NotFoundAction.IGNORE)*/
    public Recruit getRecruit() {
        return recruit;
    }

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
    }
}
