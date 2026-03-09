CREATE DATABASE IF NOT EXISTS marriage_db;
USE marriage_db;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    dob DATE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255),
    place VARCHAR(255),
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    aadhaar_number VARCHAR(20) UNIQUE,
    application_number VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS marriage_registrations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_number VARCHAR(50),
    
    -- Bride Details
    bride_name VARCHAR(255),
    bride_initial VARCHAR(50),
    bride_dob DATE,
    bride_age INT,
    bride_email VARCHAR(255),
    bride_aadhar VARCHAR(20),
    bride_region VARCHAR(255),
    bride_religion VARCHAR(100),
    bride_caste VARCHAR(100),
    bride_subcaste VARCHAR(100),
    bride_education VARCHAR(255),
    bride_phone VARCHAR(20),
    bride_marital_status VARCHAR(50),
    
    -- Groom Details
    groom_name VARCHAR(255),
    groom_initial VARCHAR(50),
    groom_dob DATE,
    groom_age INT,
    groom_email VARCHAR(255),
    groom_aadhar VARCHAR(20),
    groom_region VARCHAR(255),
    groom_religion VARCHAR(100),
    groom_caste VARCHAR(100),
    groom_subcaste VARCHAR(100),
    groom_education VARCHAR(255),
    groom_phone VARCHAR(20),
    groom_marital_status VARCHAR(50),
    
    -- Parent Details
    bride_father_name VARCHAR(255),
    bride_father_dob DATE,
    bride_father_aadhar VARCHAR(20),
    bride_mother_name VARCHAR(255),
    bride_mother_dob DATE,
    bride_mother_aadhar VARCHAR(20),
    
    groom_father_name VARCHAR(255),
    groom_father_dob DATE,
    groom_father_aadhar VARCHAR(20),
    groom_mother_name VARCHAR(255),
    groom_mother_dob DATE,
    groom_mother_aadhar VARCHAR(20),
    
    -- Witness Details
    bride_witness_relation VARCHAR(100),
    bride_witness_name VARCHAR(255),
    bride_witness_address TEXT,
    bride_witness_aadhar VARCHAR(20),
    
    groom_witness_relation VARCHAR(100),
    groom_witness_name VARCHAR(255),
    groom_witness_address TEXT,
    groom_witness_aadhar VARCHAR(20),
    
    -- Marriage Details
    reception_place VARCHAR(255),
    reception_date DATE,
    marriage_date DATE,
    marriage_time TIME,
    
    -- Address Details
    bride_permanent_address TEXT,
    groom_permanent_address TEXT,
    
    -- Payment Details
    razorpay_payment_id VARCHAR(100),
    payment_status VARCHAR(50) DEFAULT 'PENDING',
    FOREIGN KEY (application_number) REFERENCES users(application_number) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS marriage_documents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_number VARCHAR(50),
    bride_photo LONGTEXT,
    groom_photo LONGTEXT,
    bride_aadhar_card LONGTEXT,
    groom_aadhar_card LONGTEXT,
    family_smart_card LONGTEXT,
    parent_aadhar LONGTEXT,
    community_certificate LONGTEXT,
    birth_certificate LONGTEXT,
    marriage_invitation LONGTEXT,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_number) REFERENCES users(application_number) ON DELETE CASCADE
);