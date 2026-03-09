package com.temple.marriage.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "marriage_registrations")
public class MarriageRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_number")
    private String applicationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_number", referencedColumnName = "applicationNumber", insertable = false, updatable = false)
    private User user;

    // Bride Details
    private String brideName;
    private String brideInitial;
    private LocalDate brideDob;
    private Integer brideAge;
    private String brideEmail;
    private String brideAadhar;
    private String brideRegion;
    private String brideReligion;
    private String brideCaste;
    private String brideSubcaste;
    private String brideEducation;
    private String bridePhone;
    private String brideMaritalStatus;

    // Groom Details
    private String groomName;
    private String groomInitial;
    private LocalDate groomDob;
    private Integer groomAge;
    private String groomEmail;
    private String groomAadhar;
    private String groomRegion;
    private String groomReligion;
    private String groomCaste;
    private String groomSubcaste;
    private String groomEducation;
    private String groomPhone;
    private String groomMaritalStatus;

    // Parents Details - Bride
    private String brideFatherName;
    private LocalDate brideFatherDob;
    private String brideFatherAadhar;
    private String brideMotherName;
    private LocalDate brideMotherDob;
    private String brideMotherAadhar;

    // Parents Details - Groom
    private String groomFatherName;
    private LocalDate groomFatherDob;
    private String groomFatherAadhar;
    private String groomMotherName;
    private LocalDate groomMotherDob;
    private String groomMotherAadhar;

    // Witness Details
    private String brideWitnessRelation;
    private String brideWitnessName;
    private String brideWitnessAddress;
    private String brideWitnessAadhar;

    private String groomWitnessRelation;
    private String groomWitnessName;
    private String groomWitnessAddress;
    private String groomWitnessAadhar;

    // Marriage Details
    private String receptionPlace;
    private LocalDate receptionDate;
    private LocalDate marriageDate;
    private LocalTime marriageTime;

    // Address Details (Simplified for storage, assuming full string or key fields)
    @Column(length = 1000)
    private String bridePermanentAddress; // JSON or Comma Separated
    @Column(length = 1000)
    private String groomPermanentAddress; // JSON or Comma Separated

    private String razorpayPaymentId;
    private String paymentStatus;

    @Transient
    private String bridePhoto;

    @Transient
    private String groomPhoto;

    public MarriageRegistration() {
    }

    // Getters and Setters (Manual)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }

    public String getBrideInitial() {
        return brideInitial;
    }

    public void setBrideInitial(String brideInitial) {
        this.brideInitial = brideInitial;
    }

    public LocalDate getBrideDob() {
        return brideDob;
    }

    public void setBrideDob(LocalDate brideDob) {
        this.brideDob = brideDob;
    }

    public Integer getBrideAge() {
        return brideAge;
    }

    public void setBrideAge(Integer brideAge) {
        this.brideAge = brideAge;
    }

    public String getBrideEmail() {
        return brideEmail;
    }

    public void setBrideEmail(String brideEmail) {
        this.brideEmail = brideEmail;
    }

    public String getBrideAadhar() {
        return brideAadhar;
    }

    public void setBrideAadhar(String brideAadhar) {
        this.brideAadhar = brideAadhar;
    }

    public String getBrideRegion() {
        return brideRegion;
    }

    public void setBrideRegion(String brideRegion) {
        this.brideRegion = brideRegion;
    }

    public String getBrideReligion() {
        return brideReligion;
    }

    public void setBrideReligion(String brideReligion) {
        this.brideReligion = brideReligion;
    }

    public String getBrideCaste() {
        return brideCaste;
    }

    public void setBrideCaste(String brideCaste) {
        this.brideCaste = brideCaste;
    }

    public String getBrideSubcaste() {
        return brideSubcaste;
    }

    public void setBrideSubcaste(String brideSubcaste) {
        this.brideSubcaste = brideSubcaste;
    }

    public String getBrideEducation() {
        return brideEducation;
    }

    public void setBrideEducation(String brideEducation) {
        this.brideEducation = brideEducation;
    }

    public String getBridePhone() {
        return bridePhone;
    }

    public void setBridePhone(String bridePhone) {
        this.bridePhone = bridePhone;
    }

    public String getBrideMaritalStatus() {
        return brideMaritalStatus;
    }

    public void setBrideMaritalStatus(String brideMaritalStatus) {
        this.brideMaritalStatus = brideMaritalStatus;
    }

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getGroomInitial() {
        return groomInitial;
    }

    public void setGroomInitial(String groomInitial) {
        this.groomInitial = groomInitial;
    }

    public LocalDate getGroomDob() {
        return groomDob;
    }

    public void setGroomDob(LocalDate groomDob) {
        this.groomDob = groomDob;
    }

    public Integer getGroomAge() {
        return groomAge;
    }

    public void setGroomAge(Integer groomAge) {
        this.groomAge = groomAge;
    }

    public String getGroomEmail() {
        return groomEmail;
    }

    public void setGroomEmail(String groomEmail) {
        this.groomEmail = groomEmail;
    }

    public String getGroomAadhar() {
        return groomAadhar;
    }

    public void setGroomAadhar(String groomAadhar) {
        this.groomAadhar = groomAadhar;
    }

    public String getGroomRegion() {
        return groomRegion;
    }

    public void setGroomRegion(String groomRegion) {
        this.groomRegion = groomRegion;
    }

    public String getGroomReligion() {
        return groomReligion;
    }

    public void setGroomReligion(String groomReligion) {
        this.groomReligion = groomReligion;
    }

    public String getGroomCaste() {
        return groomCaste;
    }

    public void setGroomCaste(String groomCaste) {
        this.groomCaste = groomCaste;
    }

    public String getGroomSubcaste() {
        return groomSubcaste;
    }

    public void setGroomSubcaste(String groomSubcaste) {
        this.groomSubcaste = groomSubcaste;
    }

    public String getGroomEducation() {
        return groomEducation;
    }

    public void setGroomEducation(String groomEducation) {
        this.groomEducation = groomEducation;
    }

    public String getGroomPhone() {
        return groomPhone;
    }

    public void setGroomPhone(String groomPhone) {
        this.groomPhone = groomPhone;
    }

    public String getGroomMaritalStatus() {
        return groomMaritalStatus;
    }

    public void setGroomMaritalStatus(String groomMaritalStatus) {
        this.groomMaritalStatus = groomMaritalStatus;
    }

    public String getBrideFatherName() {
        return brideFatherName;
    }

    public void setBrideFatherName(String brideFatherName) {
        this.brideFatherName = brideFatherName;
    }

    public LocalDate getBrideFatherDob() {
        return brideFatherDob;
    }

    public void setBrideFatherDob(LocalDate brideFatherDob) {
        this.brideFatherDob = brideFatherDob;
    }

    public String getBrideFatherAadhar() {
        return brideFatherAadhar;
    }

    public void setBrideFatherAadhar(String brideFatherAadhar) {
        this.brideFatherAadhar = brideFatherAadhar;
    }

    public String getBrideMotherName() {
        return brideMotherName;
    }

    public void setBrideMotherName(String brideMotherName) {
        this.brideMotherName = brideMotherName;
    }

    public LocalDate getBrideMotherDob() {
        return brideMotherDob;
    }

    public void setBrideMotherDob(LocalDate brideMotherDob) {
        this.brideMotherDob = brideMotherDob;
    }

    public String getBrideMotherAadhar() {
        return brideMotherAadhar;
    }

    public void setBrideMotherAadhar(String brideMotherAadhar) {
        this.brideMotherAadhar = brideMotherAadhar;
    }

    public String getGroomFatherName() {
        return groomFatherName;
    }

    public void setGroomFatherName(String groomFatherName) {
        this.groomFatherName = groomFatherName;
    }

    public LocalDate getGroomFatherDob() {
        return groomFatherDob;
    }

    public void setGroomFatherDob(LocalDate groomFatherDob) {
        this.groomFatherDob = groomFatherDob;
    }

    public String getGroomFatherAadhar() {
        return groomFatherAadhar;
    }

    public void setGroomFatherAadhar(String groomFatherAadhar) {
        this.groomFatherAadhar = groomFatherAadhar;
    }

    public String getGroomMotherName() {
        return groomMotherName;
    }

    public void setGroomMotherName(String groomMotherName) {
        this.groomMotherName = groomMotherName;
    }

    public LocalDate getGroomMotherDob() {
        return groomMotherDob;
    }

    public void setGroomMotherDob(LocalDate groomMotherDob) {
        this.groomMotherDob = groomMotherDob;
    }

    public String getGroomMotherAadhar() {
        return groomMotherAadhar;
    }

    public void setGroomMotherAadhar(String groomMotherAadhar) {
        this.groomMotherAadhar = groomMotherAadhar;
    }

    public String getBrideWitnessRelation() {
        return brideWitnessRelation;
    }

    public void setBrideWitnessRelation(String brideWitnessRelation) {
        this.brideWitnessRelation = brideWitnessRelation;
    }

    public String getBrideWitnessName() {
        return brideWitnessName;
    }

    public void setBrideWitnessName(String brideWitnessName) {
        this.brideWitnessName = brideWitnessName;
    }

    public String getBrideWitnessAddress() {
        return brideWitnessAddress;
    }

    public void setBrideWitnessAddress(String brideWitnessAddress) {
        this.brideWitnessAddress = brideWitnessAddress;
    }

    public String getBrideWitnessAadhar() {
        return brideWitnessAadhar;
    }

    public void setBrideWitnessAadhar(String brideWitnessAadhar) {
        this.brideWitnessAadhar = brideWitnessAadhar;
    }

    public String getGroomWitnessRelation() {
        return groomWitnessRelation;
    }

    public void setGroomWitnessRelation(String groomWitnessRelation) {
        this.groomWitnessRelation = groomWitnessRelation;
    }

    public String getGroomWitnessName() {
        return groomWitnessName;
    }

    public void setGroomWitnessName(String groomWitnessName) {
        this.groomWitnessName = groomWitnessName;
    }

    public String getGroomWitnessAddress() {
        return groomWitnessAddress;
    }

    public void setGroomWitnessAddress(String groomWitnessAddress) {
        this.groomWitnessAddress = groomWitnessAddress;
    }

    public String getGroomWitnessAadhar() {
        return groomWitnessAadhar;
    }

    public void setGroomWitnessAadhar(String groomWitnessAadhar) {
        this.groomWitnessAadhar = groomWitnessAadhar;
    }

    public String getReceptionPlace() {
        return receptionPlace;
    }

    public void setReceptionPlace(String receptionPlace) {
        this.receptionPlace = receptionPlace;
    }

    public LocalDate getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDate receptionDate) {
        this.receptionDate = receptionDate;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public LocalTime getMarriageTime() {
        return marriageTime;
    }

    public void setMarriageTime(LocalTime marriageTime) {
        this.marriageTime = marriageTime;
    }

    public String getBridePermanentAddress() {
        return bridePermanentAddress;
    }

    public void setBridePermanentAddress(String bridePermanentAddress) {
        this.bridePermanentAddress = bridePermanentAddress;
    }

    public String getGroomPermanentAddress() {
        return groomPermanentAddress;
    }

    public void setGroomPermanentAddress(String groomPermanentAddress) {
        this.groomPermanentAddress = groomPermanentAddress;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getBridePhoto() {
        return bridePhoto;
    }

    public void setBridePhoto(String bridePhoto) {
        this.bridePhoto = bridePhoto;
    }

    public String getGroomPhoto() {
        return groomPhoto;
    }

    public void setGroomPhoto(String groomPhoto) {
        this.groomPhoto = groomPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
