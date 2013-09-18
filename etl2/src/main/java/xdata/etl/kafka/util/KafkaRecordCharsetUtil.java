package xdata.etl.kafka.util;

import java.util.ArrayList;
import java.util.List;

public class KafkaRecordCharsetUtil {

	public static String getCharset(String topic) {
		if (assistTopics.contains(topic)) {
			return "GBK";
		} else if (terminalTopics.contains(topic)) {
			return "ISO-8859-1";
		}
		return "UTF-8";
	}

	private static final List<String> assistTopics = new ArrayList<String>();
	private static final List<String> terminalTopics = new ArrayList<String>();
	static {
		terminalTopics.add("t_playbgn_v2");
		terminalTopics.add("t_playend_v2");
		terminalTopics.add("t_playalive_v2");
		terminalTopics.add("t_playbgn_v3");
		terminalTopics.add("t_playend_v3");
		terminalTopics.add("t_playalive_v3");
		terminalTopics.add("t_playexce_v3");
		terminalTopics.add("t_live_playbgn_v1");
		terminalTopics.add("t_live_playend_v1");
		terminalTopics.add("t_live_playalive_v1");
		terminalTopics.add("t_live_playexce_v1");
		terminalTopics.add("t_live_p2p_playbgn_v1");
		terminalTopics.add("t_live_p2p_playend_v1");
		terminalTopics.add("t_live_p2p_playalive_v1");
		terminalTopics.add("t_live_p2p_playexce_v1");
		terminalTopics.add("t_live_p2p_vlan_v1");
		terminalTopics.add("t_live_p2p_channel_v1");
		terminalTopics.add("t_live_p2p_s_down_v1");
		terminalTopics.add("t_live_p2p_s_up_v1");
		terminalTopics.add("t_live_p2p_s_peer_v1");
		terminalTopics.add("t_live_p2p_s_stat_v1");
		terminalTopics.add("t_live_p2p_c_down_v1");
		terminalTopics.add("t_live_p2p_c_up_v1");
		terminalTopics.add("t_live_p2p_c_peer_v1");
		terminalTopics.add("t_live_p2p_c_stat_v1");

		assistTopics.add("t_vercheck");
		assistTopics.add("t_get_aboutus");
		assistTopics.add("t_exception");
		assistTopics.add("t_feedback");
		assistTopics.add("t_get_help");
		assistTopics.add("t_verupdate");

	}
}
