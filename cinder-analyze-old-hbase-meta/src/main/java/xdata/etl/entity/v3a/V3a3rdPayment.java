
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3a3rdPaymentRequest;
import xdata.etl.entity.v3a.response.V3a3rdPaymentResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_3rd_payment")
public class V3a3rdPayment
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 9080698187703730219L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3a3rdPaymentRequest v3a3rdPaymentRequest = new V3a3rdPaymentRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3a3rdPaymentResponse v3a3rdPaymentResponse = new V3a3rdPaymentResponse();

    public V3a3rdPayment() {
    }

    public V3a3rdPaymentRequest getV3a3rdPaymentRequest() {
        return this.v3a3rdPaymentRequest;
    }

    public void setV3a3rdPaymentRequest(V3a3rdPaymentRequest v3a3rdPaymentRequest) {
        this.v3a3rdPaymentRequest = v3a3rdPaymentRequest;
    }

    public V3a3rdPaymentResponse getV3a3rdPaymentResponse() {
        return this.v3a3rdPaymentResponse;
    }

    public void setV3a3rdPaymentResponse(V3a3rdPaymentResponse v3a3rdPaymentResponse) {
        this.v3a3rdPaymentResponse = v3a3rdPaymentResponse;
    }

}
