
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aRepreatPlayQueryRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = -102628876596655364L;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;
    @SerializedName("hid")
    @HbaseColumn(name = "request_hid")
    private String hid;
    @SerializedName("uid")
    @HbaseColumn(name = "request_uid")
    private Long uid;
    @SerializedName("mid")
    @HbaseColumn(name = "request_mid")
    private Integer mid;
    @SerializedName("sid")
    @HbaseColumn(name = "request_sid")
    private Integer sid;
    @SerializedName("fid")
    @HbaseColumn(name = "request_fid")
    private String fid;
    @SerializedName("tj")
    @HbaseColumn(name = "request_tj")
    private Integer tj;

    public V3aRepreatPlayQueryRequest() {
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

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return this.mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Integer getTj() {
        return this.tj;
    }

    public void setTj(Integer tj) {
        this.tj = tj;
    }

}
