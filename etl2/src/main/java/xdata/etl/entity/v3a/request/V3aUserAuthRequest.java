
package xdata.etl.entity.v3a.request;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;
import xdata.etl.hbase.entity.v3a.V3aRequest;

@HbaseEmbeddable
public class V3aUserAuthRequest
    extends V3aRequest
    implements Serializable
{

    private final static long serialVersionUID = 2340309450629205594L;

    public V3aUserAuthRequest() {
    }

}
