package com.texoit.worstmovie.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "SEQ_STUDIO")
public class Studio extends Domain implements Serializable {

    private static final long serialVersionUID = 1L;

}
