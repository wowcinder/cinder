
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3a3rdPaymentRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = 5706056421376936092L;
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
    @SerializedName("price")
    @HbaseColumn(name = "request_price")
    private Double price;
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

    public V3a3rdPaymentRequest() {
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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
