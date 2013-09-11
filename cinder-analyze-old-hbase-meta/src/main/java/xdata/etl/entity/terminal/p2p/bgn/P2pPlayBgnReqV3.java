
package xdata.etl.entity.terminal.p2p.bgn;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.bgn.srv.P2pPlayBgnReqSrvV3;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_play_bgn_req_v3")
public class P2pPlayBgnReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -8531034404908022533L;
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
    private Integer srvNum;
    private Long cacheSize;
    private Long packSize;
    private Long bufSize;
    private HbaseAttachmentList<P2pPlayBgnReqSrvV3> attachments = new HbaseAttachmentList<P2pPlayBgnReqSrvV3>(this);

    public P2pPlayBgnReqV3() {
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

    public Integer getSrvNum() {
        return this.srvNum;
    }

    public void setSrvNum(Integer srvNum) {
        this.srvNum = srvNum;
    }

    public Long getCacheSize() {
        return this.cacheSize;
    }

    public void setCacheSize(Long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Long getPackSize() {
        return this.packSize;
    }

    public void setPackSize(Long packSize) {
        this.packSize = packSize;
    }

    public Long getBufSize() {
        return this.bufSize;
    }

    public void setBufSize(Long bufSize) {
        this.bufSize = bufSize;
    }

    public HbaseAttachmentList<P2pPlayBgnReqSrvV3> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pPlayBgnReqSrvV3> attachments) {
        this.attachments = attachments;
    }

}
