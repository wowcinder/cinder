
package xdata.etl.entity.terminal.p2p.alive.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_play_alive_req_srv_v3")
public class P2pPlayAliveReqSrvV3
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -1567549607685372988L;
    private Long srvIP;
    private Long connTimes;
    private Long transNum;
    private Long avgRTT;
    private Long accBytes;
    private Long accTime;
    private Long avgSpeed;
    private Long speed;
    private Long RTT;
    private Long nodeID;

    public P2pPlayAliveReqSrvV3() {
    }

    public Long getSrvIP() {
        return this.srvIP;
    }

    public void setSrvIP(Long srvIP) {
        this.srvIP = srvIP;
    }

    public Long getConnTimes() {
        return this.connTimes;
    }

    public void setConnTimes(Long connTimes) {
        this.connTimes = connTimes;
    }

    public Long getTransNum() {
        return this.transNum;
    }

    public void setTransNum(Long transNum) {
        this.transNum = transNum;
    }

    public Long getAvgRTT() {
        return this.avgRTT;
    }

    public void setAvgRTT(Long avgRTT) {
        this.avgRTT = avgRTT;
    }

    public Long getAccBytes() {
        return this.accBytes;
    }

    public void setAccBytes(Long accBytes) {
        this.accBytes = accBytes;
    }

    public Long getAccTime() {
        return this.accTime;
    }

    public void setAccTime(Long accTime) {
        this.accTime = accTime;
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

    public Long getRTT() {
        return this.RTT;
    }

    public void setRTT(Long RTT) {
        this.RTT = RTT;
    }

    public Long getNodeID() {
        return this.nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

}
