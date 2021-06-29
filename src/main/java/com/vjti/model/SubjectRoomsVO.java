package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 13/6/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRoomsVO {
    Integer sem;
    Integer subjectMstrSeq;
    String subject;
    List<RoomVO> rooms;
}
