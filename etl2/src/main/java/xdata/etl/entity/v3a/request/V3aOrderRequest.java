
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aOrderRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = -3669089527023974689L;
    @SerializedName("pid")
    @HbaseColumn(name = "request_pid")
    private Integer pid;
    @SerializedName("feetype")
    @HbaseColumn(name = "request_feetype")
    private Integer feetype;
    @SerializedName("spid")
    @HbaseColumn(name = "request_spid")
    private String spid;
    @SerializedName("starttime")
    @HbaseColumn(name = "request_starttime")
    private Date starttime;
    @SerializedName("oemid")
    @HbaseColumn(name = "request_oemid")
    private Integer oemid;

    public V3aOrderRequest() {
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

    public String getSpid() {
        return this.spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public Date getStarttime() {
        return this.starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getOemid() {
        return this.oemid;
    }

    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

}
