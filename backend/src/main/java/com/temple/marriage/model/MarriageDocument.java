package com.temple.marriage.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "marriage_documents")
public class MarriageDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_number", unique = true)
    private String applicationNumber;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String bridePhoto;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String groomPhoto;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String brideAadharCard;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String groomAadharCard;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String familySmartCard;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String parentAadhar;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String communityCertificate;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String birthCertificate;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String marriageInvitation;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @PrePersist
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }

    // Getters and Setters
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

    public String getBrideAadharCard() {
        return brideAadharCard;
    }

    public void setBrideAadharCard(String brideAadharCard) {
        this.brideAadharCard = brideAadharCard;
    }

    public String getGroomAadharCard() {
        return groomAadharCard;
    }

    public void setGroomAadharCard(String groomAadharCard) {
        this.groomAadharCard = groomAadharCard;
    }

    public String getFamilySmartCard() {
        return familySmartCard;
    }

    public void setFamilySmartCard(String familySmartCard) {
        this.familySmartCard = familySmartCard;
    }

    public String getParentAadhar() {
        return parentAadhar;
    }

    public void setParentAadhar(String parentAadhar) {
        this.parentAadhar = parentAadhar;
    }

    public String getCommunityCertificate() {
        return communityCertificate;
    }

    public void setCommunityCertificate(String communityCertificate) {
        this.communityCertificate = communityCertificate;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getMarriageInvitation() {
        return marriageInvitation;
    }

    public void setMarriageInvitation(String marriageInvitation) {
        this.marriageInvitation = marriageInvitation;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
