
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aOrderRequest;
import xdata.etl.entity.v3a.response.V3aOrderResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_order")
public class V3aOrder
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 3400642206718184050L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aOrderRequest v3aOrderRequest = new V3aOrderRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aOrderResponse v3aOrderResponse = new V3aOrderResponse();

    public V3aOrder() {
    }

    public V3aOrderRequest getV3aOrderRequest() {
        return this.v3aOrderRequest;
    }

    public void setV3aOrderRequest(V3aOrderRequest v3aOrderRequest) {
        this.v3aOrderRequest = v3aOrderRequest;
    }

    public V3aOrderResponse getV3aOrderResponse() {
        return this.v3aOrderResponse;
    }

    public void setV3aOrderResponse(V3aOrderResponse v3aOrderResponse) {
        this.v3aOrderResponse = v3aOrderResponse;
    }

}
