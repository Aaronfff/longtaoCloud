package fun.longtao.waiter.model;

import fun.longtao.waiter.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.Date;

import org.joda.money.Money;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_order
 */
public class Order implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.id
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.create_time
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.update_time
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.customer
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private String customer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.waiter
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private String waiter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.barista
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private String barista;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.discount
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private Integer discount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.total
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private Money total;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.state
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private OrderStatus state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.id
     *
     * @return the value of t_order.id
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withId(Long id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.id
     *
     * @param id the value for t_order.id
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.create_time
     *
     * @return the value of t_order.create_time
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.create_time
     *
     * @param createTime the value for t_order.create_time
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.update_time
     *
     * @return the value of t_order.update_time
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.update_time
     *
     * @param updateTime the value for t_order.update_time
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.customer
     *
     * @return the value of t_order.customer
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withCustomer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.customer
     *
     * @param customer the value for t_order.customer
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.waiter
     *
     * @return the value of t_order.waiter
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public String getWaiter() {
        return waiter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withWaiter(String waiter) {
        this.setWaiter(waiter);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.waiter
     *
     * @param waiter the value for t_order.waiter
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setWaiter(String waiter) {
        this.waiter = waiter == null ? null : waiter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.barista
     *
     * @return the value of t_order.barista
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public String getBarista() {
        return barista;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withBarista(String barista) {
        this.setBarista(barista);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.barista
     *
     * @param barista the value for t_order.barista
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setBarista(String barista) {
        this.barista = barista == null ? null : barista.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.discount
     *
     * @return the value of t_order.discount
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withDiscount(Integer discount) {
        this.setDiscount(discount);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.discount
     *
     * @param discount the value for t_order.discount
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.total
     *
     * @return the value of t_order.total
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Money getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withTotal(Money total) {
        this.setTotal(total);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.total
     *
     * @param total the value for t_order.total
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setTotal(Money total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.state
     *
     * @return the value of t_order.state
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public OrderStatus getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public Order withState(OrderStatus state) {
        this.setState(state);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.state
     *
     * @param state the value for t_order.state
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    public void setState(OrderStatus state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", customer=").append(customer);
        sb.append(", waiter=").append(waiter);
        sb.append(", barista=").append(barista);
        sb.append(", discount=").append(discount);
        sb.append(", total=").append(total);
        sb.append(", state=").append(state);
        sb.append("]");
        return sb.toString();
    }
}