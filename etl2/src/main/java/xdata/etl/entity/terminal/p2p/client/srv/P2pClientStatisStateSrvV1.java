
package xdata.etl.entity.terminal.p2p.client.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_client_statis_state_srv_v1")
public class P2pClientStatisStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 3062880599323984886L;
    private Long chl;
    private Long chTime;
    private Long chDownload;
    private Long chUpload;
    private Long chDownspeed;
    private Long chUploadspeed;
    private Long chFailcount;
    private Long chDownloadserver;
    private Long chDownloadclient;
    private Long chCurPeerCounter;

    public P2pClientStatisStateSrvV1() {
    }

    public Long getChl() {
        return this.chl;
    }

    public void setChl(Long chl) {
        this.chl = chl;
    }

    public Long getChTime() {
        return this.chTime;
    }

    public void setChTime(Long chTime) {
        this.chTime = chTime;
    }

    public Long getChDownload() {
        return this.chDownload;
    }

    public void setChDownload(Long chDownload) {
        this.chDownload = chDownload;
    }

    public Long getChUpload() {
        return this.chUpload;
    }

    public void setChUpload(Long chUpload) {
        this.chUpload = chUpload;
    }

    public Long getChDownspeed() {
        return this.chDownspeed;
    }

    public void setChDownspeed(Long chDownspeed) {
        this.chDownspeed = chDownspeed;
    }

    public Long getChUploadspeed() {
        return this.chUploadspeed;
    }

    public void setChUploadspeed(Long chUploadspeed) {
        this.chUploadspeed = chUploadspeed;
    }

    public Long getChFailcount() {
        return this.chFailcount;
    }

    public void setChFailcount(Long chFailcount) {
        this.chFailcount = chFailcount;
    }

    public Long getChDownloadserver() {
        return this.chDownloadserver;
    }

    public void setChDownloadserver(Long chDownloadserver) {
        this.chDownloadserver = chDownloadserver;
    }

    public Long getChDownloadclient() {
        return this.chDownloadclient;
    }

    public void setChDownloadclient(Long chDownloadclient) {
        this.chDownloadclient = chDownloadclient;
    }

    public Long getChCurPeerCounter() {
        return this.chCurPeerCounter;
    }

    public void setChCurPeerCounter(Long chCurPeerCounter) {
        this.chCurPeerCounter = chCurPeerCounter;
    }

}
