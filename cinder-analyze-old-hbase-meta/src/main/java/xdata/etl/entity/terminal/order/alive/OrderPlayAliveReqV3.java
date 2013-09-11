
package xdata.etl.entity.terminal.order.alive;

import java.io.Serializable;

import xdata.etl.entity.terminal.order.alive.srv.OrderPlayAliveReqSrvV3;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_order_play_alive_req_v3")
public class OrderPlayAliveReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -3628347745234366577L;
    private String sessID;
    private Long adjPlayTime;
    private Long accID;
    private Long aliveTick;
    private Long seekNum;
    private Long readNum;
    private Long unsuccRead;
    private Long readPos;
    private Long sessAvgSpeed;
    private Long sessSpeed;
    private Integer linkNum;
    private Long cacheSizeCur;
    private HbaseAttachmentList<OrderPlayAliveReqSrvV3> attachments = new HbaseAttachmentList<OrderPlayAliveReqSrvV3>(this);

    public OrderPlayAliveReqV3() {
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

    public Long getAliveTick() {
        return this.aliveTick;
    }

    public void setAliveTick(Long aliveTick) {
        this.aliveTick = aliveTick;
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

    public Long getReadPos() {
        return this.readPos;
    }

    public void setReadPos(Long readPos) {
        this.readPos = readPos;
    }

    public Long getSessAvgSpeed() {
        return this.sessAvgSpeed;
    }

    public void setSessAvgSpeed(Long sessAvgSpeed) {
        this.sessAvgSpeed = sessAvgSpeed;
    }

    public Long getSessSpeed() {
        return this.sessSpeed;
    }

    public void setSessSpeed(Long sessSpeed) {
        this.sessSpeed = sessSpeed;
    }

    public Integer getLinkNum() {
        return this.linkNum;
    }

    public void setLinkNum(Integer linkNum) {
        this.linkNum = linkNum;
    }

    public Long getCacheSizeCur() {
        return this.cacheSizeCur;
    }

    public void setCacheSizeCur(Long cacheSizeCur) {
        this.cacheSizeCur = cacheSizeCur;
    }

    public HbaseAttachmentList<OrderPlayAliveReqSrvV3> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<OrderPlayAliveReqSrvV3> attachments) {
        this.attachments = attachments;
    }

}
