package br.com.bruni.mongo.abelha.db.model;

import org.springframework.data.annotation.Id;

public class Order {

    @Id
    private String id;
    private String status;
    private String productName;
    private float value;

    public Order(String id, String productName, float value, String status, String productType) {
        this.id = id;
        this.productName = productName;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
