package com.vjti.model;

import lombok.*;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 4/7/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssignmentVO {
    Integer courseMstrSeq;
    String courseName;
    List<SemAssignmentVO> semVOList;
}
