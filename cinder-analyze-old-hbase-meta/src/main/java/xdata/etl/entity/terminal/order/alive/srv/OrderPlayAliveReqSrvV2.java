
package xdata.etl.entity.terminal.order.alive.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_order_play_alive_req_srv_v2")
public class OrderPlayAliveReqSrvV2
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 1152981524230195125L;
    private Long srvIP;
    private Long connTimes;
    private Long transNum;
    private Long avgRTT;
    private Long accBytes;
    private Long accTime;
    private Long avgSpeed;

    public OrderPlayAliveReqSrvV2() {
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

}
