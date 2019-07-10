package com.thoughtworks.gauge.example.java;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.springframework.stereotype.Component;

import static junit.framework.TestCase.assertEquals;

@Component
public class PaymentService {
    private DebitCardProcessor processor;

    public PaymentService(DebitCardProcessor processor) {
        this.processor = processor;
    }

    @Step("Pay amount <rupees> rupees")
    public void payMoney(String amount) {
        int rupees = Integer.parseInt(amount);
        Receipt receipt = this.processor.charge(rupees);

        assertEquals(receipt.getPaymentStatus(), ReceiptStatus.SUCCESS);
    }

    @Step("Fail to pay <100> rupees")
    public void failurePayment(String amount) {
        int rupees = Integer.parseInt(amount);
        Receipt receipt = this.processor.charge(rupees);

        assertEquals(receipt.getPaymentStatus(), ReceiptStatus.FAILURE);
    }

    @Step("支付失败 <3000>")
    public void failurePayment3000(String amount) {
        int rupees = Integer.parseInt(amount);
        Receipt receipt = this.processor.charge(rupees);
        assertEquals(receipt.getPaymentStatus(), ReceiptStatus.FAILURE);
    }

    @Step("人员信息 <race> 打印 <table>")
    public void implementation1(String race, Table table) {
        for (TableRow row : table.getTableRows()) {
            User user=new User();
            user.setId(row.getCell("id"));
            user.setName(row.getCell("name"));
            System.out.println(user.toString());
        }

    }
}