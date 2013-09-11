
package xdata.etl.entity.terminal.live.alive;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_play_alive_req_v1")
public class LivePlayAliveReqV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -2330461554309249133L;
    private Long channelID;
    private Long adjPlayTime;
    private Long accID;
    private Long aliveTick;
    private Long avgSpeed;
    private Long speed;
    private Long cacheSizeCur;

    public LivePlayAliveReqV1() {
    }

    public Long getChannelID() {
        return this.channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
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

    public Long getAliveTick() {
        return this.aliveTick;
    }

    public void setAliveTick(Long aliveTick) {
        this.aliveTick = aliveTick;
    }

    public Long getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(Long avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Long getSpeed() {
        return this.speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getCacheSizeCur() {
        return this.cacheSizeCur;
    }

    public void setCacheSizeCur(Long cacheSizeCur) {
        this.cacheSizeCur = cacheSizeCur;
    }

}
