package xdata.etl.hbase.entity;

import java.util.Date;

import xdata.etl.hbase.annotatins.HbaseExclude;

/**
 * Hbase 附件类
 * 
 * @author XuehuiHe
 * 
 */
public class HbaseAttachment extends HbaseEntity {
	private HbaseEntity mainPart;

	private String mainPartRow;
	/**
	 * 附件的index
	 */
	@HbaseExclude
	private int index = 0;

	public HbaseEntity getMainPart() {
		return mainPart;
	}

	public String getMainPartRow() {
		return mainPartRow;
	}

	public int getIndex() {
		return index;
	}

	public void setMainPart(HbaseEntity mainPart) {
		this.mainPart = mainPart;
	}

	public void setMainPartRow(String mainPartRow) {
		this.mainPartRow = mainPartRow;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Date getStamp() {
		if (super.getStamp() == null) {
			setStamp(getMainPart().getStamp());
		}
		return super.getStamp();
	}

}
