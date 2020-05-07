package com.atguigu.bean;

import org.springframework.stereotype.Component;

@Component
public class Clazz2 {
    private Long id;
    private Long gradeId;//对应的年级信息
    private String name;
    private String remark;//班级信息备注

    @Override
    public String toString() {
        return "Clazz2{" +
                "id=" + id +
                ", gradeId=" + gradeId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Clazz2(Long gradeId, String name, String remark) {
        this.gradeId = gradeId;
        this.name = name;
        this.remark = remark;
    }

    public Clazz2() {
    }
}
