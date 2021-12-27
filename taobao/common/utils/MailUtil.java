package com.zsj.es.common.utils;

import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.shop.model.Order;
import com.zsj.es.system.dao.SysConfigMapper;
import com.zsj.es.system.model.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName MailUtil
 * @Description 邮件发送工具类
 * @Version 1.0
 **/
@Component
@Slf4j
public class MailUtil {

    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * @return void
     * @Author dinghao
     * @Description 邮件发送  Thymeleaf模板实现带单个附件
     * @Date 2020/3/17 10:09
     * @Param to    接收人邮箱
     * @Param subject  主题
     * @Param fileName  文件附件名称
     * @Param os  文件内存输出流
     * @Param personName 接收人姓名
     */
    @Async
    public void thymeleafEmail(String to, String subject,String personName, Order order) throws MessagingException, UnsupportedEncodingException {
        //log.info("------邮件发送start-------");
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        //获取邮件发送所需配置
        SysConfig config=configMapper.selectById(1);
        if (config == null) {
            throw new BusinessException("邮件发送失败，mail参数获取异常！");
        }
        if(config.getState()==1){
            String host = config.getConfigPar1();
            String port = config.getConfigPar2();
            String from = config.getConfigPar3();
            String password = config.getConfigPar4();
            String sender = config.getConfigPar5();
            jms.setHost(host);
            jms.setPort(Integer.parseInt(port));
            jms.setUsername(from);
            jms.setPassword(password);
            jms.setDefaultEncoding("Utf-8");
            Properties p = new Properties();
            //开启SSL加密 阿里云默认不能用25端口，所以改为465 ssl
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.enable", "true");
            p.put("mail.smtp.ssl.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.ssl.socketFactory.port", 465);
            jms.setJavaMailProperties(p);

            MimeMessage mimeMessage = jms.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from, sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            // 利用 Thymeleaf 模板构建 html 文本
            Context ctx = new Context();
            // 给模板的参数的上下文
            ctx.setVariable("personName", personName);
            ctx.setVariable("orderNo", order.getOrderNo());
            ctx.setVariable("nickName", order.getNickName());
            ctx.setVariable("phone", order.getPhone());
            ctx.setVariable("goodsSn", order.getGoodsSn());
            ctx.setVariable("goodsNum", order.getGoodsNum());
            ctx.setVariable("goodsMoney", order.getGoodsMoney());
            ctx.setVariable("goodsColor", order.getGoodsColor());
            ctx.setVariable("goodsSize", order.getGoodsSize());
            ctx.setVariable("madeLogo", order.getMadeLogo());
            ctx.setVariable("madeText", order.getMadeText());
            ctx.setVariable("orderStatus", order.getOrderStatus());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ctx.setVariable("tDate", sdf.format(new Date()));
            // 执行模板引擎，执行模板引擎需要传入模板名、上下文对象
            // Thymeleaf的默认配置期望所有HTML文件都放在 **resources/templates ** 目录下，以.html扩展名结尾。
            String emailText = templateEngine.process("mailTemplate", ctx);
            mimeMessageHelper.setText(emailText, true);

            jms.send(mimeMessage);
            //log.info("------邮件发送end-------");
        }else{
            //log.info("--邮件发送配置未启用,请启用后再发送--");
        }

    }

}
