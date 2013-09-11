
package xdata.etl.entity.terminal.order.end;

import java.io.Serializable;

import xdata.etl.entity.terminal.order.end.srv.OrderPlayEndReqSrvV3;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_order_play_end_req_v3")
public class OrderPlayEndReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -1345073361139709890L;
    private String HID;
    private String UID;
    private Long localIP;
    private String sessID;
    private Long adjPlayTime;
    private Long accID;
    private Long endTick;
    private Long seekNum;
    private Long readNum;
    private Long unsuccRead;
    private Long stopPos;
    private Long sessAvgSpeed;
    private Integer srvNum;
    private HbaseAttachmentList<OrderPlayEndReqSrvV3> attachments = new HbaseAttachmentList<OrderPlayEndReqSrvV3>(this);

    public OrderPlayEndReqV3() {
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

    public String getSessID() {
        return this.sessID;
    }

    public void setSessID(String sessID) {
        this.sessID = sessID;
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

    public Long getSeekNum() {
        return this.seekNum;
    }

    public void setSeekNum(Long seekNum) {
        this.seekNum = seekNum;
    }

    public Long getReadNum() {
        return this.readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getUnsuccRead() {
        return this.unsuccRead;
    }

    public void setUnsuccRead(Long unsuccRead) {
        this.unsuccRead = unsuccRead;
    }

    public Long getStopPos() {
        return this.stopPos;
    }

    public void setStopPos(Long stopPos) {
        this.stopPos = stopPos;
    }

    public Long getSessAvgSpeed() {
        return this.sessAvgSpeed;
    }

    public void setSessAvgSpeed(Long sessAvgSpeed) {
        this.sessAvgSpeed = sessAvgSpeed;
    }

    public Integer getSrvNum() {
        return this.srvNum;
    }

    public void setSrvNum(Integer srvNum) {
        this.srvNum = srvNum;
    }

    public HbaseAttachmentList<OrderPlayEndReqSrvV3> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<OrderPlayEndReqSrvV3> attachments) {
        this.attachments = attachments;
    }

}
