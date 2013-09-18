
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aOrderPriceQueryRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = 3054321413218058586L;
    @SerializedName("uid")
    @HbaseColumn(name = "request_uid")
    private Long uid;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;
    @SerializedName("hid")
    @HbaseColumn(name = "request_hid")
    private String hid;
    @SerializedName("epgid")
    @HbaseColumn(name = "request_epgid")
    private Integer epgid;
    @SerializedName("spid")
    @HbaseColumn(name = "request_spid")
    private Integer spid;

    public V3aOrderPriceQueryRequest() {
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

    public Integer getEpgid() {
        return this.epgid;
    }

    public void setEpgid(Integer epgid) {
        this.epgid = epgid;
    }

    public Integer getSpid() {
        return this.spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

}
