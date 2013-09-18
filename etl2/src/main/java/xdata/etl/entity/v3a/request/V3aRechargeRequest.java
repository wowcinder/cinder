
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aRechargeRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = -5251762431698386167L;
    @SerializedName("cardno")
    @HbaseColumn(name = "request_cardno")
    private String cardno;
    @SerializedName("pid")
    @HbaseColumn(name = "request_pid")
    private Integer pid;
    @SerializedName("feetype")
    @HbaseColumn(name = "request_feetype")
    private Integer feetype;
    @SerializedName("spid")
    @HbaseColumn(name = "request_spid")
    private Integer spid;
    @SerializedName("uid")
    @HbaseColumn(name = "request_uid")
    private Long uid;
    @SerializedName("mobile")
    @HbaseColumn(name = "request_mobile")
    private String mobile;
    @SerializedName("orderid")
    @HbaseColumn(name = "request_orderid")
    private String orderid;
    @SerializedName("orderfrom")
    @HbaseColumn(name = "request_orderfrom")
    private Integer orderfrom;
    @SerializedName("hid")
    @HbaseColumn(name = "request_hid")
    private String hid;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;

    public V3aRechargeRequest() {
    }

    public String getCardno() {
        return this.cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFeetype() {
        return this.feetype;
    }

    public void setFeetype(Integer feetype) {
        this.feetype = feetype;
    }

    public Integer getSpid() {
        return this.spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderfrom() {
        return this.orderfrom;
    }

    public void setOrderfrom(Integer orderfrom) {
        this.orderfrom = orderfrom;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

}
