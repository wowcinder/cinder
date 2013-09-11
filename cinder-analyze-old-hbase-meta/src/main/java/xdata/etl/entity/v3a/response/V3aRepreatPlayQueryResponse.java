
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aRepreatPlayQueryResponse
    implements Serializable
{

    private final static long serialVersionUID = 8918757833764195183L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;
    @SerializedName("viewed")
    @HbaseColumn(name = "response_viewed")
    private Integer viewed;
    @SerializedName("endtime")
    @HbaseColumn(name = "response_endtime")
    private Date endtime;
    @SerializedName("order")
    @HbaseColumn(name = "response_order")
    private Integer order;
    @SerializedName("pid")
    @HbaseColumn(name = "response_pid")
    private Integer pid;
    @SerializedName("tjpid")
    @HbaseColumn(name = "response_tjpid")
    private Integer tjpid;

    public V3aRepreatPlayQueryResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getViewed() {
        return this.viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getTjpid() {
        return this.tjpid;
    }

    public void setTjpid(Integer tjpid) {
        this.tjpid = tjpid;
    }

}
