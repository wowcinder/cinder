
package xdata.etl.entity.terminal.p2p.server.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_server_upload_state_srv_v1")
public class P2pServerUploadStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 6179756236115561921L;
    private Long chl;
    private Long beginTime;
    private Long count;
    private Long speed;
    private Long reqCount;
    private Long validReqCount;

    public P2pServerUploadStateSrvV1() {
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

    public Long getValidReqCount() {
        return this.validReqCount;
    }

    public void setValidReqCount(Long validReqCount) {
        this.validReqCount = validReqCount;
    }

}
