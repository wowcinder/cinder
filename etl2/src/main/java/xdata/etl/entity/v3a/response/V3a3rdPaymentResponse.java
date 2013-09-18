
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3a3rdPaymentResponse
    implements Serializable
{

    private final static long serialVersionUID = -8291751366268466258L;
    @SerializedName("cardno")
    @HbaseColumn(name = "response_cardno")
    private String cardno;
    @SerializedName("money")
    @HbaseColumn(name = "response_money")
    private Double money;
    @SerializedName("balance")
    @HbaseColumn(name = "response_balance")
    private Integer balance;
    @SerializedName("uid")
    @HbaseColumn(name = "response_uid")
    private Long uid;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;

    public V3a3rdPaymentResponse() {
    }

    public String getCardno() {
        return this.cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
