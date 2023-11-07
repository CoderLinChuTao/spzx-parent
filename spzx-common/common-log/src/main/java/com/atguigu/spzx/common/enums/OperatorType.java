package com.atguigu.spzx.common.enums;

import lombok.Data;


public enum OperatorType {		// 操作人类别

    OTHER(1L,"admin","管理员"),		// 其他
    MANAGE(5L,"zhangsan","学生管理员"),		// 后台用户
    MOBILE(26L,"linchutao","业务人员");		// 手机端用户

    private Long id;
    private String name;
    private String type;

    private OperatorType(Long id, String name,String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}