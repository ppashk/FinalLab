package com.lab.entity;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int id;
    private int totalPrice;
    private boolean isClosed;
    private List<Line> lines;

    public Receipt() {
    }

    public Receipt(int totalPrice, boolean isClosed) {
        this.totalPrice = totalPrice;
        this.isClosed = isClosed;
        this.lines = new ArrayList<>();
    }

    public Receipt(int id, int totalPrice, boolean isClosed) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.isClosed = isClosed;
        this.lines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
