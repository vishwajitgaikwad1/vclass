package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 10/7/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeVOList {
    List<SubmittedFilesVO> submissionList;
}
