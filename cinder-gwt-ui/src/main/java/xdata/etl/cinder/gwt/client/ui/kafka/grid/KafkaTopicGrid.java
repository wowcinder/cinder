/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.KafkaTopicColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class KafkaTopicGrid extends CinderGrid<KafkaTopic> {

	public static class KafkaTopicGridConfigProvider extends
			GridConfigProvider<KafkaTopic> {

		/**
		 * @param store
		 */
		public KafkaTopicGridConfigProvider() {
			super(new ListStore<KafkaTopic>(
					PropertyUtils.KafkaTopicProperty.key()));
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<KafkaTopic>> callback) {
			RpcServiceUtils.KafkaRpcService.pagingKafkaTopic(loadConfig,
					callback);
		}

		@Override
		protected void initColumnConfig() {
			ColumnConfig<KafkaTopic, String> name = (ColumnConfig<KafkaTopic, String>) KafkaTopicColumnConfig
					.name();
			ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset = (ColumnConfig<KafkaTopic, KafkaTopicCharset>) KafkaTopicColumnConfig
					.charset();
			charset.setCell(new SimpleSafeHtmlRenderer<KafkaTopicCharset>() {
				@Override
				protected String _getLabel(KafkaTopicCharset c) {
					return c.getCharset();
				}
			}.getCell());
			ColumnConfig<KafkaTopic, Boolean> isEnabled = KafkaTopicColumnConfig
					.isEnabled();
			columns.add(name);
			columns.add(charset);
			columns.add(isEnabled);

		}

	}

	/**
	 * @param configProvider
	 * @param gridConfig
	 */
	public KafkaTopicGrid(GridConfig gridConfig) {
		super(new KafkaTopicGridConfigProvider(), gridConfig);
	}

}
