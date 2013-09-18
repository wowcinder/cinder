
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aOrderPriceQueryResponse
    implements Serializable
{

    private final static long serialVersionUID = 3241265496510447991L;
    @SerializedName("Count")
    @HbaseColumn(name = "response_Count")
    private Integer count;

    public V3aOrderPriceQueryResponse() {
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
