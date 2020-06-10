package main.entity;

import javax.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS", nullable = false)
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "WARD", nullable = false)
    private Ward ward;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String fatherName, Diagnosis diagnosis, Ward ward) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.diagnosis = diagnosis;
        this.ward = ward;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
}
