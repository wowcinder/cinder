
package xdata.etl.entity.live;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_tracker_failget")
public class LiveTrackerFailget
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 7077631416696719602L;
    private Long trackerId;
    private String uuid;
    private String uid;
    private Long userType;
    private Long ip;
    private Integer port;
    private Long channelid;
    private Long errCode;
    private Long authLen;
    private String auth;

    public LiveTrackerFailget() {
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

    public Long getChannelid() {
        return this.channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public Long getErrCode() {
        return this.errCode;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    public Long getAuthLen() {
        return this.authLen;
    }

    public void setAuthLen(Long authLen) {
        this.authLen = authLen;
    }

    public String getAuth() {
        return this.auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
