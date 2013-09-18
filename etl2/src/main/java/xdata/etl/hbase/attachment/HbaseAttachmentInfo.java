package xdata.etl.hbase.attachment;

import xdata.etl.hbase.entity.HbaseAttachment;

/**
 * 
 * @author XuehuiHe
 * 
 */
public class HbaseAttachmentInfo {
	/**
	 * 附件集合的属性名
	 */
	private String attriName;
	/**
	 * 附件的class
	 */
	private Class<? extends HbaseAttachment> attachmentClass;

	public HbaseAttachmentInfo() {
	}

	public HbaseAttachmentInfo(String attriName,
			Class<? extends HbaseAttachment> attachmentClass) {
		this.attachmentClass = attachmentClass;
		this.attriName = attriName;
	}

	public String getAttriName() {
		return attriName;
	}

	public Class<? extends HbaseAttachment> getAttachmentClass() {
		return attachmentClass;
	}

	public void setAttriName(String attriName) {
		this.attriName = attriName;
	}

	public void setAttachmentClass(
			Class<? extends HbaseAttachment> attachmentClass) {
		this.attachmentClass = attachmentClass;
	}

}
