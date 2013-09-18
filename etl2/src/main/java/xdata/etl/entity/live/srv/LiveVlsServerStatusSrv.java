
package xdata.etl.entity.live.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_live_vls_server_status_srv")
public class LiveVlsServerStatusSrv
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 2933343487030804695L;
    private Long channelId;
    private Integer channelType;
    private Integer channelStatus;
    private Long channelCurrentClients;
    private Long channelStartTime;
    private Long channelStopTime;
    private Long channelVlsIp;
    private Integer channelVlsPort;
    private Long channelSendBytes;
    private Long channelDownloadRate;
    private Long channelSendRate;

    public LiveVlsServerStatusSrv() {
    }

    public Long getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelType() {
        return this.channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getChannelStatus() {
        return this.channelStatus;
    }

    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    public Long getChannelCurrentClients() {
        return this.channelCurrentClients;
    }

    public void setChannelCurrentClients(Long channelCurrentClients) {
        this.channelCurrentClients = channelCurrentClients;
    }

    public Long getChannelStartTime() {
        return this.channelStartTime;
    }

    public void setChannelStartTime(Long channelStartTime) {
        this.channelStartTime = channelStartTime;
    }

    public Long getChannelStopTime() {
        return this.channelStopTime;
    }

    public void setChannelStopTime(Long channelStopTime) {
        this.channelStopTime = channelStopTime;
    }

    public Long getChannelVlsIp() {
        return this.channelVlsIp;
    }

    public void setChannelVlsIp(Long channelVlsIp) {
        this.channelVlsIp = channelVlsIp;
    }

    public Integer getChannelVlsPort() {
        return this.channelVlsPort;
    }

    public void setChannelVlsPort(Integer channelVlsPort) {
        this.channelVlsPort = channelVlsPort;
    }

    public Long getChannelSendBytes() {
        return this.channelSendBytes;
    }

    public void setChannelSendBytes(Long channelSendBytes) {
        this.channelSendBytes = channelSendBytes;
    }

    public Long getChannelDownloadRate() {
        return this.channelDownloadRate;
    }

    public void setChannelDownloadRate(Long channelDownloadRate) {
        this.channelDownloadRate = channelDownloadRate;
    }

    public Long getChannelSendRate() {
        return this.channelSendRate;
    }

    public void setChannelSendRate(Long channelSendRate) {
        this.channelSendRate = channelSendRate;
    }

}
