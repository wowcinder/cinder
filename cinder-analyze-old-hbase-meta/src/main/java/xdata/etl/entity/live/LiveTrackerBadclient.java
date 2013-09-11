
package xdata.etl.entity.live;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_tracker_badclient")
public class LiveTrackerBadclient
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -8549151739992912007L;
    private Long trackerId;
    private String uuid;
    private String uid;
    private Long channelid;
    private Long ip;
    private Integer port;
    private Long ip1;
    private Integer port1;
    private Long provinceid;
    private Long cityid;
    private Long netid;
    private Long userType;
    private Long oemid;
    private Long ver;
    private Long getuserCount;

    public LiveTrackerBadclient() {
    }

    public Long getTrackerId() {
        return this.trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getChannelid() {
        return this.channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public Long getIp() {
        return this.ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getIp1() {
        return this.ip1;
    }

    public void setIp1(Long ip1) {
        this.ip1 = ip1;
    }

    public Integer getPort1() {
        return this.port1;
    }

    public void setPort1(Integer port1) {
        this.port1 = port1;
    }

    public Long getProvinceid() {
        return this.provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public Long getCityid() {
        return this.cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public Long getNetid() {
        return this.netid;
    }

    public void setNetid(Long netid) {
        this.netid = netid;
    }

    public Long getUserType() {
        return this.userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Long getOemid() {
        return this.oemid;
    }

    public void setOemid(Long oemid) {
        this.oemid = oemid;
    }

    public Long getVer() {
        return this.ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

    public Long getGetuserCount() {
        return this.getuserCount;
    }

    public void setGetuserCount(Long getuserCount) {
        this.getuserCount = getuserCount;
    }

}
