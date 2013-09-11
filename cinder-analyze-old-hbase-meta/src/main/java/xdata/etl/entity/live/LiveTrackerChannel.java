
package xdata.etl.entity.live;

import java.io.Serializable;

import xdata.etl.entity.live.srv.LiveTrackerChannelSrv;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_live_tracker_channel")
public class LiveTrackerChannel
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 2246357292703698770L;
    private Long trackerId;
    private Long channelCount;
    private HbaseAttachmentList<LiveTrackerChannelSrv> attachments = new HbaseAttachmentList<LiveTrackerChannelSrv>(this);

    public LiveTrackerChannel() {
    }

    public Long getTrackerId() {
        return this.trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public Long getChannelCount() {
        return this.channelCount;
    }

    public void setChannelCount(Long channelCount) {
        this.channelCount = channelCount;
    }

    public HbaseAttachmentList<LiveTrackerChannelSrv> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<LiveTrackerChannelSrv> attachments) {
        this.attachments = attachments;
    }

}
