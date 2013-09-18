
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aPlayAuth2Request
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = 6292505211432357536L;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;
    @SerializedName("uid")
    @HbaseColumn(name = "request_uid")
    private Long uid;
    @SerializedName("hid")
    @HbaseColumn(name = "request_hid")
    private String hid;
    @SerializedName("mid")
    @HbaseColumn(name = "request_mid")
    private Integer mid;
    @SerializedName("sid")
    @HbaseColumn(name = "request_sid")
    private Integer sid;
    @SerializedName("fid")
    @HbaseColumn(name = "request_fid")
    private String fid;
    @SerializedName("pid")
    @HbaseColumn(name = "request_pid")
    private Integer pid;
    @SerializedName("playtype")
    @HbaseColumn(name = "request_playtype")
    private Integer playtype;

    public V3aPlayAuth2Request() {
    }

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
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

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPlaytype() {
        return this.playtype;
    }

    public void setPlaytype(Integer playtype) {
        this.playtype = playtype;
    }

}
