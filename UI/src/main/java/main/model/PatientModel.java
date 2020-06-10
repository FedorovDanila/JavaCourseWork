package main.model;

public class PatientModel {
    private String firstName;
    private String lastName;
    private String fatherName;
    private long diagnosisId;
    private long wardId;

    public PatientModel(String firstName, String lastName, String fatherName, long diagnosisId, long wardId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.diagnosisId = diagnosisId;
        this.wardId = wardId;
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

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + fatherName + " in ward " + wardId + " with diagnosis " + diagnosisId;
    }
}
