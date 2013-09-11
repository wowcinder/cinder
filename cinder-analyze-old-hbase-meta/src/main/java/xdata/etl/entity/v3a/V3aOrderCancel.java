
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aOrderCancelRequest;
import xdata.etl.entity.v3a.response.V3aOrderCancelResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_order_cancel")
public class V3aOrderCancel
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = -9212985555504771940L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aOrderCancelRequest v3aOrderCancelRequest = new V3aOrderCancelRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aOrderCancelResponse v3aOrderCancelResponse = new V3aOrderCancelResponse();

    public V3aOrderCancel() {
    }

    public V3aOrderCancelRequest getV3aOrderCancelRequest() {
        return this.v3aOrderCancelRequest;
    }

    public void setV3aOrderCancelRequest(V3aOrderCancelRequest v3aOrderCancelRequest) {
        this.v3aOrderCancelRequest = v3aOrderCancelRequest;
    }

    public V3aOrderCancelResponse getV3aOrderCancelResponse() {
        return this.v3aOrderCancelResponse;
    }

    public void setV3aOrderCancelResponse(V3aOrderCancelResponse v3aOrderCancelResponse) {
        this.v3aOrderCancelResponse = v3aOrderCancelResponse;
    }

}
