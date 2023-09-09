package com.acontreras.springcloud.msvc.courses.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "course_users")
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CourseUser)) {
            return false;
        }
        CourseUser bean = (CourseUser) obj;

        return this.userId != null && this.userId.equals(bean.userId);
    }
}
