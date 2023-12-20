package model;

import java.sql.Timestamp;

public class Receipt {
    private int receiptId;
    private int receiptOrderId;
    private int receiptPaymentAmount;
    private Timestamp receiptPaymentDate;
    private String receiptPaymentType;

    public Receipt(int receiptId, int receiptOrderId, int receiptPaymentAmount, Timestamp receiptPaymentDate,
            String receiptPaymentType) {
        this.receiptId = receiptId;
        this.receiptOrderId = receiptOrderId;
        this.receiptPaymentAmount = receiptPaymentAmount;
        this.receiptPaymentDate = receiptPaymentDate;
        this.receiptPaymentType = receiptPaymentType;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getReceiptOrderId() {
        return receiptOrderId;
    }

    public void setReceiptOrderId(int receiptOrderId) {
        this.receiptOrderId = receiptOrderId;
    }

    public int getReceiptPaymentAmount() {
        return receiptPaymentAmount;
    }

    public void setReceiptPaymentAmount(int receiptPaymentAmount) {
        this.receiptPaymentAmount = receiptPaymentAmount;
    }

    public Timestamp getReceiptPaymentDate() {
        return receiptPaymentDate;
    }

    public void setReceiptPaymentDate(Timestamp receiptPaymentDate) {
        this.receiptPaymentDate = receiptPaymentDate;
    }

    public String getReceiptPaymentType() {
        return receiptPaymentType;
    }

    public void setReceiptPaymentType(String receiptPaymentType) {
        this.receiptPaymentType = receiptPaymentType;
    }

}
