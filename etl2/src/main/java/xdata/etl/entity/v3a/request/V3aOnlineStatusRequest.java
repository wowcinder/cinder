
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aOnlineStatusRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = -4966805386901566857L;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;
    @SerializedName("uid")
    @HbaseColumn(name = "request_uid")
    private Integer uid;
    @SerializedName("hid")
    @HbaseColumn(name = "request_hid")
    private String hid;
    @SerializedName("status")
    @HbaseColumn(name = "request_status")
    private Integer status;

    public V3aOnlineStatusRequest() {
    }

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
