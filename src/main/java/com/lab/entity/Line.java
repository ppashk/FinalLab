package com.lab.entity;

public class Line extends Product {
    private int receiptId;

    public Line(String name, int price, int quantity, int receiptId) {
        super(name, price, quantity);
        this.receiptId = receiptId;
    }

    public Line(int id, String name, int price, int quantity, int receiptId) {
        super(id, name, price, quantity);
        this.receiptId = receiptId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
