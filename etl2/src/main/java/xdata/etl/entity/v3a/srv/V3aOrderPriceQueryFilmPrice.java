
package xdata.etl.entity.v3a.srv;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_v3a_order_price_query_film_price")
public class V3aOrderPriceQueryFilmPrice
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 3259724240668607447L;
    @SerializedName("Mid")
    private Integer mid;
    @SerializedName("Mtype")
    private String mtype;
    @SerializedName("Price")
    private Integer price;

    public V3aOrderPriceQueryFilmPrice() {
    }

    public Integer getMid() {
        return this.mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMtype() {
        return this.mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
