
package xdata.etl.entity.live.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_live_tracker_channel_srv")
public class LiveTrackerChannelSrv
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -5282247637773759626L;
    private Long channelId;
    private Long channelUserNum;

    public LiveTrackerChannelSrv() {
    }

    public Long getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getChannelUserNum() {
        return this.channelUserNum;
    }

    public void setChannelUserNum(Long channelUserNum) {
        this.channelUserNum = channelUserNum;
    }

}
