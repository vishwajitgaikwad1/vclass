package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news_mstr")
public class NewsVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NEWS_MSTR_SEQ", unique = true, nullable = false)
    Integer newsMstrSeq;

    @Column(name = "NEWS_LABEL")
    String newsLabel;

    @Column(name = "NEWS_URL")
    String newsUrl;
}
