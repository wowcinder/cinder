
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aOnlineStatusRequest;
import xdata.etl.entity.v3a.response.V3aOnlineStatusResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_online_status")
public class V3aOnlineStatus
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = -3620095445407674744L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aOnlineStatusRequest v3aOnlineStatusRequest = new V3aOnlineStatusRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aOnlineStatusResponse v3aOnlineStatusResponse = new V3aOnlineStatusResponse();

    public V3aOnlineStatus() {
    }

    public V3aOnlineStatusRequest getV3aOnlineStatusRequest() {
        return this.v3aOnlineStatusRequest;
    }

    public void setV3aOnlineStatusRequest(V3aOnlineStatusRequest v3aOnlineStatusRequest) {
        this.v3aOnlineStatusRequest = v3aOnlineStatusRequest;
    }

    public V3aOnlineStatusResponse getV3aOnlineStatusResponse() {
        return this.v3aOnlineStatusResponse;
    }

    public void setV3aOnlineStatusResponse(V3aOnlineStatusResponse v3aOnlineStatusResponse) {
        this.v3aOnlineStatusResponse = v3aOnlineStatusResponse;
    }

}
