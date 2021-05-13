package com.drive.basics.pojo.dto;


import com.drive.common.core.tree.BaseTreeNode;
import lombok.Data;

@Data
public class TreeNodeCategoryDto extends BaseTreeNode implements java.io.Serializable{

    // value
    private String value;

    private String label;

    private String type;


}
