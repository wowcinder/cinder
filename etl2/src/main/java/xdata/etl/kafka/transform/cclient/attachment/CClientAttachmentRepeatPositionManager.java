package xdata.etl.kafka.transform.cclient.attachment;

import java.util.HashMap;
import java.util.Map;

import xdata.etl.entity.assist.AssistUpdateStatistics;
import xdata.etl.entity.terminal.order.alive.OrderPlayAliveReqV3;
import xdata.etl.entity.terminal.order.bgn.OrderPlayBgnReqV3;
import xdata.etl.entity.terminal.p2p.alive.P2pPlayAliveReqV3;
import xdata.etl.entity.terminal.p2p.bgn.P2pPlayBgnReqV3;
import xdata.etl.hbase.entity.HbaseEntity;

public class CClientAttachmentRepeatPositionManager {
	private static Map<Class<? extends HbaseEntity>, Integer> notRepeatAtEndMap = new HashMap<Class<? extends HbaseEntity>, Integer>();

	static {
		notRepeatAtEndMap.put(P2pPlayAliveReqV3.class, 10);
		notRepeatAtEndMap.put(OrderPlayAliveReqV3.class, 11);
		notRepeatAtEndMap.put(OrderPlayBgnReqV3.class, 18);
		notRepeatAtEndMap.put(P2pPlayBgnReqV3.class, 12);
		notRepeatAtEndMap.put(AssistUpdateStatistics.class, 2);
	}

	public static boolean isEndRepeat(Class<?> clazz) {
		if (notRepeatAtEndMap.containsKey(clazz)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param clazz
	 * @return if clazz is repeat in end return -1 else repeat index
	 */
	public static int getRepeatIndex(Class<?> clazz) {
		if (notRepeatAtEndMap.containsKey(clazz)) {
			notRepeatAtEndMap.get(clazz);
		}
		return -1;
	}

}
