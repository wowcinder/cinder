
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aOrderCancelRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = -5500387240025240588L;
    @SerializedName("pid")
    @HbaseColumn(name = "request_pid")
    private Integer pid;
    @SerializedName("feetype")
    @HbaseColumn(name = "request_feetype")
    private Integer feetype;
    @SerializedName("spid")
    @HbaseColumn(name = "request_spid")
    private Integer spid;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;

    public V3aOrderCancelRequest() {
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

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

}
