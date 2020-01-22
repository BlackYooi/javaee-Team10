package com.kindblack.team10.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author black
 * @date 2019/12/29 - 23:33
 */

@Entity
@NamedEntityGraph(name = "recruit.all", attributeNodes = {@NamedAttributeNode("resumes")})
public class Recruit {
    private String id;
    private String jobKey;
    private String jobDescribe;
    private String requir;
    private String publisherId;

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jobkey", length = 50)
    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    @Basic
    @Column(name = "jobdescribe")
    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    @Basic
    @Column(name = "requir")
    public String getRequir() {
        return requir;
    }

    public void setRequir(String requir) {
        this.requir = requir;
    }

    @Basic
    @Column(name = "publisherid", length = 32)
    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "jobKey='" + jobKey + '\'' +
                ", resumes=" + resumes +
                '}';
    }

    private List<Resume> resumes = new ArrayList<>();

    @OneToMany(mappedBy = "recruit", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("recruit")
    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public boolean addResume(Resume resume) {
        return this.resumes.add(resume);
    }
}
