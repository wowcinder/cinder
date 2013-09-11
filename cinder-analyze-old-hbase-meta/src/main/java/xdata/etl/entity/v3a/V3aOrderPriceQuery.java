
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aOrderPriceQueryRequest;
import xdata.etl.entity.v3a.response.V3aOrderPriceQueryResponse;
import xdata.etl.entity.v3a.srv.V3aOrderPriceQueryFilmPrice;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_order_price_query")
public class V3aOrderPriceQuery
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 963965536318473731L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aOrderPriceQueryRequest v3aOrderPriceQueryRequest = new V3aOrderPriceQueryRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aOrderPriceQueryResponse v3aOrderPriceQueryResponse = new V3aOrderPriceQueryResponse();
    private HbaseAttachmentList<V3aOrderPriceQueryFilmPrice> v3aOrderPriceQueryFilmPrice = new HbaseAttachmentList<V3aOrderPriceQueryFilmPrice>(this);

    public V3aOrderPriceQuery() {
    }

    public V3aOrderPriceQueryRequest getV3aOrderPriceQueryRequest() {
        return this.v3aOrderPriceQueryRequest;
    }

    public void setV3aOrderPriceQueryRequest(V3aOrderPriceQueryRequest v3aOrderPriceQueryRequest) {
        this.v3aOrderPriceQueryRequest = v3aOrderPriceQueryRequest;
    }

    public V3aOrderPriceQueryResponse getV3aOrderPriceQueryResponse() {
        return this.v3aOrderPriceQueryResponse;
    }

    public void setV3aOrderPriceQueryResponse(V3aOrderPriceQueryResponse v3aOrderPriceQueryResponse) {
        this.v3aOrderPriceQueryResponse = v3aOrderPriceQueryResponse;
    }

    public HbaseAttachmentList<V3aOrderPriceQueryFilmPrice> getV3aOrderPriceQueryFilmPrice() {
        return this.v3aOrderPriceQueryFilmPrice;
    }

    public void setV3aOrderPriceQueryFilmPrice(HbaseAttachmentList<V3aOrderPriceQueryFilmPrice> v3aOrderPriceQueryFilmPrice) {
        this.v3aOrderPriceQueryFilmPrice = v3aOrderPriceQueryFilmPrice;
    }

}
