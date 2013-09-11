
package xdata.etl.entity.terminal.p2p.alive;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.alive.srv.P2pPlayAliveReqSrvV3;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_play_alive_req_v3")
public class P2pPlayAliveReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 8953353153850984129L;
    private Long channelID;
    private Long adjPlayTime;
    private Long accID;
    private Long aliveTick;
    private Long readNum;
    private Long unsuccRead;
    private Long curFrm;
    private Long avgSpeed;
    private Long speed;
    private Integer linkNum;
    private Long cacheSizeCur;
    private HbaseAttachmentList<P2pPlayAliveReqSrvV3> attachments = new HbaseAttachmentList<P2pPlayAliveReqSrvV3>(this);

    public P2pPlayAliveReqV3() {
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

    public Long getCurFrm() {
        return this.curFrm;
    }

    public void setCurFrm(Long curFrm) {
        this.curFrm = curFrm;
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

    public HbaseAttachmentList<P2pPlayAliveReqSrvV3> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pPlayAliveReqSrvV3> attachments) {
        this.attachments = attachments;
    }

}
