package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class HtTempletVO{

    @ApiModelProperty(value= "id")
    private String id;
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "合同类型")
    private String typeId; //合同类型
    @ApiModelProperty(value = "合同类型名称")
    private String typeName; //合同类型名称
    @ApiModelProperty(value = "合同模板名称")
    private String name; //合同模板名称
    @ApiModelProperty(value = "合同模板下载地址")
    private String url; //合同模板下载地址
    @ApiModelProperty(value = "合同模板编号")
    private String no; //合同模板编号
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
	@ApiModelProperty(value = "上传人")
    private String uploadBy; //上传人
    @ApiModelProperty(value = "上传时间")
    private String createDate;
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getTypeId(){  
        return typeId;  
    }
      
   public void setTypeId(String typeId){  
     this.typeId = typeId;  
    }

    public String getName(){  
        return name;  
    }
      
   public void setName(String name){  
     this.name = name;  
    }  
    public String getUrl(){  
        return url;  
    }
      
   public void setUrl(String url){  
     this.url = url;  
    }  

    public String getNo(){  
        return no;  
    }
      
   public void setNo(String no){  
     this.no = no;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}