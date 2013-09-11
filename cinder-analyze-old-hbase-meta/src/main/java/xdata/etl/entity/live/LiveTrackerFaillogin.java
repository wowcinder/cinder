
package xdata.etl.entity.live;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_tracker_faillogin")
public class LiveTrackerFaillogin
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -6489388600733316247L;
    private Long trackerId;
    private String uuid;
    private String uid;
    private Long userType;
    private Long oemid;
    private Long vipid;
    private Long ip;
    private Integer port;
    private Long ip1;
    private Integer port1;
    private Long channelid;
    private Long errorCode;

    public LiveTrackerFaillogin() {
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

    public Long getVipid() {
        return this.vipid;
    }

    public void setVipid(Long vipid) {
        this.vipid = vipid;
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

    public Long getChannelid() {
        return this.channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public Long getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

}
