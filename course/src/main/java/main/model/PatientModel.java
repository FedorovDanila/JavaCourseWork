package main.model;

import main.entity.Patient;

public class PatientModel {
    private String firstName;
    private String lastName;
    private String fatherName;
    private long diagnosisId;
    private long wardId;

    public PatientModel(Patient patient) {
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.fatherName = patient.getFatherName();
        this.diagnosisId = patient.getDiagnosis().getId();
        this.wardId = patient.getWard().getId();
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

    public long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(long id) {
        this.diagnosisId = id;
    }

    public long getWardId() {
        return wardId;
    }

    public void setWardId(long id) {
        this.wardId = id;
    }
}
