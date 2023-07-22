package com.example.receiptprocessor.controller;

import com.example.receiptprocessor.model.Receipt;
import com.example.receiptprocessor.repository.ReceiptRepository;
import com.example.receiptprocessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    ReceiptService receiptService;

    @PostMapping("/receipts/process")
    public ResponseEntity<Object> processDate(@RequestBody Receipt receipt) {
        Receipt receipt1 = receiptRepository.save(receipt);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("generated_id", receipt1.getId());
        map.put("message", "Successfully Data Inserted");
        map.put("status", 200);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/getAllReceipts")
    public ResponseEntity<?> getAllReceipts() {
        return ResponseEntity.status(200).body(receiptRepository.findAll());
    }

    @GetMapping("/receipts/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable Long id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (receipt.isPresent()) {
            int points = receiptService.getPoints(receipt);
            map.put("points", points);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("Error", "ID not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
