package xdata.etl.hbase.entity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 附件类容器
 * 
 * @author XuehuiHe
 * 
 * @param <T>
 */
public class HbaseAttachmentList<T extends HbaseAttachment> extends
		ArrayList<T> {
	private static final long serialVersionUID = -6873083192590600076L;
	private HbaseEntity mainPart;

	public HbaseAttachmentList(HbaseEntity mainPart) {
		super();
		this.mainPart = mainPart;
	}

	@Override
	public boolean add(T attachment) {
		attachment.setMainPart(mainPart);
		attachment.setIndex(size());
		attachment.setMainPartRow(mainPart.getRow());
		return super.add(attachment);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for (T t : c) {
			add(t);
		}
		return true;
	}

	public HbaseEntity getMainPart() {
		return mainPart;
	}
}
