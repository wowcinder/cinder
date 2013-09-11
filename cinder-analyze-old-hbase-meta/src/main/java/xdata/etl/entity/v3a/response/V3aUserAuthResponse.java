
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aUserAuthResponse
    implements Serializable
{

    private final static long serialVersionUID = 2271080419640165262L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;
    @SerializedName("uid")
    @HbaseColumn(name = "response_uid")
    private Long uid;
    @SerializedName("oemid")
    @HbaseColumn(name = "response_oemid")
    private Integer oemid;
    @SerializedName("hid")
    @HbaseColumn(name = "response_hid")
    private String hid;
    @SerializedName("epgportal")
    @HbaseColumn(name = "response_epgportal")
    private String epgportal;
    @SerializedName("epgportal2")
    @HbaseColumn(name = "response_epgportal2")
    private String epgportal2;
    @SerializedName("balance")
    @HbaseColumn(name = "response_balance")
    private Integer balance;
    @SerializedName("ispid")
    @HbaseColumn(name = "response_ispid")
    private Integer ispid;

    public V3aUserAuthResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getEpgportal() {
        return this.epgportal;
    }

    public void setEpgportal(String epgportal) {
        this.epgportal = epgportal;
    }

    public String getEpgportal2() {
        return this.epgportal2;
    }

    public void setEpgportal2(String epgportal2) {
        this.epgportal2 = epgportal2;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getIspid() {
        return this.ispid;
    }

    public void setIspid(Integer ispid) {
        this.ispid = ispid;
    }

}
