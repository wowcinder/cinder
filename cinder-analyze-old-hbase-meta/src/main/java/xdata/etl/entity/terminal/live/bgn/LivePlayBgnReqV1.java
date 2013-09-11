
package xdata.etl.entity.terminal.live.bgn;

import java.io.Serializable;

import xdata.etl.entity.terminal.live.bgn.srv.LivePlayBgnReqSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_play_bgn_req_v1")
public class LivePlayBgnReqV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -4642766936242175507L;
    private Long accID;
    private Long OEMID;
    private Long vendorID;
    private Long curVer;
    private Long buildTime;
    private String HID;
    private String UID;
    private Long localIP;
    private Long channelID;
    private Long playTick;
    private Integer uRLLen;
    private String URL;
    private Long getVLSTick;
    private Integer srvNum;
    private HbaseAttachmentList<LivePlayBgnReqSrvV1> attachments = new HbaseAttachmentList<LivePlayBgnReqSrvV1>(this);

    public LivePlayBgnReqV1() {
    }

    public Long getAccID() {
        return this.accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Long getOEMID() {
        return this.OEMID;
    }

    public void setOEMID(Long OEMID) {
        this.OEMID = OEMID;
    }

    public Long getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public Long getCurVer() {
        return this.curVer;
    }

    public void setCurVer(Long curVer) {
        this.curVer = curVer;
    }

    public Long getBuildTime() {
        return this.buildTime;
    }

    public void setBuildTime(Long buildTime) {
        this.buildTime = buildTime;
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

    public Long getChannelID() {
        return this.channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public Long getPlayTick() {
        return this.playTick;
    }

    public void setPlayTick(Long playTick) {
        this.playTick = playTick;
    }

    public Integer getURLLen() {
        return this.uRLLen;
    }

    public void setURLLen(Integer uRLLen) {
        this.uRLLen = uRLLen;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Long getGetVLSTick() {
        return this.getVLSTick;
    }

    public void setGetVLSTick(Long getVLSTick) {
        this.getVLSTick = getVLSTick;
    }

    public Integer getSrvNum() {
        return this.srvNum;
    }

    public void setSrvNum(Integer srvNum) {
        this.srvNum = srvNum;
    }

    public HbaseAttachmentList<LivePlayBgnReqSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<LivePlayBgnReqSrvV1> attachments) {
        this.attachments = attachments;
    }

}
