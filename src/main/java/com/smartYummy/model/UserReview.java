package com.smartYummy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by chenglongwei on 5/22/16.
 */
@Entity
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
