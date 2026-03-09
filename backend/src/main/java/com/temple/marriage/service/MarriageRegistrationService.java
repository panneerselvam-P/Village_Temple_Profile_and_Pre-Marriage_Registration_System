package com.temple.marriage.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.temple.marriage.model.MarriageRegistration;
import com.temple.marriage.model.MarriageDocument;
import com.temple.marriage.model.MarriageDocumentRepository;
import com.temple.marriage.repository.MarriageRegistrationRepository;
import com.temple.marriage.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MarriageRegistrationService {

    @Autowired
    private MarriageRegistrationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarriageDocumentRepository documentRepository;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public MarriageRegistration saveRegistration(MarriageRegistration registration) {
        if (registration.getApplicationNumber() == null || registration.getApplicationNumber().isEmpty()) {
            if (registration.getGroomPhone() != null && !registration.getGroomPhone().isEmpty()) {
                userRepository.findByMobileNumber(registration.getGroomPhone())
                        .ifPresent(u -> registration.setApplicationNumber(u.getApplicationNumber()));
            }
            if (registration.getApplicationNumber() == null && registration.getBridePhone() != null
                    && !registration.getBridePhone().isEmpty()) {
                userRepository.findByMobileNumber(registration.getBridePhone())
                        .ifPresent(u -> registration.setApplicationNumber(u.getApplicationNumber()));
            }
        }
        return repository.save(registration);
    }

    public void saveDocuments(MarriageDocument documents) {
        if (documents != null && documents.getApplicationNumber() != null) {
            // Check if documents already exist for this application
            documentRepository.findByApplicationNumber(documents.getApplicationNumber())
                    .ifPresent(existing -> documents.setId(existing.getId()));

            documents.setUploadedAt(LocalDateTime.now());
            documentRepository.save(documents);
        }
    }

    public String createRazorpayOrder(double amount) throws Exception {
        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Amount in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + UUID.randomUUID().toString());
        orderRequest.put("payment_capture", 1);

        Order order = razorpay.orders.create(orderRequest);
        return order.get("id");
    }

    public void updatePaymentStatus(Long registrationId, String paymentId, String status) {
        repository.findById(registrationId).ifPresent(registration -> {
            registration.setRazorpayPaymentId(paymentId);
            registration.setPaymentStatus(status);
            repository.save(registration);
        });
    }

    public MarriageRegistration getRegistration(Long id) {
        return repository.findById(id).map(this::enrichRegistration).orElse(null);
    }

    public List<MarriageRegistration> getAllRegistrations() {
        List<MarriageRegistration> list = repository.findAll();
        for (MarriageRegistration reg : list) {
            enrichRegistration(reg);
        }
        return list;
    }

    private MarriageRegistration enrichRegistration(MarriageRegistration reg) {
        String foundAppNo = reg.getApplicationNumber();
        if (foundAppNo == null || foundAppNo.isEmpty()) {
            if (reg.getGroomPhone() != null && !reg.getGroomPhone().isEmpty()) {
                foundAppNo = userRepository.findByMobileNumber(reg.getGroomPhone())
                        .map(u -> u.getApplicationNumber()).orElse(null);
            }
            if (foundAppNo == null && reg.getBridePhone() != null && !reg.getBridePhone().isEmpty()) {
                foundAppNo = userRepository.findByMobileNumber(reg.getBridePhone())
                        .map(u -> u.getApplicationNumber()).orElse(null);
            }
            if (foundAppNo != null) {
                reg.setApplicationNumber(foundAppNo);
            }
        }

        // Fetch Photos from Documents table
        if (reg.getApplicationNumber() != null && !reg.getApplicationNumber().isEmpty()) {
            documentRepository.findByApplicationNumber(reg.getApplicationNumber()).ifPresent(docs -> {
                reg.setBridePhoto(docs.getBridePhoto());
                reg.setGroomPhoto(docs.getGroomPhoto());
            });
        }

        return reg;
    }

    public MarriageRegistration updateRegistration(Long id, MarriageRegistration updatedData) {
        return repository.findById(id).map(registration -> {
            registration.setApplicationNumber(updatedData.getApplicationNumber());

            // Bride Details
            registration.setBrideName(updatedData.getBrideName());
            registration.setBrideInitial(updatedData.getBrideInitial());
            registration.setBrideDob(updatedData.getBrideDob());
            registration.setBrideAge(updatedData.getBrideAge());
            registration.setBrideEmail(updatedData.getBrideEmail());
            registration.setBrideAadhar(updatedData.getBrideAadhar());
            registration.setBrideRegion(updatedData.getBrideRegion());
            registration.setBrideReligion(updatedData.getBrideReligion());
            registration.setBrideCaste(updatedData.getBrideCaste());
            registration.setBrideSubcaste(updatedData.getBrideSubcaste());
            registration.setBrideEducation(updatedData.getBrideEducation());
            registration.setBridePhone(updatedData.getBridePhone());
            registration.setBrideMaritalStatus(updatedData.getBrideMaritalStatus());

            // Groom Details
            registration.setGroomName(updatedData.getGroomName());
            registration.setGroomInitial(updatedData.getGroomInitial());
            registration.setGroomDob(updatedData.getGroomDob());
            registration.setGroomAge(updatedData.getGroomAge());
            registration.setGroomEmail(updatedData.getGroomEmail());
            registration.setGroomAadhar(updatedData.getGroomAadhar());
            registration.setGroomRegion(updatedData.getGroomRegion());
            registration.setGroomReligion(updatedData.getGroomReligion());
            registration.setGroomCaste(updatedData.getGroomCaste());
            registration.setGroomSubcaste(updatedData.getGroomSubcaste());
            registration.setGroomEducation(updatedData.getGroomEducation());
            registration.setGroomPhone(updatedData.getGroomPhone());
            registration.setGroomMaritalStatus(updatedData.getGroomMaritalStatus());

            // Parents Details
            registration.setBrideFatherName(updatedData.getBrideFatherName());
            registration.setBrideFatherDob(updatedData.getBrideFatherDob());
            registration.setBrideFatherAadhar(updatedData.getBrideFatherAadhar());
            registration.setBrideMotherName(updatedData.getBrideMotherName());
            registration.setBrideMotherDob(updatedData.getBrideMotherDob());
            registration.setBrideMotherAadhar(updatedData.getBrideMotherAadhar());

            registration.setGroomFatherName(updatedData.getGroomFatherName());
            registration.setGroomFatherDob(updatedData.getGroomFatherDob());
            registration.setGroomFatherAadhar(updatedData.getGroomFatherAadhar());
            registration.setGroomMotherName(updatedData.getGroomMotherName());
            registration.setGroomMotherDob(updatedData.getGroomMotherDob());
            registration.setGroomMotherAadhar(updatedData.getGroomMotherAadhar());

            // Witness Details
            registration.setBrideWitnessRelation(updatedData.getBrideWitnessRelation());
            registration.setBrideWitnessName(updatedData.getBrideWitnessName());
            registration.setBrideWitnessAddress(updatedData.getBrideWitnessAddress());
            registration.setBrideWitnessAadhar(updatedData.getBrideWitnessAadhar());

            registration.setGroomWitnessRelation(updatedData.getGroomWitnessRelation());
            registration.setGroomWitnessName(updatedData.getGroomWitnessName());
            registration.setGroomWitnessAddress(updatedData.getGroomWitnessAddress());
            registration.setGroomWitnessAadhar(updatedData.getGroomWitnessAadhar());

            // Marriage Details
            registration.setReceptionPlace(updatedData.getReceptionPlace());
            registration.setReceptionDate(updatedData.getReceptionDate());
            registration.setMarriageDate(updatedData.getMarriageDate());
            registration.setMarriageTime(updatedData.getMarriageTime());

            // Address Details
            registration.setBridePermanentAddress(updatedData.getBridePermanentAddress());
            registration.setGroomPermanentAddress(updatedData.getGroomPermanentAddress());

            // Payment Details
            registration.setRazorpayPaymentId(updatedData.getRazorpayPaymentId());
            registration.setPaymentStatus(updatedData.getPaymentStatus());

            return repository.save(registration);
        }).orElse(null);
    }

    public void updateStatus(Long id, String status) {
        repository.findById(id).ifPresent(registration -> {
            registration.setPaymentStatus(status);
            repository.save(registration);
        });
    }

    @Transactional
    public MarriageRegistration registerWithDocuments(MarriageRegistration registration, MarriageDocument documents) {
        MarriageRegistration savedRegistration = saveRegistration(registration);
        if (documents != null) {
            documents.setApplicationNumber(savedRegistration.getApplicationNumber());
            saveDocuments(documents);
        }
        return savedRegistration;
    }

    public boolean deleteRegistration(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
