
package xdata.etl.entity.terminal.live.end;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_play_end_req_v1")
public class LivePlayEndReqV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 6155451639117726137L;
    private Long channelID;
    private String HID;
    private String UID;
    private Long localIP;
    private Long adjPlayTime;
    private Long accID;
    private Long endTick;
    private Long avgSpeed;

    public LivePlayEndReqV1() {
    }

    public Long getChannelID() {
        return this.channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public String getHID() {
        return this.HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }

    public String getUID() {
        return this.UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Long getLocalIP() {
        return this.localIP;
    }

    public void setLocalIP(Long localIP) {
        this.localIP = localIP;
    }

    public Long getAdjPlayTime() {
        return this.adjPlayTime;
    }

    public void setAdjPlayTime(Long adjPlayTime) {
        this.adjPlayTime = adjPlayTime;
    }

    public Long getAccID() {
        return this.accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Long getEndTick() {
        return this.endTick;
    }

    public void setEndTick(Long endTick) {
        this.endTick = endTick;
    }

    public Long getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(Long avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

}
