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
    private int receiptId = 0;

    public ReceiptService() {
        this.receiptDao = DaoFactory.getEntityDao(DaoType.RECEIPT);
        this.lineDao = DaoFactory.getEntityDao(DaoType.LINE);
        this.productDao = DaoFactory.getEntityDao(DaoType.PRODUCT);
    }

    public boolean createReceipt() {
        if (receiptId == 0){
            receiptId++;
            Receipt receipt = new Receipt(0, false);
            return receiptDao.create(receipt);
        }

        List<Receipt> receipts = receiptDao.getAll();

        for (Receipt receipt : receipts) {
            if (receipt.isClosed())
                receiptId = receipt.getId();
                receipts.remove(receipt);
        }

        if (receipts.isEmpty()){
            receiptId++;
            Receipt receipt = new Receipt(0, false);
            return receiptDao.create(receipt);
        } else
            receiptId++;

        return false;
    }

    public boolean closeReceipt() {
        Receipt receipt = getReceiptById();
        receipt.setClosed(true);
        return receiptDao.update(receipt);
    }

    public boolean deleteReceipt(int receiptId) {
        Receipt receipt = getReceiptById(receiptId);

        for (Line line : receipt.getLines())
            lineDao.remove(line);

        return receiptDao.remove(receipt);
    }

    public boolean addLine(String param, int quantity) {
        Receipt receipt = getReceiptById();
        List<Product> products = productDao.getAll();

        for (Product product : products) {
            if (product.getId() == Integer.parseInt(param) || param.equals(product.getName()) && product.getQuantity() >= quantity) {
                lineDao.create(new Line(product.getName(), product.getPrice(), quantity, receiptId));
                product.setQuantity(product.getQuantity() - quantity);
                productDao.update(product);
                receipt.setTotalPrice(receipt.getTotalPrice() + product.getPrice() * quantity);
                receiptDao.update(receipt);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLine(String param) {
        List<Line> lines = lineDao.getAll();
        for (Line line : lines)
            if (Integer.parseInt(param) == line.getId() || param.equals(line.getName()))
                lineDao.remove(line);
        return true;
    }

    public List<Line> getLinesByReceiptId() {
        if (receiptId > 0 ) {
            List<Line> lines = lineDao.getAll();
            for (Line line : lines) {
                if (line.getReceiptId() != receiptId)
                    lines.remove(line);
            }
            return lines;
        }
        return null;
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
    public Receipt getReceiptById() {
        Receipt receipt = receiptDao.getById(receiptId, true);
        List<Line> lines = lineDao.getAll();

        for (Line line : lines) {
            if (line.getReceiptId() == receiptId)
                receipt.addLine(line);
        }

        return receipt;
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
