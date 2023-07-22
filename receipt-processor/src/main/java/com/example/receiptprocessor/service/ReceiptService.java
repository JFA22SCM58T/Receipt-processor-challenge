package com.example.receiptprocessor.service;

import com.example.receiptprocessor.model.Receipt;
import com.example.receiptprocessor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;


    public Receipt processReceiptData(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public int getPoints(Optional<Receipt> receipt) {

        String removeWhileSpace = receipt.get().getRetailer().replace("\\s", "");

        String regex = "[a-zA-Z0-9]";
        int points = 0;
        for (int i = 0; i < removeWhileSpace.length(); i++) {
            if (Character.toString(removeWhileSpace.charAt(i)).matches(regex)) {
                points += 1;
            }
        }

        if ((int) Double.parseDouble(receipt.get().getTotal()) == Double.parseDouble(receipt.get().getTotal())) {
            points += 50;
        }

        if (Double.parseDouble(receipt.get().getTotal()) % 0.25 == 0) {
            points += 25;
        }

        points += 5 * ((int) receipt.get().getItems().stream().count() / 2);

        int data = 0;
        for (String s : receipt.get().getPurchaseDate().split("-")) {
            data = Integer.parseInt(s);
        }

        if (data % 2 != 0) {
            points += 6;
        }

        Double price = (double) 0;
        for (int i = 0; i < receipt.get().getItems().size(); i++) {
            if (receipt.get().getItems().get(i).getShortDescription().trim().length() % 3 == 0) {
                Double variable = Double.parseDouble(receipt.get().getItems().get(i).getPrice()) * 0.2;
                points += (int) Math.ceil(variable);
            }
        }

        LocalTime startTime = LocalTime.parse(receipt.get().getPurchaseTime());
        if ((startTime).isAfter(LocalTime.parse("14:00")) && startTime.isBefore((LocalTime.parse("16:00")))) {
            points += 10;
        }
        return points;
    }
}
