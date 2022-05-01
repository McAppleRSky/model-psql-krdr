package ru.rob.model.debt.entity;

import org.hibernate.annotations.GenericGenerator;
import ru.rob.model.debt.enums.DebtResponseStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "debt_request")
public class DebtRequest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID", strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
    public String id;

    @Column(name = "gis_id", nullable = false, columnDefinition = "varchar(36)")
    public String gisId;

    @Column(name = "request_number", nullable = false, columnDefinition = "varchar(30)")
    public String number;

    @Column(name = "start_date", nullable = false)
    public Date startDate;

    @Column(name = "end_date", nullable = false)
    public Date endDate;

    @Column(name = "house_addr", nullable = false, columnDefinition = "varchar(4000)")
    public String houseAddr;

    @Column(name = "house_guid", nullable = false, columnDefinition = "varchar(36)")
    public String houseGuid;

    @Column(name = "flat_number")
    public String flatNumber;

    @Column(name = "response_date", nullable = false)
    public Date responseDate;

    @Column(name = "requester_org_name", nullable = false)
    public String requesterOrgName;

    @Column(name = "requester_org_guid", nullable = false, columnDefinition = "varchar(36)")
    public String requesterOrgGuid;

    @Column(name = "requester_org_tel", columnDefinition = "varchar(30)")
    public String requesterOrgTel;

    @Column(name = "requester_fio", nullable = false)
    public String requesterFio;

    @Column(name = "response_status", nullable = false, columnDefinition = "varchar(36)")
    @Enumerated(EnumType.STRING)
    public DebtResponseStatus responseStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id", columnDefinition = "varchar(36)")
    public DebtResponse response;

}
