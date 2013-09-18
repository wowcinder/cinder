package xdata.etl.kafka.transform.cclient.data;

import java.util.Arrays;

public class TerminalDataSwapper extends AbstractCClientDataSwapper {
	private boolean isVerticalEnding = false;

	public TerminalDataSwapper(String raw) {
		super(raw);
	}

	@Override
	protected void preRaw() {
		if (raw.charAt(raw.length() - 1) == '|') {
			isVerticalEnding = true;
		}
		strs = raw.split("\\|");
		if (isVerticalEnding) {
			String[] tempStrs = new String[strs.length + 1];
			for (int i = 0; i < strs.length; i++) {
				tempStrs[i] = strs[i];
			}
			tempStrs[strs.length] = "";
			strs = tempStrs;
		}
	}

	protected boolean doMatch() {
		if (attachmentClass == null) {
			if (strs.length == mainPartColumnInfos.size()) {
				return true;
			} else if (strs.length == mainPartColumnInfos.size() + 1
					&& isVerticalEnding) {
				strs = Arrays.copyOf(strs, strs.length - 1);
				return true;
			}
		} else {
			if ((strs.length - mainPartColumnInfos.size())
					% attachmentColumnInfos.size() == 0) {
				return true;
			} else if ((strs.length - mainPartColumnInfos.size() - 1)
					% attachmentColumnInfos.size() == 0
					&& isVerticalEnding) {
				strs = Arrays.copyOf(strs, strs.length - 1);
				return true;
			}
		}
		return false;
	}
}
