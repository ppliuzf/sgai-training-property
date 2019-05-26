package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;


/**
 * 空间关系描述Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmSpaceTree extends BoEntity<MdmSpaceTree> {
	
	private static final long serialVersionUID = 1L;
	private String spaceCode;		// node_code
	private String parentCode;		// parent_code
	private String spaceName;		// node_name
	private String nodeType;		// node_type
	private String nodeTable;		// node_table
	private Long nodeLevel;		// node_level
	private String busiId;		// busi_id
	private String nodeSequence;		// node_sequence
	private char enabledFlag;		// enabled_flag
	
	public MdmSpaceTree() {
		super();
	}

	public MdmSpaceTree(String id){
		super(id);
	}

	@Length(min=0, max=60, message="node_code长度必须介于 0 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
	@Length(min=0, max=60, message="parent_code长度必须介于 0 和 60 之间")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Length(min=0, max=60, message="node_name长度必须介于 0 和 60 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	@Length(min=0, max=60, message="node_type长度必须介于 0 和 60 之间")
	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	@Length(min=0, max=60, message="node_table长度必须介于 0 和 60 之间")
	public String getNodeTable() {
		return nodeTable;
	}

	public void setNodeTable(String nodeTable) {
		this.nodeTable = nodeTable;
	}
	
	public Long getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(Long nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	
	@Length(min=0, max=60, message="busi_id长度必须介于 0 和 60 之间")
	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	
	@Length(min=0, max=60, message="node_sequence长度必须介于 0 和 60 之间")
	public String getNodeSequence() {
		return nodeSequence;
	}

	public void setNodeSequence(String nodeSequence) {
		this.nodeSequence = nodeSequence;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public char getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(char enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}