
package xdata.etl.entity.terminal.p2p.server.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_server_download_state_srv_v1")
public class P2pServerDownloadStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 2860434945738559317L;
    private Long chl;
    private Long beginTime;
    private Long count;
    private Long speed;
    private Long reqCount;
    private Long failCount;
    private Long dropCount;
    private Long dropData;
    private Long dropSourceIp;
    private Long dropSourceUid;

    public P2pServerDownloadStateSrvV1() {
    }

    public Long getChl() {
        return this.chl;
    }

    public void setChl(Long chl) {
        this.chl = chl;
    }

    public Long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getSpeed() {
        return this.speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getReqCount() {
        return this.reqCount;
    }

    public void setReqCount(Long reqCount) {
        this.reqCount = reqCount;
    }

    public Long getFailCount() {
        return this.failCount;
    }

    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }

    public Long getDropCount() {
        return this.dropCount;
    }

    public void setDropCount(Long dropCount) {
        this.dropCount = dropCount;
    }

    public Long getDropData() {
        return this.dropData;
    }

    public void setDropData(Long dropData) {
        this.dropData = dropData;
    }

    public Long getDropSourceIp() {
        return this.dropSourceIp;
    }

    public void setDropSourceIp(Long dropSourceIp) {
        this.dropSourceIp = dropSourceIp;
    }

    public Long getDropSourceUid() {
        return this.dropSourceUid;
    }

    public void setDropSourceUid(Long dropSourceUid) {
        this.dropSourceUid = dropSourceUid;
    }

}
