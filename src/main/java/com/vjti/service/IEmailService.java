package com.vjti.service;

/**
 * Created by vishwajit_gaikwad on 18/7/21.
 */
public interface IEmailService {

    void sendMail(String message, String to, String subject);
}
