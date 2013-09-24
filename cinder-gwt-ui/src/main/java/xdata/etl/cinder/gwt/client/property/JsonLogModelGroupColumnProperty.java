
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;

public interface JsonLogModelGroupColumnProperty
    extends PropertyAccess<JsonLogModelGroupColumn>
{


    public ValueProvider<JsonLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<JsonLogModelGroupColumn, List<JsonLogModelColumn>> columns();

    public ValueProvider<JsonLogModelGroupColumn, JsonLogModelGroupColumn> groupColumn();

    public ValueProvider<JsonLogModelGroupColumn, String> path();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModelGroupColumn> key();

    public ValueProvider<JsonLogModelGroupColumn, Integer> id();

    public ValueProvider<JsonLogModelGroupColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModelGroupColumn, Date> createTime();

    public ValueProvider<JsonLogModelGroupColumn, String> createTimePropertyName();

}
