package com.sgai.property.ctl.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/20 18:14
 */
@Table(name = "p2_ctl_emp")
public class CtlEmpDto {
    /** 主键. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empCode;        // emp_id 员工代码
    private String deptCode;        // dept_id 部门代码
    private String deptName;        //deptName 部门名称
    private String lastname;        // lastname 姓名
    private String telepno1;        // telepno1 家庭电话号码
    private String telepno2;        // telepno2 工作单位电话号
    private String telepno3;        // telepno3 移动电话
    private String birthdt;        // birthdt 出生日期
    private String sex;        // sex 性别
    private String defaproj;        // defaproj 主要工作任务
    private String marriage;        // marriage 婚姻状况
    private String idCard;        // id_card 身份证号
    private String employdt;        // employdt 入职日期
    private String termindt;        // termindt 离职日期
    private String email;        // email 电子邮件
    /**部门主键.*/
    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelepno1() {
        return telepno1;
    }

    public void setTelepno1(String telepno1) {
        this.telepno1 = telepno1;
    }

    public String getTelepno2() {
        return telepno2;
    }

    public void setTelepno2(String telepno2) {
        this.telepno2 = telepno2;
    }

    public String getTelepno3() {
        return telepno3;
    }

    public void setTelepno3(String telepno3) {
        this.telepno3 = telepno3;
    }

    public String getBirthdt() {
        return birthdt;
    }

    public void setBirthdt(String birthdt) {
        this.birthdt = birthdt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDefaproj() {
        return defaproj;
    }

    public void setDefaproj(String defaproj) {
        this.defaproj = defaproj;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmploydt() {
        return employdt;
    }

    public void setEmploydt(String employdt) {
        this.employdt = employdt;
    }

    public String getTermindt() {
        return termindt;
    }

    public void setTermindt(String termindt) {
        this.termindt = termindt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
