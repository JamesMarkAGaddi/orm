package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId; // Use Long to accommodate numeric(5) in the database

    @Column(name = "cust_name", nullable = false, length = 30)
    private String customerName; // CUST_NAME

    @Column(name = "city", nullable = false, length = 15)
    private String city; // CITY

    @Column(name = "grade")
    private Integer grade; // GRADE

    @Column(name = "salesman_id", nullable = false)
    private Long salesmanId; // SALESMAN_ID

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(Long customerId, String customerName, String city, Integer grade, Long salesmanId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.city = city;
        this.grade = grade;
        this.salesmanId = salesmanId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", city='" + city + '\'' +
                ", grade=" + grade +
                ", salesmanId=" + salesmanId +
                '}';
    }
}
