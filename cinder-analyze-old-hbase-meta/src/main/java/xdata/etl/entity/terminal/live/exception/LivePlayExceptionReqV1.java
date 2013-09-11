
package xdata.etl.entity.terminal.live.exception;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_play_exception_req_v1")
public class LivePlayExceptionReqV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -7515220221531108526L;
    private Long accID;
    private Long channelID;
    private Integer srvType;
    private Long srvIP;
    private Integer srvPort;
    private Long avgSpeed;
    private Long speed;
    private Long cacheSizeCur;
    private Long codeType;
    private Long contentLen;
    private String codeContent;

    public LivePlayExceptionReqV1() {
    }

    public Long getAccID() {
        return this.accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Long getChannelID() {
        return this.channelID;
    }

    public void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public Integer getSrvType() {
        return this.srvType;
    }

    public void setSrvType(Integer srvType) {
        this.srvType = srvType;
    }

    public Long getSrvIP() {
        return this.srvIP;
    }

    public void setSrvIP(Long srvIP) {
        this.srvIP = srvIP;
    }

    public Integer getSrvPort() {
        return this.srvPort;
    }

    public void setSrvPort(Integer srvPort) {
        this.srvPort = srvPort;
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

    public Long getCacheSizeCur() {
        return this.cacheSizeCur;
    }

    public void setCacheSizeCur(Long cacheSizeCur) {
        this.cacheSizeCur = cacheSizeCur;
    }

    public Long getCodeType() {
        return this.codeType;
    }

    public void setCodeType(Long codeType) {
        this.codeType = codeType;
    }

    public Long getContentLen() {
        return this.contentLen;
    }

    public void setContentLen(Long contentLen) {
        this.contentLen = contentLen;
    }

    public String getCodeContent() {
        return this.codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

}
