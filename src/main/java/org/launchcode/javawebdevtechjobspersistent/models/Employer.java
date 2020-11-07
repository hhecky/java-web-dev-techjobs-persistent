package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Location is required")
    private String location;

    @OneToMany //removed (mappedBy = "employer") as threw error, can't use it w/@JoinColumn
    @JoinColumn(name = "employer_id", insertable=false, updatable=false) //is this correct? hibernate annotations ,insertable=false, updatable=false)???
    private final List<Job> jobs = new ArrayList<>();

    public Employer(){};

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
