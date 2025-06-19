package job.ata.ata_exam.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Salary {

    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("Employer")
    private String employer;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("Job Title")
    private String jobTitle;

    @JsonProperty("Years at Employer")
    private String yearsAtEmployer;

    @JsonProperty("Years of Experience")
    private String yearsOfExperience;

    @JsonProperty("Salary")
    private String salary;

    @JsonProperty("Signing Bonus")
    private String signBonus;

    @JsonProperty("Annual Bonus")
    private String annualBonus;

    @JsonProperty("Annual Stock Value/Bonus")
    private String annualStockValue;

    @JsonProperty("Gender")
    private String gender;

    @JsonProperty("Additional Comments")
    private String comments;

    public Salary() {}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getYearsAtEmployer() {
        return yearsAtEmployer;
    }

    public void setYearsAtEmployer(String yearsAtEmployer) {
        this.yearsAtEmployer = yearsAtEmployer;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSignBonus() {
        return signBonus;
    }

    public void setSignBonus(String signBonus) {
        this.signBonus = signBonus;
    }

    public String getAnnualBonus() {
        return annualBonus;
    }

    public void setAnnualBonus(String annualBonus) {
        this.annualBonus = annualBonus;
    }

    public String getAnnualStockValue() {
        return annualStockValue;
    }

    public void setAnnualStockValue(String annualStockValue) {
        this.annualStockValue = annualStockValue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "timestamp='" + timestamp + '\'' +
                ", employer='" + employer + '\'' +
                ", location='" + location + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", yearsAtEmployer='" + yearsAtEmployer + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", salary='" + salary + '\'' +
                ", signBonus='" + signBonus + '\'' +
                ", annualBonus='" + annualBonus + '\'' +
                ", annualStockValue='" + annualStockValue + '\'' +
                ", gender='" + gender + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}