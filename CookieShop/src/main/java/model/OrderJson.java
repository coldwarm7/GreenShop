package model;

import java.util.List;

public class OrderJson {

    /**
     * itemList : [{"amount":1,"goodsName":"猕猴桃","id":6,"price":6.01}]
     * paytype : 1
     * status : 2
     * user_id : 25
     */

    private int paytype;
    private int status;
    private int user_id;
    private List<OrderItem> itemList;

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }



}
