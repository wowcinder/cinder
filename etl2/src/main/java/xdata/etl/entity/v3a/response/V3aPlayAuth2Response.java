
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aPlayAuth2Response
    implements Serializable
{

    private final static long serialVersionUID = -3910619042827778596L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;
    @SerializedName("play_url")
    @HbaseColumn(name = "response_play_url")
    private String playUrl;
    @SerializedName("delaydeduct")
    @HbaseColumn(name = "response_delaydeduct")
    private Integer delaydeduct;
    @SerializedName("delaytime")
    @HbaseColumn(name = "response_delaytime")
    private Integer delaytime;
    @SerializedName("pid")
    @HbaseColumn(name = "response_pid")
    private String pid;
    @SerializedName("time")
    @HbaseColumn(name = "response_time")
    private Long time;
    @SerializedName("stime")
    @HbaseColumn(name = "response_stime")
    private Long stime;
    @SerializedName("etime")
    @HbaseColumn(name = "response_etime")
    private Long etime;

    public V3aPlayAuth2Response() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Integer getDelaydeduct() {
        return this.delaydeduct;
    }

    public void setDelaydeduct(Integer delaydeduct) {
        this.delaydeduct = delaydeduct;
    }

    public Integer getDelaytime() {
        return this.delaytime;
    }

    public void setDelaytime(Integer delaytime) {
        this.delaytime = delaytime;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getStime() {
        return this.stime;
    }

    public void setStime(Long stime) {
        this.stime = stime;
    }

    public Long getEtime() {
        return this.etime;
    }

    public void setEtime(Long etime) {
        this.etime = etime;
    }

}
