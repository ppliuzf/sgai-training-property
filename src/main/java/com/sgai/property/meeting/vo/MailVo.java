package com.sgai.property.meeting.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件配置
 *
 * @author 146584
 * @create 2017-11-03 15:45
 */
public class MailVo implements Serializable {
    //STMP 服务器
    private String host;

    //端口
    private int port;

    //是否需要认证
    private String auth;

    //发信名称
    private String name;

    //用户名
    private String from;

    //密码
    private String password;

    //收件人
    private List<String> toList;

    //抄送人
    private List<String> ccList;

    //主题
    private String subject;

    //内容
    private String content;

    public String getHost() {
        return host;
    }

    public MailVo setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MailVo setPort(int port) {
        this.port = port;
        return this;
    }

    public String getAuth() {
        return auth;
    }

    public MailVo setAuth(String auth) {
        this.auth = auth;
        return this;
    }

    public String getName() {
        return name;
    }

    public MailVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public MailVo setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MailVo setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<String> getToList() {
        return toList;
    }

    public MailVo setToList(List<String> toList) {
        this.toList = toList;
        return this;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public MailVo setCcList(List<String> ccList) {
        this.ccList = ccList;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MailVo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MailVo setContent(String content) {
        this.content = content;
        return this;
    }
}
