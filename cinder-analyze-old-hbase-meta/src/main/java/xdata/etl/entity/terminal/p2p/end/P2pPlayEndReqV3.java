
package xdata.etl.entity.terminal.p2p.end;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.end.srv.P2pPlayEndReqSrvV3;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_play_end_req_v3")
public class P2pPlayEndReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 2936659053676478816L;
    private Long channelID;
    private String HID;
    private String UID;
    private Long localIP;
    private Long adjPlayTime;
    private Long accID;
    private Long endTick;
    private Long readNum;
    private Long avgSpeed;
    private Integer srvNum;
    private HbaseAttachmentList<P2pPlayEndReqSrvV3> attachments = new HbaseAttachmentList<P2pPlayEndReqSrvV3>(this);

    public P2pPlayEndReqV3() {
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

    public Long getReadNum() {
        return this.readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(Long avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getSrvNum() {
        return this.srvNum;
    }

    public void setSrvNum(Integer srvNum) {
        this.srvNum = srvNum;
    }

    public HbaseAttachmentList<P2pPlayEndReqSrvV3> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pPlayEndReqSrvV3> attachments) {
        this.attachments = attachments;
    }

}
