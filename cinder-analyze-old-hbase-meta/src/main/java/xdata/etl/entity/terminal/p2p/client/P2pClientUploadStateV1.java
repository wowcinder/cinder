
package xdata.etl.entity.terminal.p2p.client;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_client_upload_state_v1")
public class P2pClientUploadStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -4681394388921727794L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long chl;
    private Long beginTime;
    private Long count;
    private Long speed;
    private Long reqCount;
    private Long validReqCount;

    public P2pClientUploadStateV1() {
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

    public Long getHid() {
        return this.hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getOemid() {
        return this.oemid;
    }

    public void setOemid(Long oemid) {
        this.oemid = oemid;
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
