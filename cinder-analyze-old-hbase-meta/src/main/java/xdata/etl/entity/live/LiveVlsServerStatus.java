
package xdata.etl.entity.live;

import java.io.Serializable;

import xdata.etl.entity.live.srv.LiveVlsServerStatusSrv;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_vls_server_status")
public class LiveVlsServerStatus
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -3863990981241976628L;
    private Long vlsId;
    private Integer vlsType;
    private Integer vlsStatus;
    private String vlsDescription;
    private Long vlsIp;
    private Integer vlsHttpPort;
    private Integer vlsLivePort;
    private Long vlsStartTime;
    private Long vlsAllClients;
    private Long vlsAllStreamBytes;
    private Long vlsDownloadRate;
    private Long vlsSendRate;
    private Long vlsEffectiveChannels;
    private HbaseAttachmentList<LiveVlsServerStatusSrv> attachments = new HbaseAttachmentList<LiveVlsServerStatusSrv>(this);

    public LiveVlsServerStatus() {
    }

    public Long getVlsId() {
        return this.vlsId;
    }

    public void setVlsId(Long vlsId) {
        this.vlsId = vlsId;
    }

    public Integer getVlsType() {
        return this.vlsType;
    }

    public void setVlsType(Integer vlsType) {
        this.vlsType = vlsType;
    }

    public Integer getVlsStatus() {
        return this.vlsStatus;
    }

    public void setVlsStatus(Integer vlsStatus) {
        this.vlsStatus = vlsStatus;
    }

    public String getVlsDescription() {
        return this.vlsDescription;
    }

    public void setVlsDescription(String vlsDescription) {
        this.vlsDescription = vlsDescription;
    }

    public Long getVlsIp() {
        return this.vlsIp;
    }

    public void setVlsIp(Long vlsIp) {
        this.vlsIp = vlsIp;
    }

    public Integer getVlsHttpPort() {
        return this.vlsHttpPort;
    }

    public void setVlsHttpPort(Integer vlsHttpPort) {
        this.vlsHttpPort = vlsHttpPort;
    }

    public Integer getVlsLivePort() {
        return this.vlsLivePort;
    }

    public void setVlsLivePort(Integer vlsLivePort) {
        this.vlsLivePort = vlsLivePort;
    }

    public Long getVlsStartTime() {
        return this.vlsStartTime;
    }

    public void setVlsStartTime(Long vlsStartTime) {
        this.vlsStartTime = vlsStartTime;
    }

    public Long getVlsAllClients() {
        return this.vlsAllClients;
    }

    public void setVlsAllClients(Long vlsAllClients) {
        this.vlsAllClients = vlsAllClients;
    }

    public Long getVlsAllStreamBytes() {
        return this.vlsAllStreamBytes;
    }

    public void setVlsAllStreamBytes(Long vlsAllStreamBytes) {
        this.vlsAllStreamBytes = vlsAllStreamBytes;
    }

    public Long getVlsDownloadRate() {
        return this.vlsDownloadRate;
    }

    public void setVlsDownloadRate(Long vlsDownloadRate) {
        this.vlsDownloadRate = vlsDownloadRate;
    }

    public Long getVlsSendRate() {
        return this.vlsSendRate;
    }

    public void setVlsSendRate(Long vlsSendRate) {
        this.vlsSendRate = vlsSendRate;
    }

    public Long getVlsEffectiveChannels() {
        return this.vlsEffectiveChannels;
    }

    public void setVlsEffectiveChannels(Long vlsEffectiveChannels) {
        this.vlsEffectiveChannels = vlsEffectiveChannels;
    }

    public HbaseAttachmentList<LiveVlsServerStatusSrv> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<LiveVlsServerStatusSrv> attachments) {
        this.attachments = attachments;
    }

}
