package Model.Receipt;

import java.sql.Timestamp;

public class Receipt {

	private int receiptOrder;
	private int receiptPaymentAmount;
	private Timestamp receiptPaymentDate;
	private String receiptPaymentType;

	public Receipt(int receiptOrder, int receiptPaymentAmount, Timestamp receiptPaymentDate,
			String receiptPaymentType) {

		this.receiptOrder = receiptOrder;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate;
		this.receiptPaymentType = receiptPaymentType;
	}

	public int getReceiptOrder() {
		return receiptOrder;
	}

	public void setReceiptOrder(int receiptOrder) {
		this.receiptOrder = receiptOrder;
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
