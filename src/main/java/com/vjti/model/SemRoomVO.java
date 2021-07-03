package com.vjti.model;

import lombok.*;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 13/6/21.
 */
@Data
public class SemRoomVO extends SemVO {

    List<SubjectRoomsVO> roomsVOList;

    public SemRoomVO(Integer sem, List<SubjectRoomsVO> roomsVOList) {
        super(sem);
        this.roomsVOList = roomsVOList;
    }
}
