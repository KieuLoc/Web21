package application.model.viewmodel.order;

import application.model.viewmodel.common.LayoutHeaderVM;

import java.util.Date;
import java.util.List;

public class OrderDetailVM {

    private LayoutHeaderVM layoutHeaderVM;
    private List<OrderProductVM> orderProductVMS;
    private String totalPrice;
    private int totalProduct;
    private String customerName;
    private Date createdDate;
    private String status;

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<OrderProductVM> getOrderProductVMS() {
        return orderProductVMS;
    }

    public void setOrderProductVMS(List<OrderProductVM> orderProductVMS) {
        this.orderProductVMS = orderProductVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setOrderStatus(String status) {
        this.status = status;
    }
}
