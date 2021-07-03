package com.vjti.model;

import lombok.*;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 13/6/21.
 */
@Data
public class SubjectRoomsVO extends SubjectVO {
    List<RoomVO> rooms;

    public SubjectRoomsVO(Integer sem, Integer subjectMstrSeq, String subject, List<RoomVO> rooms) {
        super(sem, subjectMstrSeq, subject);
        this.rooms = rooms;
    }
}
