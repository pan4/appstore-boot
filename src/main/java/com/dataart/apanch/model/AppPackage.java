package com.dataart.apanch.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APP_PACKAGE")
public class AppPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Lob
    @NotEmpty
    @Column(name = "FILE", nullable = false, length = 100000)
    private byte[] file;

    @Lob
    @Column(name = "SMALL_ICON", length = 100000)
    private byte[] smallIcon;

    @Lob
    @Column(name = "BIG_ICON", length = 100000)
    private byte[] bigIcon;

    @NotEmpty
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "SMALL_ICON_NAME")
    private String smallIconName;

    @Column(name = "BIG_ICON_NAME")
    private String bigIconName;

    @OneToOne(mappedBy = "appPackage", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private App app;
}
