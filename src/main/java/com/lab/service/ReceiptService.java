package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.Line;
import com.lab.entity.Product;
import com.lab.entity.Receipt;
import com.lab.enums.DaoType;
import com.lab.factory.DaoFactory;

import java.util.List;

public class ReceiptService {
    private EntityDao<Receipt> receiptDao;
    private EntityDao<Line> lineDao;
    private EntityDao<Product> productDao;

    public ReceiptService() {
        this.receiptDao = DaoFactory.getEntityDao(DaoType.RECEIPT);
        this.lineDao = DaoFactory.getEntityDao(DaoType.LINE);
        this.productDao = DaoFactory.getEntityDao(DaoType.PRODUCT);
    }

    private Integer getReceiptId() {
        List<Receipt> receipts = receiptDao.getAll();
        try {
            for (Receipt receipt : receipts) {
                if (!receipt.isClosed())
                    return receipt.getId();
            }
        } catch (Exception e) {
            return 1;
        }
        return null;
    }

    public Receipt getReceipt() {
        return getReceiptById(getReceiptId());
    }

    public boolean createReceipt() {
        if (getReceiptId() == null){
            Receipt receipt = new Receipt(0, false);
            return receiptDao.create(receipt);
        }
        return false;
    }

    public boolean closeReceipt() {
        Receipt receipt = getReceipt();
        receipt.setClosed(true);
        return receiptDao.update(receipt);
    }

    public boolean deleteReceipt(int receiptId) {
        Receipt receipt = getReceiptById(receiptId);

        for (Line line : receipt.getLines())
            lineDao.remove(line);

        return receiptDao.remove(receipt);
    }

    private boolean createLine(Receipt receipt, Product product, int quantity) {
        if (!lineDao.create(new Line(product.getName(), product.getPrice(), quantity, getReceiptId())))
            return false;
        product.setQuantity(product.getQuantity() - quantity);
        productDao.update(product);
        receipt.setTotalPrice(receipt.getTotalPrice() + product.getPrice() * quantity);
        receiptDao.update(receipt);
        return true;
    }

    public boolean addLine(String param, int quantity) {
        Receipt receipt = getReceipt();
        List<Product> products = productDao.getAll();

        for (Product product : products) {
            try {
                if (param.equals(product.getName()) || product.getId() == Integer.parseInt(param) && product.getQuantity() >= quantity) {
                    return createLine(receipt, product, quantity);
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return false;
    }

    public boolean deleteLine(String param) {
        List<Line> lines = lineDao.getAll();
        for (Line line : lines) {
            try {
                if (Integer.parseInt(param) == line.getId() || param.equals(line.getName())) {
                    Receipt receipt = getReceiptById(line.getReceiptId());
                    receipt.setTotalPrice(receipt.getTotalPrice() - line.getPrice() * line.getQuantity());
                    receiptDao.update(receipt);
                    return lineDao.remove(line);
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return false;
    }

    public List<Receipt> getAll() {
        List<Receipt> receipts = receiptDao.getAll();
        List<Line> lines = lineDao.getAll();

        for (Receipt receipt : receipts) {
            for (Line line : lines) {
                if (line.getReceiptId() == receipt.getId())
                    receipt.addLine(line);
            }
        }

        return receipts;
    }

    private Receipt getReceiptById(int receiptId) {
        Receipt receipt = receiptDao.getById(receiptId, true);
        List<Line> lines = lineDao.getAll();

        for (Line line : lines) {
            if (line.getReceiptId() == receiptId)
                receipt.addLine(line);
        }

        return receipt;
    }
}
