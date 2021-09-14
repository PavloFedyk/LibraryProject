package com.library.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "rent_info")
public class RentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_status", nullable = false)
    private RentStatus rentStatus;

    @Column(name = "rent_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentDate;

    @Column(name = "required_return_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requiredReturnDate;

    @Column(name = "return_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
