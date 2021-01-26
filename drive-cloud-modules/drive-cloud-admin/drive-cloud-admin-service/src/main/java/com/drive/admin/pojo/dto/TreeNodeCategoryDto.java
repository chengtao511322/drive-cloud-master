package com.drive.admin.pojo.dto;


import com.drive.common.core.tree.BaseTreeNode;
import lombok.Data;

@Data
public class TreeNodeCategoryDto extends BaseTreeNode {

    // value
    private String value;

    private String label;

    private String type;


}
