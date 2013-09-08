
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.hbasemeta.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.entity.base.HbaseTableVersion;

public interface HbaseTableVersionProperty
    extends PropertyAccess<HbaseTableVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTableVersion> key();

    public ValueProvider<HbaseTableVersion, Integer> id();

    public ValueProvider<HbaseTableVersion, HbaseTable> table();

    public ValueProvider<HbaseTableVersion, String> desc();

    public ValueProvider<HbaseTableVersion, String> version();

    public ValueProvider<HbaseTableVersion, List<HbaseTableColumn>> columns();

    public ValueProvider<HbaseTableVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<HbaseTableVersion, Date> createTime();

    public ValueProvider<HbaseTableVersion, String> createTimePropertyName();

}
